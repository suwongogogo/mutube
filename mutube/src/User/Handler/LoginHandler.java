package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.PasswordNotMatchException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.LoginService;

public class LoginHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/user/loginForm.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		return null;

	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		
		User user = new User(loginId, password);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		user.loginValidate(errors);
		if( !errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			LoginService loginService = LoginService.getInstance();
			
			User loginUser = loginService.login(user);
			
			req.getSession().setAttribute("loginUser", loginUser);
			
			resp.sendRedirect(req.getContextPath() + "/Main.jsp");
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/user/login");
			return ERROR_PAGE;
		} catch (PasswordNotMatchException e) {
			e.printStackTrace();
			errors.put("passwordNotMatch", true);
			return FORM_VIEW;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			errors.put("userNotFound",true);
			return FORM_VIEW;
		}

	}

}
