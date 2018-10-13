package Notice.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Service.DeleteNoticeCommentService;
import Post.Exception.CommentNotFoundException;
import Post.Service.DeleteCommentService;

public class DeleteNoticeCommentHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int commentId = 0;
		int noticeId = 0;
		int pageNum = 1;
		if(req.getParameter("commentId") != null) {
			commentId = Integer.parseInt(req.getParameter("commentId"));
		}
		if(req.getParameter("noticeId") != null) {
			noticeId = Integer.parseInt(req.getParameter("noticeId"));
		}
		if(req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}

		try {
			DeleteNoticeCommentService deleteNoticeCommentService = DeleteNoticeCommentService.getInstance();
			deleteNoticeCommentService.deleteNoticeComment(commentId);
			
			resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId="+noticeId+"&pageNum="+pageNum);
		}catch(CommentNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId="+noticeId+"&pageNum="+pageNum);
			return null;
		}
		return null;
	}
}
