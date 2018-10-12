package Admin.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Exception.YourNotAdminException;
import Admin.Service.PostManagementService;
import Admin.Service.PostPageINF;
import Admin.Service.UserManagementService;
import Admin.Service.UserPageINF;
import Handler.CommandHandler;
import User.Model.User;

public class PostManagementHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/admin/postListView.jsp";

	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			User loginUser = (User) req.getSession().getAttribute("loginUser");

			if ( loginUser!=null && loginUser.isAuthority() == false) {
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
			resp.sendRedirect(req.getContextPath()+"/Main.jsp");
			throw new YourNotAdminException("권한이 없습니다.");
		}
	}
}
