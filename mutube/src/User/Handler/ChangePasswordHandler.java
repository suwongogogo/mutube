package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.NewPasswordNotMatchException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.ChangePasswordService;

public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/user/changePassword.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		ChangePasswordService changePassword = ChangePasswordService.getInstance();
		User user = changePassword.selectByUserId(userId);
		
		req.setAttribute("user", user);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		try {
			String now_password = req.getParameter("now_password");
			String new_password = req.getParameter("new_password");
			String new_password_confirm = req.getParameter("new_password_confirm");
			
			User loginUser = null;
			if(req.getSession().getAttribute("loginUser")!= null) {
				loginUser =  (User)req.getSession().getAttribute("loginUser");
			}
			
			Map<String, Boolean> errors = new HashMap<String, Boolean>();
			req.setAttribute("errors", errors);
			if(loginUser == null) {
				throw new UserNotFoundException("없는 유저입니다.");
			}
			if(now_password.isEmpty() || now_password == null) {
				errors.put("now_password", true);
			}
			if(new_password.isEmpty() || new_password == null) {
				errors.put("new_password", true);
			}
			if(new_password_confirm.isEmpty() || new_password_confirm == null) {
				errors.put("new_password_confirm", true);
			}
			if(!new_password.equals(new_password_confirm)) {
				errors.put("passwordNotMatch", true);
				return FORM_VIEW;
			}
			
			ChangePasswordService changePassword = ChangePasswordService.getInstance();
			User user = changePassword.changePwd(new_password, loginUser.getLoginId());
			
			loginUser.setPassword(user.getPassword());
			
			resp.sendRedirect(req.getContextPath()+"/myPage.jsp");
			return null;
			
		}catch(SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/user/changePassword");
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/user/changePassword");
		}
		return ERROR_PAGE;
	}

}
