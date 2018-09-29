package Post.Handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Model.PostComment;
import User.Model.User;

public class WriteCommentHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/writeComment.jsp";

	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		String postIdstr = req.getParameter("postId");
		int postId = Integer.parseInt(postIdstr);
		
//		PostComment postComment = new PostComment(postId, , req.getParameter("name"), 
//				req.getParameter("comment"));
		
		return "/WEB-INF/view/writeCommentSuccess.jsp";
	}

}
