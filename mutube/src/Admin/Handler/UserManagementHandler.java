package Admin.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Exception.YourNotAdminException;
import Admin.Service.UserPageINF;
import Admin.Service.UserManagementService;
import Handler.CommandHandler;
import User.Model.User;

public class UserManagementHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/admin/userListView.jsp";
	private static final String ERROR_PAGE = "/error.jsp";

	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);

		try {
			User loginUser = (User) req.getSession().getAttribute("loginUser");

			if (loginUser.isAuthority() == false) {
				throw new YourNotAdminException("권한이 없습니다.");
			}

			UserManagementService managementService = UserManagementService.getInstance();
			String pageNumStr = req.getParameter("pageNum");
			int pageNum = 1;
			if (pageNumStr != null) {
				pageNum = Integer.parseInt(pageNumStr);
			}
			UserPageINF userPageINF = managementService.getUserList(pageNum);

			req.setAttribute("userpageINF", userPageINF);

			return FORM_VIEW;
		} catch (YourNotAdminException e) {
			e.printStackTrace();
			error.put("errorCode", "notAdmin");
		} catch (SQLException e) {
			error.put("errorCode", "dbError");
			error.put("from", "/admin/userManagement");
			e.printStackTrace();
		}
		return ERROR_PAGE;
	}

}
