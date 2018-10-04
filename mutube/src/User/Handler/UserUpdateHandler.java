package User.Handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Connection.ConnectionProvider;
import Handler.CommandHandler;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.UserUpdateService;

public class UserUpdateHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/userUpdateForm.jsp";
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

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, UserNotFoundException {
	
		UserDAO userDAO = UserDAO.getInstance();
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			User user = userDAO.selectByUserId(conn, userId);
		}
		
		req.setAttribute("user", userId);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, UserNotFoundException {
		
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		User user = new User();
		
		user.setLoginId(loginId);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		if(loginId.isEmpty() || loginId == null) {
			errors.put("loginid", true);
		}
		if(password.isEmpty() || password == null) {
			errors.put("password", true);
		}
		if(name.isEmpty() || name == null) {
			errors.put("name", true);
		}
		if(email.isEmpty() || email == null) {
			errors.put("email", true);
		}
		
		try {
			UserUpdateService updateService = UserUpdateService.getInstance();
			updateService.update(user);
			
			// 업데이트를 완료하면 다시 내 정보를 볼 수 있는 MyPage로.
			return "/WEB-INF/view/myPage.jsp";
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException("없는 유저입니다.");
		}
		
	}
		
}
