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
import User.Service.UserDeleteService;

public class UserDeleteHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/user/view/userDeleteForm.jsp";
	private static final String ERROR_PAGE = "/error.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("삭제");
		User sessionUser = (User) req.getSession().getAttribute("loginUser");
		User loginUser = (User) req.getSession().getAttribute("loginUser");

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		try {
			UserDeleteService deleteService = UserDeleteService.getInstance();
			int cnt = deleteService.delete(sessionUser.getUserId());

			return "/WEB-INF/user/view/deleteSuccess.jsp";
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/myPage.jsp");
		}
		return null;

	}
}
