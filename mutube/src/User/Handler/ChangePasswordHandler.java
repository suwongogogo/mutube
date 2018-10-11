package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
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
			String password = req.getParameter("password");
			User sessionUser = (User)req.getSession().getAttribute("loginUser");
			
			System.out.println(sessionUser.getLoginId());
			
			Map<String, Boolean> errors = new HashMap<String, Boolean>();
			if(sessionUser == null) {
				throw new UserNotFoundException("없는 유저입니다.");
			}
			if(password.isEmpty() || password == null) {
				errors.put("password", true);
			}
			
			ChangePasswordService changePassword = ChangePasswordService.getInstance();
			User user = changePassword.changePwd(password, sessionUser.getLoginId());
			
			sessionUser.setPassword(user.getPassword());
			
			resp.sendRedirect(req.getContextPath()+"/myPage.jsp");
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
