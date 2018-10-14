package Notice.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import Notice.Exception.ValueIsEmptyException;
import Notice.Exception.WriteNoticeCommentFailException;
import Notice.Model.Notice;
import Notice.Model.NoticeComment;
import Notice.Service.WriteNoticeCommentService;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostComment;
import Post.Service.WriteCommentService;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class WriteNoticeCommentHandler implements CommandHandler {
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
		String comment = "";
		if (req.getParameter("comment") != null) {
			comment = req.getParameter("comment");
		}
		int pageNum = 0;
		if (req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		int noticeId = 0;
		if (req.getParameter("noticeId") != null) {
			noticeId = Integer.parseInt(req.getParameter("noticeId"));

		}
		try {
			System.out.println("하이");
			WriteNoticeCommentService writeNoticeComment = WriteNoticeCommentService.getInstance();

			if (noticeId == 0) {
				throw new ValueIsEmptyException("NoticeId 의 값이 올바르지 않습니다.");
			}
			if (pageNum == 0) {
				throw new ValueIsEmptyException("PageNum 의 값이 올바르지 않습니다.");
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

		} catch (WriteNoticeCommentFailException e) {
			e.printStackTrace();
			error.put("errorCode", "WriteNoticeCommentFail");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
			return ERROR_PAGE;
		} catch (NoticeNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "NoticeNotFound");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
			return ERROR_PAGE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "UserNotFound");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
			return ERROR_PAGE;
		} catch (ValueIsEmptyException e) {
			e.printStackTrace();
			error.put("errorCode", "ValueIsEmpty");
			error.put("from", "/notice/readNotice?noticeId=" + noticeId + "&pageNum=" + pageNum);
			return ERROR_PAGE;
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/notice/notice");
			return ERROR_PAGE;
		}
		return null;
	}

}
