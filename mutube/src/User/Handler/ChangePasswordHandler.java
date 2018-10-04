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
		System.out.println("수정폼");
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		ChangePasswordService changePassword = ChangePasswordService.getInstance();
		User user = changePassword.selectByUserId(userId);
		
		req.setAttribute("user", user);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		
		String password = (String)req.getParameter("password");
		String loginId = (String)req.getParameter("loginId");
		
		User user = new User(loginId, password);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		if(user.getLoginId().isEmpty() || user.getLoginId() == null) {
			errors.put("loginId", true);
		}
		if(user.getPassword().isEmpty() || user.getPassword() == null) {
			errors.put("password", true);
		}
		
		try {
			ChangePasswordService changePassword = ChangePasswordService.getInstance();
			changePassword.changePwd(password, loginId);
			
		
			return "/WEB-INF/view/myPage.jsp";
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
