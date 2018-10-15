package Post.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.DeletePostFailException;
import Post.Exception.PostNotFoundException;
import Post.Service.DeletePostService;

public class DeletePostHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/post/deletePostForm.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		try {
			int postId = 0;
			if (req.getParameter("no") != null) {
				postId = Integer.parseInt(req.getParameter("no"));
			}

			if (postId == 0) {
				throw new PostNotFoundException("잘못된 게시글번호");
			}
			DeletePostService deletePostService = DeletePostService.getInstance();
			int cnt = deletePostService.delete(postId);
			
			if(cnt < 0) {
				throw new DeletePostFailException("게시글 삭제 실패");
			}
			
			resp.sendRedirect(req.getContextPath() + "/post/list");

		} catch (PostNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PostNotFound");
			error.put("from", "/post/list");
			return ERROR_PAGE;
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/post/list");
			return ERROR_PAGE;
		} catch(DeletePostFailException e) {
			e.printStackTrace();
			error.put("errorCode", "DeletePostFail");
			error.put("from", "/post/list");
			return ERROR_PAGE;
		}
		return null;
	}

}
