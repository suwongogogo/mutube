package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;import javax.security.auth.callback.ConfirmationCallback;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.jdbc.exceptions.SQLError;

import Handler.CommandHandler;
import User.Exception.ComfirmPasswordNotMatchException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.DeleteUserComfirmByPasswordService;

public class DeleteUserComfirmByPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/user/deleteUserComfirmByPassword.jsp";
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
		System.out.println("회원 탈퇴 비밀번호");
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("회원 탈퇴 비밀번호 서밋");
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		int userId = 0;
		if (req.getParameter("userId") != null) {
			userId = Integer.parseInt(req.getParameter("userId"));
		}

		String comfirmPassword = "";
		if (req.getParameter("comfirmPassword") != null) {
			comfirmPassword = req.getParameter("comfirmPassword");
		}
	
		try {
			DeleteUserComfirmByPasswordService deleteUserService = DeleteUserComfirmByPasswordService.getInstance();
			User comfirmUser = deleteUserService.getUser(userId);

			System.out.println("비번 " + comfirmUser.getPassword());
			
			if (!comfirmUser.getPassword().equals(comfirmPassword)) {
				throw new ComfirmPasswordNotMatchException("비밀번호가 일치하지 않습니다. ");
			}
			
			if (userId == 0) {
				throw new UserNotFoundException("유저를 찾을 수 없습니다.");
			}

			return "/user/delete";

		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/myPage.jsp");
		} catch (ComfirmPasswordNotMatchException e) {
			e.printStackTrace();
			error.put("errorCode", "ComfirmPasswordNotMatch");
			error.put("from", "/myPage.jsp");
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "UserNotFound");
			error.put("from", "/myPage.jsp");
		}
		return ERROR_PAGE;
	}
}
