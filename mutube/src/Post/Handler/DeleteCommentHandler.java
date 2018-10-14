package Post.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.CommentNotFoundException;
import Post.Service.DeleteCommentService;
import User.Model.User;

public class DeleteCommentHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
			resp.sendRedirect(req.getContextPath() + "/post/view?no="+postId+"&pageNum="+pageNum);
			return null;
		}
		return null;
	}
	
}
