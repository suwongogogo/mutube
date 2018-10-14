package Notice.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import Notice.Exception.PageNotFoundException;
import Notice.Model.NoticeCommentPage;
import Notice.Model.NoticeData;
import Notice.Service.NoticeCommentListService;
import Notice.Service.ReadNoticeService;
import Post.Model.CommentPage;
import Post.Service.CommentListService;

public class ReadNoticeHandler implements CommandHandler {
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		try {
			int noticeId = 0;
			int pageNum = 1;
			if (req.getParameter("noticeId") != null) {
				noticeId = Integer.parseInt(req.getParameter("noticeId"));
			}
			if (req.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}
			if (noticeId == 0) {
				throw new NoticeNotFoundException("해당 공지사항을 찾을 수 없습니다.");
			}
			if (pageNum == 0) {
				throw new PageNotFoundException("페이지를 찾을 수 없습니다.");
			}
			ReadNoticeService readNoticeService = ReadNoticeService.getInstance();
			NoticeData noticeData = readNoticeService.getNotice(noticeId);

			String repleaceNoticeContent = noticeData.getNoticeContent().getContent().replaceAll("<", "&lt")
					.replaceAll(">", "&gt").replaceAll(" ", "&nbsp").replaceAll("\n", "<br>");
			noticeData.getNoticeContent().setContent(repleaceNoticeContent);

			NoticeCommentListService noticeCommentList = NoticeCommentListService.getInstance();
			NoticeCommentPage noticeCommentPage = noticeCommentList.commentList(pageNum, noticeId);
			noticeData.setNoticeCommentPage(noticeCommentPage);

			req.setAttribute("noticeData", noticeData);

			return "/WEB-INF/view/notice/readNotice.jsp";
		} catch (NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "NoticeNotFound");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		} catch (PageNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PageNotFound");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		}
	}
}
