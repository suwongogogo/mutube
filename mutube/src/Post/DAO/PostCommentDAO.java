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
		String sql = "insert into post_comment(postId, userId , comment, write_date) values(?, ?, ?, now())";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postComment.getPostId());
			pst.setInt(2, postComment.getUserId());
			pst.setString(3, postComment.getComment());
			int insertCnt = pst.executeUpdate();

			if (insertCnt > 0) {
				return postComment;
			} else {
				return null;
			}
		}
	}
	
	public int update(Connection conn,String comment , int postId) throws SQLException {
		String sql = "update post_comment set comment = ? where postId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, comment);
			pst.setInt(2, postId);
			return pst.executeUpdate();
		}
	}
}
