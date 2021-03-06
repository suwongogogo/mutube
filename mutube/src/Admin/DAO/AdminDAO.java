package Admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Notice.Model.Notice;
import Post.Model.Post;
import Post.Model.Writer;
import User.Model.User;

public class AdminDAO {
	private static AdminDAO instance = new AdminDAO();

	private AdminDAO() {
	}

	public static AdminDAO getInstance() {
		return instance;
	}

	public List<User> userList(Connection conn, int startrow, int size) throws SQLException {
		String sql = "select userId, loginId, cast(aes_decrypt(unhex(password),'mutube') as char(50)) as password, email, name, register_date, authority"
				+ " from user order by userId desc limit ?, ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, startrow);
			pst.setInt(2, size);
			try (ResultSet rs = pst.executeQuery()) {
				List<User> userList = new ArrayList<>();
				while (rs.next()) {
					userList.add(new User(rs.getInt("userId"), rs.getString("loginId"), rs.getString("password"),
							rs.getString("email"), rs.getString("name"),
							rs.getTimestamp("register_date").toLocalDateTime(), rs.getBoolean("authority")));
				}

				return userList;
			}
		}
	}

	public List<Post> getPostList(Connection conn, int startRow, int size) throws SQLException {
		String query = "select  * from Post order by postId desc limit ?, ?";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, startRow);
			pst.setInt(2, size);
			try (ResultSet rs = pst.executeQuery()) {
				List<Post> postList = new ArrayList<Post>();
				while (rs.next()) {
					postList.add(new Post(rs.getInt("postId"), new Writer(rs.getInt("userId"), rs.getString("name")),
							rs.getString("title"), rs.getString("genre"), rs.getString("country"),
							rs.getString("instrument"), rs.getTimestamp("write_date").toLocalDateTime(),
							rs.getTimestamp("update_date").toLocalDateTime(), rs.getInt("views"),
							rs.getBoolean("able")));
				}
				return postList;
			}
		}
	}

	public List<Notice> getNoticeList(Connection conn, int startRow, int size) throws SQLException {
		String query = "select  * from notice order by noticeId desc limit ?, ?";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, startRow);
			pst.setInt(2, size);
			try (ResultSet rs = pst.executeQuery()) {
				List<Notice> noticeList = new ArrayList<Notice>();
				while (rs.next()) {
					noticeList.add(
							new Notice(rs.getInt("noticeId"), new Writer(rs.getInt("userId"), rs.getString("name")),
									rs.getString("title"), rs.getTimestamp("write_date").toLocalDateTime(),
									rs.getTimestamp("update_date").toLocalDateTime(), rs.getInt("views"),
									rs.getBoolean("able")));
				}
				return noticeList;
			}
		}
	}

	public int getUserCount(Connection conn) throws SQLException {
		String sql = "select count(*) from user";
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			if (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
			return 0;
		}
	}

	public int getPostCount(Connection conn) throws SQLException {
		String query = "select count(*) from post";
		int cnt = 0;
		try (Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				cnt = rs.getInt(1);

			}
		}
		return cnt;
	}

	public int getNoticeCount(Connection conn) throws SQLException {
		String query = "select count(*) from notice";
		int cnt = 0;
		try (Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				cnt = rs.getInt(1);

			}
		}
		return cnt;
	}

	public int deleteUser(Connection conn, int userId) throws SQLException {
		String sql = "delete from user where userId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, userId);
			return pst.executeUpdate();
		}
	}

	public int deletePost(Connection conn, int postId) throws SQLException {
		String sql = "delete from post where postid = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postId);
			return pst.executeUpdate();
		}
	}

	public int deletePostContent(Connection conn, int postId) throws SQLException {
		String sql = "delete from post_content where postid = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postId);
			return pst.executeUpdate();
		}
	}

	public int deletePostComment(Connection conn, int postId) throws SQLException {
		String sql = "delete from post_comment where postid = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, postId);
			return pst.executeUpdate();
		}
	}
	
	public int deleteNotice(Connection conn, int noticeId) throws SQLException {
		String sql = "delete from notice where noticeId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, noticeId);
			return pst.executeUpdate();
		}
	}
}
