package Notice.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Notice.DAO.NoticeCommentDAO;
import Post.DAO.PostCommentDAO;
import Post.Exception.CommentNotFoundException;
import Post.Service.DeleteCommentService;

public class DeleteNoticeCommentService {
	private static DeleteNoticeCommentService instance = new DeleteNoticeCommentService();

	private DeleteNoticeCommentService() {
	}

	public static DeleteNoticeCommentService getInstance() {
		return instance;
	}

	public void deleteNoticeComment(int commentId) throws CommentNotFoundException, SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {

			System.out.println(commentId);
			NoticeCommentDAO noticeCommentDAO = NoticeCommentDAO.getInstance();
			int count = noticeCommentDAO.commentDelete(conn, commentId);

			if (count == 0) {
				conn.rollback();
				throw new CommentNotFoundException("댓글을 찾을 수 없음");
			}
		}
	}
}
