package User.Handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.PasswordNotMatchException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.ConfirmUserByPasswordService;

public class ConfirmUserByPasswordHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/confirmUserByPassword.jsp";
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
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws PasswordNotMatchException, UserNotFoundException {
		
		String password = req.getParameter("password");
		User sessionUser = (User) req.getSession().getAttribute("loginUser");
		
		ConfirmUserByPasswordService confirmService = ConfirmUserByPasswordService.getInstance();
		try {
			if(!password.equals(sessionUser.getPassword())) {
				throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
			}
			
			User user = confirmService.confirmUser(password);
			
			req.setAttribute("User", user);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return "/WEB-INF/view/myPage.jsp";
	}
	
}
