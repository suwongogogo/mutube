package Admin.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Service.DeleteUserService;
import Handler.CommandHandler;
import User.Exception.UserNotFoundException;

public class DeleteUserHandler implements CommandHandler{
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
		
		
		try {
			int userId = 0;
			if(req.getParameter("userId") != null) {
				userId = Integer.parseInt(req.getParameter("userId"));
			}
			if(userId == 0) {
				throw new UserNotFoundException("유저를 찾을 수 없습니다.");
			}
			DeleteUserService deleteUserService = DeleteUserService.getInstance();
			deleteUserService.deleteUser(userId);
			
			resp.sendRedirect(req.getContextPath() + "/admin/userManagement");
			return null;
			
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/admin/userManagement");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/admin/userManagement");
		}
		return ERROR_PAGE;
	}
	
}
