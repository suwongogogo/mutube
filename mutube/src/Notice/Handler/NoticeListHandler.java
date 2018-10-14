package Notice.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.PageNotFoundException;
import Notice.Model.NoticePage;
import Notice.Service.NoticeListService;

public class NoticeListHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		try {
			NoticeListService noticeService = NoticeListService.getInstance();
			String pageNumStr = req.getParameter("pageNum");
			int pageNum = 1;
			if (pageNumStr != null) {
				pageNum = Integer.parseInt(pageNumStr);
			}
			if (pageNum == 0) {
				throw new PageNotFoundException("페이지를 찾을 수 없습니다.");
			}
			NoticePage noticePage = noticeService.getNoticePage(pageNum);
			req.setAttribute("noticePage", noticePage);

			return "/WEB-INF/view/notice/noticeList.jsp";
		} catch (PageNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PageNotFound");
			error.put("from", "notice/noticeList");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
		}
		return null;
	}
}
