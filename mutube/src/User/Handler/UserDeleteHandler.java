package User.Handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.UserDeleteService;

public class UserDeleteHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/userDeleteForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		
	
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		req.setAttribute("userId", userId);
		
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		
		User sessionUser = (User)req.getSession().getAttribute("loginUser");
		
		try {
			UserDeleteService deleteService = UserDeleteService.getInstance();
			deleteService.delete(sessionUser.getUserId());
						
			req.setAttribute("success", true);
			return "/WEB-INF/view/deleteSuccess.jsp";
		}catch(UserNotFoundException e) {
			req.setAttribute("success", false);
			e.printStackTrace();
		}
		return null;
	}
	
	
}
