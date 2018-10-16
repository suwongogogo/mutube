package Post.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Handler.CommandHandler;
import Post.Exception.UpdatePostFailExcpetion;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Model.Writer;
import Post.Service.UpdatePostService;
import Post.Service.WritePostService;
import User.Model.User;

public class UpdatePostHandler implements Handler.CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/post/updatePostForm.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	private static final String SUCCESS_PAGE = "/success.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("Post")) {
			return processSubmit(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		int postId = Integer.parseInt(req.getParameter("no"));

		UpdatePostService updatePostService = UpdatePostService.getInstance();
		PostData postData;
		try {
			postData = updatePostService.getPost(postId);
			req.setAttribute("postData", postData);
			System.out.println(postData.getPost().getPostId());
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/post/view?postId="+postId);
		}
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User loginUser = (User) req.getSession().getAttribute("loginUser");

		String directory = req.getServletContext().getRealPath("/upload/");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		

		ArrayList<String> imageNames = new ArrayList<>();
		Map<String, String> params = new HashMap<>();

		int postId = 0;
		int pageNum = 1;
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
							if(name.equals("image")) {
								imageNames.add(value);
							}
							System.out.println(name + ", " + value);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (params.get("no") != null) {
				postId = Integer.parseInt(params.get("no"));
			}
			
			if (params.get("pageNum") != null) {
				pageNum = Integer.parseInt(params.get("pageNum"));
			}
			System.out.println(postId);

			
			if (postId == 0) {
				throw new PostNotFoundException("올바르지 않은 게시글 번호");
			}
	
			Post post = new Post(postId, new Writer(loginUser.getUserId(), loginUser.getName()), params.get("title"),
					params.get("genre"), params.get("country"), params.get("instrument"));
			
			
			post.writeValidate(errors);
			if (!errors.isEmpty()) {
				return FORM_VIEW;
			}
			PostContent postContent = new PostContent(postId, params.get("content"), params.get("video_link"));
			if(!imageNames.isEmpty()) {
				postContent.setImageNames(imageNames);
			}
			postContent.trimLink();

			PostData postData = new PostData(post, postContent);
			UpdatePostService updatePostService = UpdatePostService.getInstance();
			updatePostService.update(postData);

			Map<String, String> success = new HashMap<String, String>();
			req.setAttribute("success", success);

			success.put("successCode", "updatePost");
			success.put("from", "/post/view?no=" + postId);
			return SUCCESS_PAGE;
			
		} catch (PostNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PostNotFound");
			error.put("from", "/post/view?no=" + postId+"&pageNum="+pageNum);
			return ERROR_PAGE;
		} catch (UpdatePostFailExcpetion e) {
			e.printStackTrace();
			error.put("errorCode", "UpdatePostFail");
			error.put("from", "/post/view?no=" + postId+"&pageNum="+pageNum);
			return ERROR_PAGE;
		} catch (SQLException e) {
			error.put("errorCode", "dbError");
			error.put("from", "/post/view?no=" + postId+"&pageNum="+pageNum);
			return ERROR_PAGE;
		}
	}

}
