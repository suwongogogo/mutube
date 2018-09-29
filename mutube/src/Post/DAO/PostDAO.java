package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Post.Model.Post;

public class PostDAO {
	private static PostDAO instance = new PostDAO();

	private PostDAO() {
	};

	public static PostDAO getInstance() {
		return instance;
	}

	public Post insert(Connection conn, Post post) throws SQLException {
		String sql = "insert into post(userId, title, genre, musician, instrument, write_date) values(?,?,?,?,?,now())";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, post.getWriter().getUserId());
			pst.setString(2, post.getTitle());
			pst.setString(3, post.getGenre());
			pst.setString(4, post.getMusician());
			pst.setString(5, post.getInstrument());
			int writeCnt = pst.executeUpdate();

			if (writeCnt > 0) {
				return post;
			} else {
				return null;
			}
		}
	}
	
	public int update(Connection conn,String title , int postId) throws SQLException {
		String sql = "update post set title = ? where postId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, title);
			pst.setInt(2, postId);
			return pst.executeUpdate();
		}
	}

}
