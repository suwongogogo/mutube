package Post.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.CommentNotFoundException;
import Post.Service.DeleteCommentService;
import User.Model.User;

public class DeleteCommentHandler implements CommandHandler{
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		int commentId = 0;
		int postId = 0;
		int pageNum = 1;
		if(req.getParameter("commentId") != null) {
			commentId = Integer.parseInt(req.getParameter("commentId"));
		}
		if(req.getParameter("no") != null) {
			postId = Integer.parseInt(req.getParameter("no"));
		}
		if(req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}

		try {
			DeleteCommentService deleteCommentService = DeleteCommentService.getInstance();
			deleteCommentService.deleteComment(commentId);
			
			resp.sendRedirect(req.getContextPath() + "/post/view?no="+postId+"&pageNum="+pageNum);
		}catch(CommentNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "CommentNotFound");
			return ERROR_PAGE;
		}catch(SQLException e) {
			e.printStackTrace();
			error.put("error", "dbError");
			error.put("from", "/post/view");
			return ERROR_PAGE;
		}
		return null;
	}
	
}
