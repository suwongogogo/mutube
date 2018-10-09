package Post.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Model.PostData;
import Post.Service.CommentListService;
import Post.Service.CommentPage;

public class CommentListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int postId = Integer.parseInt(req.getParameter("no"));
		String pageNumStr = req.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		CommentListService commentList = CommentListService.getInstance();
		CommentPage commentPage = commentList.commentList(pageNum, postId);
		PostData postData = new PostData();
		postData.setCommentPage(commentPage);
		
		req.setAttribute("postData", postData);
		resp.sendRedirect(req.getContextPath()+"/post/view?no="+postId+"&pageNum="+pageNum);
		
		return null;
	}
	

}
