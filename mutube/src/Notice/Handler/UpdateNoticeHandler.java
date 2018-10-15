package Notice.Handler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import Notice.Model.Notice;
import Notice.Model.NoticeContent;
import Notice.Model.NoticeData;
import Notice.Service.UpdateNoticeService;
import Post.Exception.PostNotFoundException;
import Post.Exception.UpdatePostFailExcpetion;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Model.Writer;
import Post.Service.UpdatePostService;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class UpdateNoticeHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/notice/updateNoticeForm.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	private static final String SUCCESS_PAGE = "/success.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("Post")) {
			return processSubmit(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			;
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		Map<String, String> error = new HashMap<String, String>();
		try {
			if (loginUser == null) {
				throw new UserNotFoundException("유저를 찾을 수 없습니다.");
			}
			if (loginUser.isAuthority() == false) {
				throw new NoPermissionException("어드민 권한이 없습니다.");
			}

		} catch (NoPermissionException e) {
			e.printStackTrace();
			error.put("errorCode", "NoPermission");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "UserNotFound");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		}
		int noticeId = Integer.parseInt(req.getParameter("noticeId"));
		try {
			UpdateNoticeService updatePostService = UpdateNoticeService.getInstance();
			NoticeData noticeData = updatePostService.getNotice(noticeId);
			req.setAttribute("noticeData", noticeData);
		}catch(SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		}
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User loginUser = (User) req.getSession().getAttribute("loginUser");

		String directory = req.getServletContext().getRealPath("/upload/");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);

		ArrayList<String> imageNames = new ArrayList<>();
		Map<String, String> params = new HashMap<>();

		int noticeId = 0;
		
		try {
			if (ServletFileUpload.isMultipartContent(req)) {
				try {
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
					SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

					for (FileItem item : multiparts) {
						if (!item.isFormField()) {// 파일일때..
							String name = item.getName();
							if (!name.equals("")) {
								if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".PNG")
										|| name.endsWith(".gif") || name.endsWith(".jpeg")) {
									String date = sdf.format(new Date());
									item.write(new File(directory + date + name));
									imageNames.add(date + name);
								}
							}
							System.out.println(name);
						} else {
							String name = item.getFieldName();
							String value = item.getString("UTF-8");
							params.put(name, value);
							System.out.println(name + ", " + value);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (params.get("noticeId") != null) {
				noticeId = Integer.parseInt(params.get("noticeId"));
			}
			
			System.out.println(noticeId);
			if (noticeId == 0) {
				throw new NoticeNotFoundException("올바르지 않은 게시글 번호");
			}

			Notice notice = new Notice(noticeId, new Writer(loginUser.getUserId(), loginUser.getName()),
					params.get("title"));

			notice.writeValidate(errors);
			if (!errors.isEmpty()) {
				return FORM_VIEW;
			}
			NoticeContent noticeContent = new NoticeContent(noticeId, params.get("content"), params.get("video_link"));
			if (!imageNames.isEmpty()) {
				noticeContent.setImageNames(imageNames);
			}
			noticeContent.trimLink();

			NoticeData noticeData = new NoticeData(notice, noticeContent);
			UpdateNoticeService updateNoticeService = UpdateNoticeService.getInstance();
			updateNoticeService.update(noticeData);

			Map<String, String> success = new HashMap<String, String>();
			req.setAttribute("success", success);

			success.put("successCode", "updatePost");
			success.put("from", "/notice/readNotice?noticeId=" + noticeId);
			return SUCCESS_PAGE;

		} catch (NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "NoticeNotFound");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId);
			return ERROR_PAGE;
		} catch (UpdatePostFailExcpetion e) {
			e.printStackTrace();
			error.put("errorCode", "UpdateNoticeFail");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId);
			return ERROR_PAGE;
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId);
			return ERROR_PAGE;
		}
	}
}
