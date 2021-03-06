package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.UserAlreadyExistException;
import User.Model.User;
import User.Service.RegisterService;

public class RegisterHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/user/registerForm.jsp";
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
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		
		// errors 맵을 이용해 무결성 검사
		// RegisterService 객체를 이용해 회원가입 진행
		// 회원가입 성공 시 로그인 화면으로 전환.
		
		User user = new User();
		user.setLoginId(req.getParameter("loginId"));
		user.setPassword(req.getParameter("password"));
		user.setConfirm_password(req.getParameter("confirmPassword"));
		user.setEmail(req.getParameter("email"));
		user.setName(req.getParameter("name"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		RegisterService registerService = RegisterService.getInstance();
		
		
		user.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		if(!user.matchPassword()) {
			errors.put("notMatch", true);
			return FORM_VIEW;
		}
		
		
		try {
			registerService.register(user);
			
			return "/WEB-INF/view/user/loginForm.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/user/register");
			return ERROR_PAGE;
		} catch (UserAlreadyExistException e) {
			e.printStackTrace();
			errors.put("userExist", true);
			return FORM_VIEW;
		}
	
		
	}

	
	
}
