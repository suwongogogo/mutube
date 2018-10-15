package Notice.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.DeleteNoticeFailException;
import Notice.Exception.NoticeNotFoundException;
import Notice.Service.DeleteNoticeService;


public class DeleteNoticeHandler implements CommandHandler{
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		int deleteCnt = deleteNoticeService.deleteNotice(noticeId);
		
		if(deleteCnt < 0 ) {
			throw new DeleteNoticeFailException("삭제 실패");
		}
		
		resp.sendRedirect(req.getContextPath() + "/notice/notice");
		
		}catch(NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "NoticeNotFound");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		} catch(DeleteNoticeFailException e) {
			e.printStackTrace();
			error.put("errorCode", "DeleteNoticeFail");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		}
		return null;
	}
	
}
