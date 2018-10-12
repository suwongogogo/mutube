package Admin.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Exception.YourNotAdminException;
import Admin.Service.UserPageINF;
import Admin.Service.UserManagementService;
import Handler.CommandHandler;
import User.Model.User;

public class UserManagementHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/admin/userListView.jsp";

	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			User loginUser = (User) req.getSession().getAttribute("loginUser");

			if (loginUser!=null && loginUser.isAuthority() == false) {
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
			resp.sendRedirect(req.getContextPath()+"/Main.jsp");
			return null;
		}
	}

}
