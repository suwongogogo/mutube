package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
		String sql = "insert into post(userId, loginId ,name, title, genre, country, instrument) values(?,?,?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, post.getWriter().getUserId());
			pst.setString(2, post.getWriter().getLoginId());
			pst.setString(3, post.getWriter().getName());
			pst.setString(4, post.getTitle());
			pst.setString(5, post.getGenre());
			pst.setString(6, post.getCountry());
			pst.setString(7, post.getInstrument());
			int writeCnt = pst.executeUpdate();

			if (writeCnt > 0) {
				return post;
			} else {
				return null;
			}
		}
	}
	 
	public int update(Connection conn, Post post) throws SQLException {
		String sql = "update post set title = ?, genre=?, country=?, instrument=? where postId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, post.getTitle());
			pst.setString(2, post.getGenre());
			pst.setString(3, post.getCountry());
			pst.setString(4, post.getInstrument());
			pst.setInt(5, post.getPostId());
			return pst.executeUpdate();
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
		String query = "select count(*) from post where able=1";
		int cnt = 0;
		try (Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				cnt = rs.getInt(1);

			}
		}
		return cnt;
	}
	
	public int selectCountByUserId(Connection conn, int userId) throws SQLException {
		String query = "select count(*) from post where able=1 and userId = ?";
		int cnt = 0;
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, userId);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					cnt = rs.getInt(1);
				}
			}
		}
		return cnt;
	}

	// 리미트를 이용한 리스트를 가져오는 쿼리
	public List<Post> select(Connection conn, int startRow, int size) throws SQLException {
		String query = "select postId, userId, loginId, name, title, genre, country, instrument, "
				+ "date_format(write_date, '%Y-%m-%d %H:%i'), date_format(update_date, '%Y-%m-%d %H:%i'), "
				+ "views, able from Post where able=1 order by postId desc limit ?, ?";
		
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
	
	public List<Post> searchPostList(Connection conn, String keyword, int startRow, int size) throws SQLException{
		String query = "select postId, userId, loginId, name, title, genre, country, instrument, "
					+ "date_format(write_date, '%Y-%m-%d %H:%i'), date_format(update_date, '%Y-%m-%d %H:%i')," 
					+ "views, able from Post where title like ? and able = 1 order by write_date desc limit ?, ?";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			pst.setInt(2, startRow);
			pst.setInt(3, size);
			try(ResultSet rs = pst.executeQuery()){
				List<Post> postList = new ArrayList<Post>();
				while(rs.next()) {
					postList.add(toPost(rs));
				}
				return postList;
			}
		}
	}
	
	public List<Post> selectByUserId(Connection conn, int userId, int startRow, int size) throws SQLException {
		String query = "select postId, userId, loginId, name, title, genre, country, instrument, "
				+ "date_format(write_date, '%Y-%m-%d %H:%i'), date_format(update_date, '%Y-%m-%d %H:%i'), "
				+ "views, able from Post where able=1 and userId = ? order by postId desc limit ?, ?";
		
		try (PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, userId);
			pst.setInt(2, startRow);
			pst.setInt(3, size);
			try (ResultSet rs = pst.executeQuery()) {
				List<Post> postList = new ArrayList<Post>();
				while (rs.next()) {
					postList.add(toPost(rs));
				}
				return postList;
			}
		}
	}
	
	public List<Post> searchGenreList(Connection conn, String keyword, int startRow, int size) throws SQLException{
		String query = "select postId, userId, loginId, name, title, genre, country, instrument, "
				+ "date_format(write_date, '%Y-%m-%d %H:%i'), date_format(update_date, '%Y-%m-%d %H:%i')," 
				+ "views, able from Post where genre like ? and able = 1 order by write_date desc limit ?, ?";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			pst.setInt(2, startRow);
			pst.setInt(3, size);
			try(ResultSet rs = pst.executeQuery()){
				List<Post> postList = new ArrayList<Post>();
				while(rs.next()) {
					postList.add(toPost(rs));
				}
				return postList;
			}
		}
	}
	
	public List<Post> searchCountryList(Connection conn, String keyword, int startRow, int size) throws SQLException{
		String query = "select postId, userId,loginId, name, title, genre, country, instrument, "
				+ "date_format(write_date, '%Y-%m-%d %H:%i'), date_format(update_date, '%Y-%m-%d %H:%i')," 
				+ "views, able from Post where country like ? and able = 1 order by write_date desc limit ?, ?";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			pst.setInt(2, startRow);
			pst.setInt(3, size);
			try(ResultSet rs = pst.executeQuery()){
				List<Post> postList = new ArrayList<Post>();
				while(rs.next()) {
					postList.add(toPost(rs));
				}
				return postList;
			}
		}
	}
	
	public List<Post> searchInstrumentList(Connection conn, String keyword, int startRow, int size) throws SQLException{
		String query = "select postId, userId,loginId, name, title, genre, country, instrument, "
				+ "date_format(write_date, '%Y-%m-%d %H:%i'), date_format(update_date, '%Y-%m-%d %H:%i')," 
				+ "views, able from Post where Instrument like ? and able = 1  order by write_date desc limit ?, ?";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			pst.setInt(2, startRow);
			pst.setInt(3, size);
			try(ResultSet rs = pst.executeQuery()){
				List<Post> postList = new ArrayList<Post>();
				while(rs.next()) {
					postList.add(toPost(rs));
				}
				return postList;
			}
		}
	}
	
	public int searchTitleCount(Connection conn, String keyword) throws SQLException {
		String query = "select count(*) from post where title like ? and able = 1 ";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			return 0;
		}
	}
	
	public int searchInstrumentCount(Connection conn, String keyword) throws SQLException {
		String query = "select count(*) from post where instrument like ? and able = 1";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			return 0;
		}
	}
	
	public int searchGenreCount(Connection conn, String keyword) throws SQLException {
		String query = "select count(*) from post where genre like ? and able = 1";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			return 0;
		}
	}
	
	public int searchCountryCount(Connection conn, String keyword) throws SQLException {
		String query = "select count(*) from post where country like ? and able = 1";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, keyword);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			return 0;
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
		Post post = new Post(rs.getInt("postId"), new Writer(rs.getInt("userId"),rs.getString("loginId"),rs.getString("name")),
				rs.getString("title"), rs.getString("genre"), rs.getString("country"), rs.getString("instrument")
				, rs.getString(9), rs.getString(10), rs.getInt("views"), rs.getBoolean("able"));
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

	public List<Post> readMyPost(Connection conn, int userId) throws SQLException {
		String sql = "select * from post where userId = ? and able = 1";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, userId);
			try(ResultSet rs = pst.executeQuery()){
				List<Post> myPostList = new ArrayList<>();
				while(rs.next()) {
					Post post = new Post(rs.getInt("postId"), new Writer(rs.getInt("userId"),rs.getString("loginId"),rs.getString("name")),
							rs.getString("title"), rs.getString("genre"), rs.getString("country"), rs.getString("instrument")
							, rs.getString(9), rs.getString(10), rs.getInt("views"), rs.getBoolean("able"));
					myPostList.add(post);
				}
				return myPostList;
			}
		}
	}
	
}
