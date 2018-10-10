package Post.Handler;


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
		try {
			int postId = Integer.parseInt(req.getParameter("postId"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			String comment = req.getParameter("comment");
			User sessionUser = (User) req.getSession().getAttribute("loginUser");
			Post post = writeComment.selectById(postId);
			if(sessionUser == null) {
				throw new UserNotFoundException("로그인하지 않으셨습니다.");
			}
			
			PostComment postComment = new PostComment(post.getPostId(), sessionUser.getUserId(), sessionUser.getLoginId(), sessionUser.getName(), comment);
		
			writeComment.writeComment(postComment);
			
			
			resp.sendRedirect(req.getContextPath()+"/post/view?no="+postId+"&pageNum="+pageNum);
		}catch(FailWriteCommentException e) {
			e.printStackTrace();
			return "/WEB-INF/view/post/readPost.jsp";
		}catch(PostNotFoundException e) {
			e.printStackTrace();
			return "/WEB-INF/view/post/list.jsp";
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			return "/WEB-INF/view/user/loginForm.jsp";
		}
		return null;
	}

}
