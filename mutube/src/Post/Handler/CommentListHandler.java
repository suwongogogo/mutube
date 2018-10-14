package Post.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.PageNotFoundException;
import Post.Model.CommentPage;
import Post.Model.PostData;
import Post.Service.CommentListService;

public class CommentListHandler implements CommandHandler{
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		try {
		int postId = Integer.parseInt(req.getParameter("no"));
		String pageNumStr = req.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		if(pageNum == 0) {
			throw new PageNotFoundException("페이지를 찾을 수 없습니다.");
		}
		CommentListService commentList = CommentListService.getInstance();
		CommentPage commentPage = commentList.commentList(pageNum, postId);
		PostData postData = new PostData();
		postData.setCommentPage(commentPage);
		
		
		resp.sendRedirect(req.getContextPath()+"/post/view?no="+postId+"&pageNum="+pageNum);
		}catch(PageNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PageNotFound");
			error.put("from", "/post/view");
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
