package Admin.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Service.DeletePostService;
import Handler.CommandHandler;
import Post.Exception.PostNotFoundException;

public class DeletePostHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			int postId = 0;
			if(req.getParameter("postId") != null) {
				postId = Integer.parseInt(req.getParameter("postId"));
			}
			
			DeletePostService deletePostService = DeletePostService.getInstance();
			deletePostService.DeletePost(postId);
			
			resp.sendRedirect(req.getContextPath() + "/admin/postManagement");
		}catch(PostNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/postManagement");
			return null;
		}
		return null;
	}

}
