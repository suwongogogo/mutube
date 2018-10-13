package Notice.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import Notice.Model.NoticeCommentPage;
import Notice.Model.NoticeData;
import Notice.Service.NoticeCommentListService;
import Post.Model.PostData;
import Post.Service.CommentListService;
import Post.Service.CommentPage;

public class NoticeCommentListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int noticeId = 0;
		if (req.getParameter("noticeId") != null) {
			noticeId = Integer.parseInt(req.getParameter("noticeId"));
		}
		if(noticeId == 0) {
			resp.sendRedirect(req.getContextPath() + "/notice/notice");
			throw new NoticeNotFoundException("올바르지 않은 게시글 번호");
		}
		String pageNumStr = req.getParameter("pageNum");
		int pageNum = 1;
		if (pageNumStr != null) {
			pageNum = Integer.parseInt(pageNumStr);
		}

		NoticeCommentListService noticeCommentList = NoticeCommentListService.getInstance();
		NoticeCommentPage noticeCommentPage = noticeCommentList.commentList(pageNum, noticeId);
		NoticeData noticeData = new NoticeData();
		noticeData.setNoticeCommentPage(noticeCommentPage);

		resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);

		return null;
	}
}
