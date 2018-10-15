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
import User.Exception.ComfirmPasswordNotMatchException;
import User.Exception.samePasswordException;
import User.Model.User;
import User.Service.ChangePasswordService;

public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/user/changePassword.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	private static final String SUCCESS_PAGE = "/success.jsp";
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
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		User loginUser = null;
		if(req.getSession().getAttribute("loginUser")!= null) {
			loginUser =  (User)req.getSession().getAttribute("loginUser");
		}
		
		try {
			String now_password = req.getParameter("now_password");
			String new_password = req.getParameter("new_password");
			String new_password_confirm = req.getParameter("new_password_confirm");
			
			
			if(loginUser == null) {
				throw new UserNotFoundException("없는 유저입니다.");
			}
			// 입력한 값이 없거나 공백을 넣었을 때
			if(now_password.isEmpty() || now_password == null) {
				errors.put("now_password", true);
			}
			// 입력한 값이 없거나 공백을 넣었을 때
			if(new_password.isEmpty() || new_password == null) {
				errors.put("new_password", true);
			}
			// 입력한 값이 없거나 공백을 넣었을 때
			if(new_password_confirm.isEmpty() || new_password_confirm == null) {
				errors.put("new_password_confirm", true);
			}
			// 입력한 비밀번호가 현재 비밀번호와 같지 않을 때
			if(!loginUser.getPassword().equals(now_password)) {
				throw new NowPasswordNotMatchException("입력하신 비밀번호가 맞지 않습니다.");
			}
			// 새 비밀번호 확인의 값과 새 비밀번호의 값이 일치하지 않을 때
			if(!new_password.equals(new_password_confirm)) {
				throw new ComfirmPasswordNotMatchException("새 비밀번호 확인가 일치하지 않습니다.");
			}
			// 새 비밀번호의 값이 현재 비밀번호와 같을 때
			if(new_password.equals(now_password)) {
				throw new samePasswordException("새 비밀번호의 값이 현재 비밀번호와 같습니다.");
			}
			
			ChangePasswordService changePassword = ChangePasswordService.getInstance();
			User user = changePassword.changePwd(new_password, loginUser.getLoginId());
			
			loginUser.setPassword(user.getPassword());
			
			req.getSession().setAttribute("loginUser", null);
			
			Map<String, String> success = new HashMap<String, String>();
			req.setAttribute("success", success);

			success.put("successCode", "changePassword");
			success.put("from", "/user/login");
			return SUCCESS_PAGE;
			
		}catch(SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/user/changePassword?userId="+loginUser.getUserId());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/user/changePassword?userId="+loginUser.getUserId());
		} catch (ComfirmPasswordNotMatchException e) {
			e.printStackTrace();
			error.put("errorCode", "ConfirmPasswordNotMatch");
			error.put("from", "/user/changePassword?userId="+loginUser.getUserId());
		} catch (samePasswordException e) {
			e.printStackTrace();
			error.put("errorCode", "SamePassword");
			error.put("from", "/user/changePassword?userId="+loginUser.getUserId());
		} catch (NowPasswordNotMatchException e) {
			e.printStackTrace();
			error.put("errorCode", "NowPasswordNotMatch");
			error.put("from", "/user/changePassword?userId="+loginUser.getUserId());
		}
		return ERROR_PAGE;
	}

}
