package Post.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.UpdatePostFailExcpetion;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Model.Writer;
import Post.Service.UpdatePostService;
import Post.Service.WritePostService;
import User.Model.User;

public class UpdatePostHandler implements Handler.CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/updatePostForm.jsp";

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

			Post post = new Post();
			post.setTitle(req.getParameter("title"));
			post.setGenre(req.getParameter("genre"));
			post.setCountry(req.getParameter("musician"));
			post.setInstrument(req.getParameter("instrument"));
			post.setPostId(postId);
			post.writeValidate(errors);
			if (!errors.isEmpty()) {
				return FORM_VIEW;
			}

			PostContent postContent = new PostContent(req.getParameter("content"), req.getParameter("video_link"));
			postContent.trimLink();

			PostData postData = new PostData(post, postContent);
			UpdatePostService updatePostService = UpdatePostService.getInstance();

			updatePostService.update(postData);
			
			resp.sendRedirect(req.getContextPath()+"/viewPost?no="+postId);
		} catch( UpdatePostFailExcpetion e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
