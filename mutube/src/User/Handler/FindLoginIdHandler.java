package User.Handler;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.FindLoginIdService;

public class FindLoginIdHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/user/findLoginIdForm.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		FindLoginIdService findService = FindLoginIdService.getInstance();

		String name = req.getParameter("name");
		String email = req.getParameter("email");

		User user = new User();
		user.setName(name);
		user.setEmail(email);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if (name == null || name.isEmpty()) {
			errors.put("name", true);
		}
		if (email == null || email.isEmpty()) {
			errors.put("email", true);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			List<User> userList = findService.checkId(name, email);
			if(userList == null) {
				throw new UserNotFoundException("유저를 찾을 수 없음");
			}
			req.setAttribute("loginIdList", userList);
			
			return "/WEB-INF/view/user/findIdSuccess.jsp";
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/user/findLoginId");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/user/findLoginId");
		}
	
		return ERROR_PAGE;
	}

}
