package Notice.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.ValueIsEmptyException;
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
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
		try {
			System.out.println("하이");
			WriteNoticeCommentService writeNoticeComment = WriteNoticeCommentService.getInstance();
			int noticeId = 0;
			if (req.getParameter("noticeId") != null) {
				noticeId = Integer.parseInt(req.getParameter("noticeId"));
			}
			if (noticeId == 0) {
				throw new ValueIsEmptyException("NoticeId 의 값이 올바르지 않습니다.");
			}
			int pageNum = 0;
			if (req.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}
			if (pageNum == 0) {
				throw new ValueIsEmptyException("PageNum 의 값이 올바르지 않습니다.");
			}
			String comment = "";
			if (req.getParameter("comment") != null) {
				comment = req.getParameter("comment");
			}
			if (req.getParameter("comment").equals("")) {
				throw new ValueIsEmptyException("comment 의 값이 올바르지 않습니다.");
			}

			User loginUser = (User) req.getSession().getAttribute("loginUser");

			Notice notice = writeNoticeComment.selectById(noticeId);
			if (loginUser == null) {
				throw new UserNotFoundException("로그인하지 않으셨습니다.");
			}

			NoticeComment noticeComment = new NoticeComment(notice.getNoticeId(), loginUser.getUserId(), comment);

			writeNoticeComment.writeNoticeComment(noticeComment);

			resp.sendRedirect(req.getContextPath() + "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);

		} catch (FailWriteCommentException e) {
			e.printStackTrace();
			error.put("errorCode", "FailWriteComment");
			error.put("from", "/notice/readNotice");
		} catch (PostNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PostNotFound");
			error.put("from", "/notice/readNotice");
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "UserNotFound");
			error.put("from", "/notice/readNotice");
		} catch (ValueIsEmptyException e) {
			e.printStackTrace();
			error.put("errorCode", "ValueIsEmpty");
			error.put("from", "/notice/readNotice");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
		}
		return null;
	}

}
