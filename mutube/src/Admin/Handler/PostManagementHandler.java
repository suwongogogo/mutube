package Admin.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Exception.YourNotAdminException;
import Admin.Service.PostManagementService;
import Admin.Service.PostPageINF;
import Admin.Service.UserManagementService;
import Admin.Service.UserPageINF;
import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class PostManagementHandler implements CommandHandler {
	private static final String ERROR_PAGE = "/error.jsp";
	private static final String FORM_VIEW = "/WEB-INF/admin/postListView.jsp";

	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);

		try {
			User loginUser = (User) req.getSession().getAttribute("loginUser");

			if (loginUser.isAuthority() == false) {
				throw new YourNotAdminException("권한이 없습니다.");
			}

			PostManagementService managementService = PostManagementService.getInstance();
			String pageNumStr = req.getParameter("pageNum");

			int pageNum = 1;
			if (pageNumStr != null) {
				pageNum = Integer.parseInt(pageNumStr);
			}
			PostPageINF postPageINF = managementService.getPostList(pageNum);

			req.setAttribute("postPageINF", postPageINF);

			return FORM_VIEW;

		} catch (YourNotAdminException e) {
			e.printStackTrace();
			error.put("errorCode", "notAdmin");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/admin/postManagement");
		}
		return ERROR_PAGE;
	}
}
