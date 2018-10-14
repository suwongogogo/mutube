package Notice.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.DeleteNoticeCommentException;
import Notice.Service.DeleteNoticeCommentService;
import Post.Exception.CommentNotFoundException;
import Post.Service.DeleteCommentService;

public class DeleteNoticeCommentHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		int commentId = 0;
		int noticeId = 0;
		int pageNum = 1;

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);

		if (req.getParameter("commentId") != null) {
			commentId = Integer.parseInt(req.getParameter("commentId"));
		}
		if (req.getParameter("noticeId") != null) {
			noticeId = Integer.parseInt(req.getParameter("noticeId"));
		}
		if (req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}

		try {
			DeleteNoticeCommentService deleteNoticeCommentService = DeleteNoticeCommentService.getInstance();
			int deleteCnt = deleteNoticeCommentService.deleteNoticeComment(commentId);

			if(deleteCnt > 0) {
				throw new DeleteNoticeCommentException("공지 댓글 삭제 실패");
			}
			
			resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
		} catch (CommentNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "CommentNotFound");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
		} catch (DeleteNoticeCommentException e) {
			e.printStackTrace();
			error.put("errorCode", "DeleteNoticeComment");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
		}
		return null;
	}
}
