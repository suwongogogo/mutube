package Post.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.PageNotFoundException;
import Post.Model.PostPage;
import Post.Service.ListPostService;
import Post.Service.ReadMyPostService;
import User.Exception.UserNotFoundException;

public class ReadMyPostHandler implements CommandHandler {
	private static final String ERROR_PAGE = "/error.jsp";

	// 페이지 번호를 파라미터로 받고 서비스를 이용해서 페이지 목록을 받아옴
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		try {
			ReadMyPostService readMyPostService = ReadMyPostService.getInstance();
			int pageNum = 1;
			if (req.getParameter("pageNum") != null && !req.getParameter("pageNum").equals("")) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}

			if (pageNum < 0) {
				throw new PageNotFoundException("페이지를 찾을 수 없습니다.");
			}

			int userId = 0;
			if (req.getParameter("userId") != null && !req.getParameter("userId").equals("")) {
				userId = Integer.parseInt(req.getParameter("userId"));
			}

			if (userId < 0) {
				throw new UserNotFoundException("유저를 찾을 수 없습니다.");
			}
			System.out.println(pageNum);
			PostPage postPage = readMyPostService.readMyPost(pageNum, userId);

			req.setAttribute("postPage", postPage);

			return "/WEB-INF/view/post/myPostList.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/myPage.jsp");
			return ERROR_PAGE;
		} catch (PageNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PageNotFound");
			error.put("from", "/myPage.jsp");
			return ERROR_PAGE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/myPage.jsp");
			return ERROR_PAGE;
		}
	}
}
