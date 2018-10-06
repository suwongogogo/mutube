package Post.Handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.PostNotFoundException;
import Post.Service.DeletePostService;

public class DeletePostHandler implements CommandHandler {

	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int postId = 0;
		if(req.getParameter("no")!=null) {
			postId = Integer.parseInt(req.getParameter("no"));
		}
		try {
			if(postId==0) {
				resp.sendRedirect(req.getContextPath() + "/list");
				throw new PostNotFoundException("잘못된 게시글번호");
			}
		} catch(PostNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return "/WEB-INF/view/deletePostForm.jsp?no="+postId;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(req.getParameter("submit").equals("아니오")) {
			resp.sendRedirect(req.getContextPath()+"/Main.jsp");
			return null;
		}
		
		try {
			int postId = 0;
			if(req.getParameter("no")!=null) {
				postId = Integer.parseInt(req.getParameter("no"));
			}
			if(postId==0) {
				throw new PostNotFoundException("잘못된 게시글번호");
			}
			DeletePostService deletePostService = DeletePostService.getInstance();
			deletePostService.delete(postId);
			
			System.out.println("삭제끝@");
			resp.sendRedirect(req.getContextPath() + "/list");
			return null;
		} catch(PostNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
