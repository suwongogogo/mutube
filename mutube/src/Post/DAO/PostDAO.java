package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Post.Model.Post;
import Post.Model.Writer;

public class PostDAO {
	private static PostDAO instance = new PostDAO();

	private PostDAO() {
	};

	public static PostDAO getInstance() {
		return instance;
	}

	public Post insert(Connection conn, Post post) throws SQLException {
		String sql = "insert into post(userId, name, title, genre, musician, instrument) values(?,?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, post.getWriter().getUserId());
			pst.setString(2, post.getWriter().getName());
			pst.setString(3, post.getTitle());
			pst.setString(4, post.getGenre());
			pst.setString(5, post.getMusician());
			pst.setString(6, post.getInstrument());
			int writeCnt = pst.executeUpdate();

			if (writeCnt > 0) {
				return post;
			} else {
				return null;
			}
		}
	}
	 
	public int update(Connection conn, Post post) throws SQLException {
		String sql = "update post set title = ?, genre=?, musician=?, instrument=? where postId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, post.getTitle());
			pst.setString(2, post.getGenre());
			pst.setString(3, post.getMusician());
			pst.setString(4, post.getInstrument());
			pst.setInt(5, post.getPostId());
			return pst.executeUpdate();
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
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

	// 리미트를 이용한 리스트를 가져오는 쿼리
	public List<Post> select(Connection conn, int startRow, int size) throws SQLException {
		String query = "select  * from Post order by postId desc limit ?, ?";
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, startRow);
			pst.setInt(2, size);
			try (ResultSet rs = pst.executeQuery()) {
				List<Post> postList = new ArrayList<Post>();
				while (rs.next()) {
					postList.add(toPost(rs));
				}
				return postList;
			}
		}
	}
	
	public int selectLatestPostId(Connection conn) throws SQLException {
		String query="select postId from post order by write_date desc limit 1";
		try(Statement pst = conn.createStatement()){
			ResultSet rs = pst.executeQuery(query);
			if(rs.next()) {
			return rs.getInt(1);
			}
		}
		return 0;
	}

	
	public int delete(Connection conn, int postId) throws SQLException{ 
		String query = "update post set able=0 where postId=?";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setInt(1, postId);
			return pst.executeUpdate();
		}
	}
	
	// ResultSet으로 나온 결과를 Post객체로 생성해 담는 역할
	private Post toPost(ResultSet rs) throws SQLException {
		Post post = new Post(rs.getInt("postId"), new Writer(rs.getInt("userId"),rs.getString("name")),
				rs.getString("title"), rs.getString("genre"), rs.getString("musician"), rs.getString("instrument")
				, rs.getTimestamp("write_date").toLocalDateTime(), rs.getTimestamp("update_date").toLocalDateTime(), rs.getInt("views"));

		return post;

	}
	
	public Post selectById(Connection conn, int postId) throws SQLException {
		String query = "select * from Post where postId= ? and able = 1";
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, postId);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return toPost(rs);
				}	
			}
		}
		return null;
	}
	
	public void increaseReadCount(Connection conn, int postId) throws SQLException {
		String query = "update post set views = views+1 where postId = ? ";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setInt(1, postId);
			pst.executeUpdate();
		}
	}

}
