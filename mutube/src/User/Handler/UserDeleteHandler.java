package User.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.UserDeleteService;

public class UserDeleteHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User sessionUser = (User) req.getSession().getAttribute("loginUser");

		try {
			UserDeleteService deleteService = UserDeleteService.getInstance();
			int cnt = deleteService.delete(sessionUser.getUserId());
			
			req.getSession().setAttribute("loginUser", null);
			resp.sendRedirect(req.getContextPath() + "/Main.jsp");
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/myPage.jsp");
		}
		return null;
	}


}
