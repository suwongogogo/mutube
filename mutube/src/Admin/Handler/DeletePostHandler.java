package Admin.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Service.DeletePostService;
import Handler.CommandHandler;
import Post.Exception.PostNotFoundException;

public class DeletePostHandler implements CommandHandler{

	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
		
		try {
			
			if(req.getParameter("check")!=null && req.getParameter("check").equals("true")) {
				String[] selected = req.getParameterValues("delete");
				
				DeletePostService deletePostService = DeletePostService.getInstance();
				
				for(String s : selected) {
					int postId = Integer.parseInt(s);
					deletePostService.DeletePost(postId);
				}
				resp.sendRedirect(req.getContextPath() + "/admin/postManagement");
				return null;
			}else {
				
				int postId = 0;
				if(req.getParameter("postId") != null) {
					postId = Integer.parseInt(req.getParameter("postId"));
				}	
				
				DeletePostService deletePostService = DeletePostService.getInstance();
				deletePostService.DeletePost(postId);
					
				resp.sendRedirect(req.getContextPath() + "/admin/postManagement");
				return null;
			}
			
		}catch(PostNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "postNotFound");
			error.put("from", "/mutube/admin/postManagement");
		} catch (SQLException e) {
			error.put("errorCode", "dbError");
			error.put("from", "/mutube/admin/postManagement");
			e.printStackTrace();
		}
		return ERROR_PAGE;
		
	}

}
