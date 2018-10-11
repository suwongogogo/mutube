package User.Handler;

import java.io.IOException;
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
	private static final String FORM_VIEW = "/WEB-INF/view/user/userUpdateForm.jsp";
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
		System.out.println("수정폼");
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		System.out.println("수정 시작");
		
		int userId = Integer.parseInt(req.getParameter("userId"));
		String loginId = (String)req.getParameter("loginId");
		String name = (String)req.getParameter("name");
		String email = (String)req.getParameter("email");

		User savedUser = new User();
		savedUser.setUserId(userId);
		savedUser.setLoginId(loginId);
		savedUser.setName(name);
		savedUser.setEmail(email);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		if(loginId.isEmpty() || loginId == null) {
			errors.put("loginid", true);
		}
		if(name.isEmpty() || name == null) {
			errors.put("name", true);
		}
		if(email.isEmpty() || email == null) {
			errors.put("email", true);
		}
		
		try {
			UserUpdateService updateService = UserUpdateService.getInstance();
			updateService.update(savedUser, userId);
			
			req.getSession().setAttribute("loginUser", savedUser);
			
			System.out.println(savedUser.getLoginId());
			
			resp.sendRedirect(req.getContextPath()+"/myPage.jsp");
		}catch(UserNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
		
}
