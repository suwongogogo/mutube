package Notice.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Model.Notice;
import Notice.Model.NoticeComment;
import Notice.Service.WriteNoticeCommentService;
import Post.Exception.FailWriteCommentException;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostComment;
import Post.Service.WriteCommentService;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class WriteNoticeCommentHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("하이");
		WriteNoticeCommentService writeNoticeComment = WriteNoticeCommentService.getInstance();
		int noticeId = Integer.parseInt(req.getParameter("noticeId"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String comment = req.getParameter("comment");

		User loginUser = (User) req.getSession().getAttribute("loginUser");
		try {

			Notice notice = writeNoticeComment.selectById(noticeId);
			if (loginUser == null) {
				throw new UserNotFoundException("로그인하지 않으셨습니다.");
			}

			NoticeComment noticeComment = new NoticeComment(notice.getNoticeId(), loginUser.getUserId(), comment);

			writeNoticeComment.writeNoticeComment(noticeComment);

			resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);

		} catch (FailWriteCommentException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
		} catch (PostNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/notice/noticeList");
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/user/login");
		}
		return null;
	}

}
