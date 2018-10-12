package Admin.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Exception.DeleteFailException;
import Admin.Service.DeleteUserService;
import Handler.CommandHandler;
import User.Exception.UserNotFoundException;

public class DeleteUserHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
		}catch(DeleteFailException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/userManagement");
			return null;
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/admin/userManagement");
			return null;
		}
		return null;
	}
	
}
