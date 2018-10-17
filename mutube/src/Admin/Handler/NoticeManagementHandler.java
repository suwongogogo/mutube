package Admin.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Exception.YourNotAdminException;
import Admin.Service.NoticeManagementService;
import Admin.Service.NoticePageINF;
import Admin.Service.UserManagementService;
import Admin.Service.UserPageINF;
import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import User.Model.User;

public class NoticeManagementHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/admin/noticeListView.jsp";
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
		try {
			User loginUser = null;
			if (req.getSession().getAttribute("loginUser") != null) {
				loginUser = (User) req.getSession().getAttribute("loginUser");
			}

			int noticeId = 0;
			if (req.getParameter("noticeId") != null) {
				noticeId = Integer.parseInt(req.getParameter("noticeId"));
			}

			if (loginUser.isAuthority() == false) {
				throw new YourNotAdminException("권한이 없습니다.");
			}

			if (noticeId < 0) {
				throw new NoticeNotFoundException("공지를 찾을 수 없습니다.");
			}

			String pageNumStr = req.getParameter("pageNum");
			int pageNum = 1;
			if (pageNumStr != null) {
				pageNum = Integer.parseInt(pageNumStr);
			}

			NoticeManagementService noticeManagement = NoticeManagementService.getInstance();
			NoticePageINF noticePageINF = noticeManagement.getNoticeList(pageNum);

			req.setAttribute("noticePageINF", noticePageINF);

			return FORM_VIEW;
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/managementList.jsp");
		} catch (NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "NoticeNotFound");
			error.put("from", "/managementList.jsp");
		} catch (YourNotAdminException e) {
			e.printStackTrace();
			error.put("errorCode", "notAdmin");
			error.put("from", "/Main.jsp");
		}
		return ERROR_PAGE;
	}

}
