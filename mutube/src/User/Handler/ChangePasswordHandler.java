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
		System.out.println("비밀번호 수정폼");
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		ChangePasswordService changePassword = ChangePasswordService.getInstance();
		User user = changePassword.selectByUserId(userId);
		
		req.setAttribute("user", user);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			System.out.println("비밀번호 변경 실행");
			String now_password = req.getParameter("now_password");
			String new_password = req.getParameter("new_password");
			String new_password_confirm = req.getParameter("new_password_confirm");
			User sessionUser = (User)req.getSession().getAttribute("loginUser");
			
			System.out.println(sessionUser.getLoginId());
			
			Map<String, Boolean> errors = new HashMap<String, Boolean>();
			if(sessionUser == null) {
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
				throw new NewPasswordNotMatchException("입력하신 새 비밀번호가 일치하지 않습니다.");
			}
			
			ChangePasswordService changePassword = ChangePasswordService.getInstance();
			User user = changePassword.changePwd(new_password, sessionUser.getLoginId());
			
			sessionUser.setPassword(user.getPassword());
			
			resp.sendRedirect(req.getContextPath()+"/myPage.jsp");
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}catch(NewPasswordNotMatchException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath()+"/user/changePassword");
		}
		return null;
	}

}
