package Notice.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Notice.DAO.NoticeCommentDAO;
import Notice.DAO.NoticeDAO;
import Notice.Exception.NoticeNotFoundException;
import Notice.Exception.WriteNoticeCommentFailException;
import Notice.Model.Notice;
import Notice.Model.NoticeComment;
import Post.DAO.PostCommentDAO;
import Post.DAO.PostDAO;
import Post.Exception.WriteCommentFailException;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostComment;
import Post.Service.WriteCommentService;

public class WriteNoticeCommentService {
	private static WriteNoticeCommentService instance = new WriteNoticeCommentService();

	private WriteNoticeCommentService() {
	}

	public static WriteNoticeCommentService getInstance() {
		return instance;
	}

	public void writeNoticeComment(NoticeComment noticeComment) throws WriteNoticeCommentFailException, SQLException {
		NoticeCommentDAO noticeCommentDAO = NoticeCommentDAO.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			try {
				NoticeComment savedNoticeComment = noticeCommentDAO.insert(conn, noticeComment);

				if (savedNoticeComment == null) {
					conn.rollback();
					throw new WriteNoticeCommentFailException("댓글 작성 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("");
			}
		}
	}

	public Notice selectById(int noticeId) throws SQLException, NoticeNotFoundException {
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			try {
			Notice notice = noticeDAO.selectById(conn, noticeId);
			if (notice == null) {
				conn.rollback();
				throw new NoticeNotFoundException("게시글을 찾을 수 없음.");
			}

			return notice;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException("");
			}
		}
	}
}
