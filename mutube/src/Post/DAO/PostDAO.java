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
<<<<<<< HEAD

	public Post insert(Connection conn, Post post) throws SQLException {
		String sql = "insert into post(userId, title, genre, musician, instrument, write_date) values(?,?,?,?,?,now())";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, post.getUserid());
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
=======
	
	
>>>>>>> branch 'master' of https://github.com/suwongogogo/mutube.git
}
