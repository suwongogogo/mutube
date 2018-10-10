package Post.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Handler.CommandHandler;
import Post.Exception.UpdatePostFailExcpetion;
import Post.Model.File;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Model.Writer;
import Post.Service.UpdatePostService;
import Post.Service.WritePostService;
import User.Model.User;

public class UpdatePostHandler implements Handler.CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/post/updatePostForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		int postId = Integer.parseInt(req.getParameter("no"));

		UpdatePostService updatePostService = UpdatePostService.getInstance();
		PostData postData = updatePostService.getPost(postId);
		req.setAttribute("postData", postData);

		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int postId = Integer.parseInt(req.getParameter("no"));

			Map<String, Boolean> errors = new HashMap<>();
			req.setAttribute("errors", errors);

			//넘겨받은 이미지 파일에 대한 정보를 저장
			String directory = req.getServletContext().getRealPath("/upload/");
			int maxSize = 1024*1024*10;
			String encoding = "utf-8";
			
			MultipartRequest multipartRequest = new MultipartRequest(req, directory, maxSize, encoding, 
					new DefaultFileRenamePolicy());
			
			
			
			
			Post post = new Post();
			post.setTitle(multipartRequest.getParameter("title"));
			post.setGenre(multipartRequest.getParameter("genre"));
			post.setCountry(multipartRequest.getParameter("country"));
			post.setInstrument(multipartRequest.getParameter("instrument"));
			post.setPostId(postId);
			post.writeValidate(errors);
			if (!errors.isEmpty()) {
				return FORM_VIEW;
			}
			
			PostContent postContent = null;
			File image = null;
			if(multipartRequest.getFile("image")!= null ) {
				image = new File();
			
				image.setFileName(multipartRequest.getOriginalFileName("image"));
				image.setFileRealName(multipartRequest.getFile("image").getAbsolutePath());
			
				postContent = new PostContent(multipartRequest.getParameter("content"),multipartRequest.getParameter("video_link"));
			}else{
			
				postContent = new PostContent(multipartRequest.getParameter("content"),multipartRequest.getParameter("video_link"));
			}
			postContent.trimLink();

			PostData postData = new PostData(post, postContent);
			UpdatePostService updatePostService = UpdatePostService.getInstance();

			updatePostService.update(postData);
			
			resp.sendRedirect(req.getContextPath()+"/post/view?no="+postId);
		} catch( UpdatePostFailExcpetion e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
