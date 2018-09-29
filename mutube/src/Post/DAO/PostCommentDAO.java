package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Post.Model.PostComment;

public class PostCommentDAO {
	private static PostCommentDAO instance = new PostCommentDAO();

	private PostCommentDAO() {
	};

	public static PostCommentDAO getInstance() {
		return instance;
	}

	public PostComment insert(Connection conn, PostComment postComment) throws SQLException {
		String sql = "insert into post_comment(postId, comment) values(?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postComment.getPostId());
			pst.setString(2, postComment.getComment());
			int insertCnt = pst.executeUpdate();

			if (insertCnt > 0) {
				return postComment;
			} else {
				return null;
			}
		}
	}
}
