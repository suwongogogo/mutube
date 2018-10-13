package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public int insert(Connection conn, PostContent postContent) throws SQLException {
		String sql = "insert into post_content(postId ,content, video_link, imageName) values(?, ?, ?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postContent.getPostId());
			pst.setString(2, postContent.getContent());
			pst.setString(3, postContent.getVideo_link());
			pst.setString(4, postContent.getImageNamesStr());
			return pst.executeUpdate();

		}
	}

	public PostContent selectByPostId(Connection conn, int postId) throws SQLException {
		PostContent postContent = null;
		String sql = "select * from post_content where postId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					postContent = new PostContent(rs.getInt("postId"), rs.getString("content"), rs.getString("video_link"), rs.getString("imageName"));
				}
			}
		}

		return postContent;
	}

	public int update(Connection conn, PostContent postContent, int postId) throws SQLException {
		String sql = "update post_content set content = ?, video_link= ? where postId = ?";
		System.out.println("test in update");
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, postContent.getContent());
			pst.setString(2, postContent.getVideo_link());
			pst.setInt(3, postId);
			return pst.executeUpdate();
		}
	}
	
	public int updateWithImage(Connection conn, PostContent postContent, int postId) throws SQLException {
		String sql = "update post_content set content = ?, video_link= ?, imageName = ? where postId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, postContent.getContent());
			pst.setString(2, postContent.getVideo_link());
			pst.setString(3, postContent.getImageNamesStr());
			pst.setInt(4, postId);
			return pst.executeUpdate();
		}
	}
	
}
