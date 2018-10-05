package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.PasswordNotMatchException;
import User.Model.User;
import User.Service.LoginService;

public class LoginHandler implements CommandHandler{

	public static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
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
			System.out.println("로그인 성공"+loginUser.getName());
			
			req.getSession().setAttribute("loginUser", loginUser);
			
			resp.sendRedirect(req.getContextPath() + "/Main.jsp");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PasswordNotMatchException e) {
			e.printStackTrace();
			errors.put("passwordNotMatch", true);
			return FORM_VIEW;
		}
		
		return null;
	}

}
