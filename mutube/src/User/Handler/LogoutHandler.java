package User.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;

public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.getSession().setAttribute("loginUser", null);
		resp.sendRedirect(req.getContextPath() + "/Main.jsp");
		
		return null;
	}

	
}
