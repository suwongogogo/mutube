package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Post.Model.PostComment;

public class PostCommentDAO {
	private static PostCommentDAO instance = new PostCommentDAO();

	private PostCommentDAO() {
	};

	public static PostCommentDAO getInstance() {
		return instance;
	}
	
	public PostComment insert(Connection conn, PostComment postComment) throws SQLException {
		String sql = "insert into post_comment(postId, userId, comment) values(?, ?, ?)";
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
	
	public int update(Connection conn, String userId, String comment , int postId) throws SQLException {
		String sql = "update post_comment set comment = ? where postId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, userId);
			pst.setString(2, comment);
			pst.setInt(3, postId);
			return pst.executeUpdate();
		}
	}
	
	public List<PostComment> commentList(Connection conn, int postId, int startRow, int size) throws SQLException{
		String sql = "select * from post_comment where postId = ? order by write_date asc limit ?, ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, postId);
			pst.setInt(2, startRow);
			pst.setInt(3, size);
			try(ResultSet rs = pst.executeQuery()){
				List<PostComment> commentList = new ArrayList<PostComment>();
				while(rs.next()) {
					PostComment postComment = new PostComment(rs.getInt("postId"), rs.getInt("userId"),
							rs.getString("comment"), rs.getString(4), rs.getString(5));
					commentList.add(postComment);
				}
				return commentList;
			}
		}
	}
	
	public int commentCount(Connection conn, int postId) throws SQLException {
		String sql ="select count(*) from post_comment where postId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, postId);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}
}
