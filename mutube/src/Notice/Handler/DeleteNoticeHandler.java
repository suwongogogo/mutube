package Notice.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import Notice.Service.DeleteNoticeService;


public class DeleteNoticeHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		int noticeId = 0;
		try {
		if(req.getParameter("noticeId") != null) {
			noticeId = Integer.parseInt(req.getParameter("noticeId"));
		}
		
		if(noticeId == 0) {
			throw new NoticeNotFoundException("해당 공지사항이 없습니다.");
		}
		
		DeleteNoticeService deleteNoticeService = DeleteNoticeService.getInstance();
		deleteNoticeService.deleteNotice(noticeId);
		
		resp.sendRedirect(req.getContextPath() + "/notice/notice");
		
		}catch(NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "DeleteNoticeFail");
			error.put("from", "/notice/notice");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
		}
		return null;
	}
	
}
