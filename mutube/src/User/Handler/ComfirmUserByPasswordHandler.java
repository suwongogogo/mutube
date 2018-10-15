package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.PasswordNotMatchException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.ConfirmUserByPasswordService;

public class ComfirmUserByPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/user/confirmUserByPassword.jsp";
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

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		try {
			ConfirmUserByPasswordService confirmService = ConfirmUserByPasswordService.getInstance();
			User loginUser = (User) req.getSession().getAttribute("loginUser");
			
			User user = confirmService.confirmUser(loginUser.getLoginId());
			String password = req.getParameter("password");

			if (!user.getPassword().equals(password) && !loginUser.getPassword().equals(password)) {
				throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
			}
			resp.sendRedirect(req.getContextPath() + "/myPage.jsp");
			return null;
			
		} catch (PasswordNotMatchException e) {
			e.printStackTrace();
			error.put("errorCode", "passwordNotMatch");
			error.put("from", "/user/confirmUserByPassword");
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/user/confirmUserByPassword");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/user/confirmUserByPassword");
		}
		return ERROR_PAGE;
	}

}
