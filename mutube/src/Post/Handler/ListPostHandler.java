package Post.Handler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Post.Model.PostPage;
import Post.Service.ListPostService;

public class ListPostHandler implements Handler.CommandHandler {
	private static final String ERROR_PAGE = "/error.jsp";
	// 페이지 번호를 파라미터로 받고 서비스를 이용해서 페이지 목록을 받아옴
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		try {
			ListPostService postService = ListPostService.getInstance();
			int pageNum = 1;
			if (req.getParameter("pageNum") != null  && !req.getParameter("pageNum").equals("")) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}
			System.out.println(pageNum);
			PostPage postPage = postService.getPostPage(pageNum);

			req.setAttribute("postPage", postPage);
 
			return "/WEB-INF/view/post/list.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/Main.jsp");
			return ERROR_PAGE;
		}
	}
}
