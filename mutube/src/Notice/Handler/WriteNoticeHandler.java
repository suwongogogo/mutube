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
import Notice.Model.Notice;
import Notice.Model.NoticeContent;
import Notice.Model.NoticeData;
import Notice.Service.WriteNoticeFailException;
import Notice.Service.WriteNoticeService;
import Post.Exception.WritePostFailException;
import Post.Model.Writer;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class WriteNoticeHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/notice/writeNoticeForm.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		try {
			if(loginUser == null) {
				throw new UserNotFoundException("유저를 찾을 수 없습니다.");
			}
			if(loginUser.isAuthority() == false) {
				throw new NoPermissionException("어드민 권한이 없습니다.");
			}
			
		}catch(NoPermissionException e) {
			e.printStackTrace();
			req.setAttribute("errorCode", "UserNotFound");
			return ERROR_PAGE;
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			req.setAttribute("errorCode", "NoPermisson");
			return ERROR_PAGE;
		}
		
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// 세션에 있는 유저의 정보와 파라미터로 값을 받고
		User loginUser = (User) req.getSession().getAttribute("loginUser");

		// 넘겨받은 이미지 파일에 대한 정보를 저장
		String directory = req.getServletContext().getRealPath("/upload/");
		int maxSize = 1024 * 1024 * 5;
		String encoding = "utf-8";

		ArrayList<String> imageNames = new ArrayList<>();
		Map<String, String> params = new HashMap<>();

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
		
		if (ServletFileUpload.isMultipartContent(req)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {// 파일일때..
						String name = item.getName();
						if (!name.equals("")) {
							if (name.endsWith(".jpg") ||name.endsWith(".png")||name.endsWith(".PNG") || name.endsWith(".gif")
									|| name.endsWith(".jpeg")) {
								String date = sdf.format(new Date());
								item.write(new File(directory + date + name));
								imageNames.add(date + name);
							}else {
								errors.put("imageType", true);
							}
						}
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
		
		if (params.get("title").trim().equals("")) {
			errors.put("title", true);
			return FORM_VIEW;
		}
		// 글 정보는 Post, 내용은 PostContent 객체에 담아 WriteRequest를 생성.
		Notice notice = new Notice(new Writer(loginUser.getUserId(), loginUser.getName()), params.get("title"));
	
		
		NoticeContent noticeContent = null;
		if (!imageNames.isEmpty()) {

			noticeContent = new NoticeContent(params.get("content"), params.get("video_link"), imageNames);
		} else {

			noticeContent = new NoticeContent(params.get("content"), params.get("video_link"));
		}
		noticeContent.trimLink();

		
		NoticeData writeReq = new NoticeData(notice, noticeContent);

		// WriteRequest의 무결성 검사를 진행하고 이상있으면 다시 FORM_VIEW로 이동

		// post.writeValidate(errors);
		if (!errors.isEmpty()) {
			for(String as : errors.keySet())
				System.out.println(as);
			return FORM_VIEW;
		}

		// WriteService를 이용한다.
		WriteNoticeService writeNoticeService = WriteNoticeService.getInstance();
		int postId = 0;
		try {
			postId = writeNoticeService.writeNotice(writeReq);
			resp.sendRedirect(req.getContextPath() + "/notice/notice");

		} catch(WriteNoticeFailException e) {
			e.printStackTrace();
			error.put("errorCode", "WriteNoticeFail");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		} catch (SQLException e) {
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		}

		return null;
	}
}
