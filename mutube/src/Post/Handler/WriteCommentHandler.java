package Post.Handler;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.FailWriteCommentException;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostComment;
import Post.Model.PostData;
import Post.Service.WriteCommentService;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class WriteCommentHandler implements CommandHandler {

	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		WriteCommentService writeComment = WriteCommentService.getInstance();
		int postId = Integer.parseInt(req.getParameter("postId"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String comment = req.getParameter("comment");
		
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		try {
			
			Post post = writeComment.selectById(postId);
			if(loginUser == null) {
				throw new UserNotFoundException("로그인하지 않으셨습니다.");
			}
			
			PostComment postComment = new PostComment(post.getPostId(), loginUser.getUserId(), comment);
		
			writeComment.writeComment(postComment);
			
			resp.sendRedirect(req.getContextPath()+"/post/view?no="+postId+"&pageNum="+pageNum);
			
		}catch(FailWriteCommentException e) {
			e.printStackTrace();
			error.put("errorCode", "FailWriteComment");
			error.put("from", "/post/view");
		}catch(PostNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PostNotFound");
			error.put("from", "/post/view");
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "UserNotFound");
			error.put("from", "/post/view");
		}catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/post/view");
		}
		return null;
	}

}
