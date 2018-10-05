package User.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Model.User;
import User.Service.ChangePasswordService;

public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/changePassword.jsp";

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

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("비밀번호 변경 실행");
		String password = (String)req.getParameter("password");
		User sessionUser = (User)req.getSession().getAttribute("loginUser");
		
		System.out.println(password);
		
		User user = new User(sessionUser.getLoginId(), password);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		if(user.getLoginId().isEmpty() || user.getLoginId() == null) {
			errors.put("loginId", true);
		}
		if(user.getPassword().isEmpty() || user.getPassword() == null) {
			errors.put("password", true);
		}
		
		try {
			int integerPassword = Integer.parseInt(password);
			ChangePasswordService changePassword = ChangePasswordService.getInstance();
			user = changePassword.changePwd(integerPassword, sessionUser.getLoginId());
			
			req.setAttribute("user", user);
			return "/WEB-INF/view/myPage.jsp";
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
