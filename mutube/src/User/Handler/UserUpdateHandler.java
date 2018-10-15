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
import User.Exception.SameValueException;
import User.Exception.SameloginIdException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.UserUpdateService;

public class UserUpdateHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/user/userUpdateForm.jsp";
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

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));
		String loginId = (String) req.getParameter("loginId");
		String name = (String) req.getParameter("name");
		String email = (String) req.getParameter("email");

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		User loginUser = (User) req.getSession().getAttribute("loginUser");

		if (loginId.isEmpty() || loginId == null) {
			errors.put("loginid", true);
		}
		if (name.isEmpty() || name == null) {
			errors.put("name", true);
		}
		if (email.isEmpty() || email == null) {
			errors.put("email", true);
		}
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		User savedUser = new User();
		savedUser.setUserId(userId);
		savedUser.setLoginId(loginId);
		savedUser.setName(name);
		savedUser.setEmail(email);
		
		try {
			
			UserUpdateService updateService = UserUpdateService.getInstance();
			updateService.update(savedUser, userId);

			req.getSession().setAttribute("loginUser", savedUser);
			
			Map<String, String> success = new HashMap<String, String>();
			req.setAttribute("success", success);

			success.put("successCode", "updateUser");
			success.put("from", "/myPage.jsp");
			return SUCCESS_PAGE;
			
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/myPage.jsp");
		} 
		return ERROR_PAGE;
	}

}
