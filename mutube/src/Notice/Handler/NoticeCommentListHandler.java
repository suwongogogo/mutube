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
import Post.Model.CommentPage;
import Post.Model.PostData;
import Post.Service.CommentListService;

public class NoticeCommentListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		int noticeId = 0;
		if (req.getParameter("noticeId") != null) {
			noticeId = Integer.parseInt(req.getParameter("noticeId"));
		}
		int pageNum = 1;
		String pageNumStr = req.getParameter("pageNum");
		if (pageNumStr != null) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		try {
			if (noticeId == 0) {
				resp.sendRedirect(req.getContextPath() + "/notice/notice");
				throw new NoticeNotFoundException("올바르지 않은 게시글 번호");
			}
			if (pageNum == 0) {
				throw new PageNotFoundException("페이지를 찾을 수 없습니다.");
			}

			NoticeCommentListService noticeCommentList = NoticeCommentListService.getInstance();
			NoticeCommentPage noticeCommentPage = noticeCommentList.commentList(pageNum, noticeId);
			NoticeData noticeData = new NoticeData();
			noticeData.setNoticeCommentPage(noticeCommentPage);

			resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);

		} catch (NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "NoticeNotFound");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
		} catch (PageNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PageNotFound");
			error.put("from", "/notice/notice");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
		}
		return null;
	}
}
