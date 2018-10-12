package Notice.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Model.NoticePage;
import Notice.Service.NoticeListService;

public class NoticeListHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		NoticeListService noticeService = NoticeListService.getInstance();
		String pageNumStr = req.getParameter("pageNum");
		int pageNum = 1;
		if (pageNumStr != null) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		NoticePage noticePage = noticeService.getNoticePage(pageNum);
		req.setAttribute("noticePage", noticePage);

		return "/WEB-INF/view/notice/noticeList.jsp";
	}
}
