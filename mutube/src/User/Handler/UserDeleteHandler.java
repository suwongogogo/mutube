package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Exception.DeleteFailException;
import User.Model.User;
import User.Service.UserDeleteService;

public class UserDeleteHandler implements CommandHandler {
	private static final String ERROR_PAGE = "/error.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User loginUser = (User) req.getSession().getAttribute("loginUser");

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		try {
			UserDeleteService deleteService = UserDeleteService.getInstance();
			int cnt = deleteService.delete(loginUser.getUserId());
			if (cnt == 0) {
				throw new DeleteFailException("알 수 없는 사용자");
			}
			req.getSession().setAttribute("loginUser", null);

			resp.sendRedirect(req.getContextPath() + "/Main.jsp");
			return null;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "/myPage.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/myPage.jsp");
		} catch (DeleteFailException e) {
			e.printStackTrace();
			error.put("errorCode", "deleteFail");
			error.put("from", "/myPage.jsp");
		}
		return ERROR_PAGE;
	}

}
