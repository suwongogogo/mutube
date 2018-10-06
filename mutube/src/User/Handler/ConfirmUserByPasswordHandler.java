package User.Handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.PasswordNotMatchException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.ConfirmUserByPasswordService;

public class ConfirmUserByPasswordHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/user/confirmUserByPassword.jsp";
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
		System.out.println("폼");
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		System.out.println("실행이당.");
		ConfirmUserByPasswordService confirmService = ConfirmUserByPasswordService.getInstance();
		String password = req.getParameter("password");
		
		
		try {
			User sessionUser = (User) req.getSession().getAttribute("loginUser");
			User user = confirmService.confirmUser(sessionUser.getLoginId());
			
			System.out.println(password + ", " + sessionUser.getPassword());
			
			if(!user.getPassword().equals(password) &&!sessionUser.getPassword().equals(password)) {
				throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
			}
			
			resp.sendRedirect(req.getContextPath()+"/myPage.jsp");
		}catch(PasswordNotMatchException e) {
			e.printStackTrace();
			return FORM_VIEW;
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			return FORM_VIEW;
		}
		return null;
	}
	
}
