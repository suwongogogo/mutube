package Admin.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.Service.DeleteNoticeService;
import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import User.Exception.DeleteFailException;


public class DeleteNoticeHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
		System.out.println("In");
		try {
			int noticeId = 0;
			if(req.getParameter("noticeId") != null) {
				noticeId = Integer.parseInt(req.getParameter("noticeId"));
			}
			if(noticeId == 0) {
				throw new NoticeNotFoundException("공지를 찾을 수 없습니다.");
			}
			
			DeleteNoticeService deleteNotice = DeleteNoticeService.getInstance();
			int Cnt = deleteNotice.deleteNotice(noticeId);
			
			if(Cnt < 0) {
				throw new DeleteFailException("삭제를 실패 하였습니다.");
			}
			
			resp.sendRedirect(req.getContextPath() + "/admin/noticeManagement");
			return null;
			
		}catch(SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/admin/noticeListView.jsp");
		}catch(DeleteFailException e) {
			e.printStackTrace();
			error.put("errorCode", "DeleteFail");
			error.put("from", "/admin/noticeListView.jsp");
		}catch(NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "NoticeNotFound");
			error.put("from", "/admin/noticeListView.jsp");
		}
		return null;
	}
	
}
