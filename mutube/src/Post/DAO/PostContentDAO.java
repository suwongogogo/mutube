package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Post.Model.Post;
import Post.Model.PostContent;

public class PostContentDAO {
	private static PostContentDAO instance = new PostContentDAO();

	private PostContentDAO() {
	};

	public static PostContentDAO getInstance() {
		return instance;
	}

	public PostContent insert(Connection conn, PostContent postContent) throws SQLException {
		String sql = "insert into post_content(postId ,content) values(?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postContent.getPostId());
			pst.setString(2, postContent.getContent());
			int insertCnt = pst.executeUpdate();

			if (insertCnt > 0) {
				return postContent;
			} else {
				return null;
			}
		}
	}
}
