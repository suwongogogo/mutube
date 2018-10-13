package Notice.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Notice.Model.NoticeComment;
import Post.Model.PostComment;

public class NoticeCommentDAO {
	private static NoticeCommentDAO instance = new NoticeCommentDAO();

	private NoticeCommentDAO() {
	};

	public static NoticeCommentDAO getInstance() {
		return instance;
	}
	public NoticeComment insert(Connection conn, NoticeComment noticeComment) throws SQLException {
		String sql = "insert into notice_comment(noticeId, userId, comment) values(?, ?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, noticeComment.getNoticeId());
			pst.setInt(2, noticeComment.getUserId());
			pst.setString(3, noticeComment.getComment());
			int insertCnt = pst.executeUpdate();

			if (insertCnt > 0) {
				return noticeComment;
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
	
	public List<NoticeComment> noticeCommentList(Connection conn, int noticeId, int startRow, int size) throws SQLException{
		String sql = "select * from notice_comment where noticeId = ? order by write_date desc limit ?, ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, noticeId);
			pst.setInt(2, startRow);
			pst.setInt(3, size);
			try(ResultSet rs = pst.executeQuery()){
				List<NoticeComment> noticeCommentList = new ArrayList<NoticeComment>();
				while(rs.next()) {
					NoticeComment noticeComment = new NoticeComment(rs.getInt("commentId"),rs.getInt("noticeId"), rs.getInt("userId"),
							rs.getString("comment"));
					noticeCommentList.add(noticeComment);
				}
				return noticeCommentList;
			}
		}
	}
	
	public int commentCount(Connection conn, int noticeId) throws SQLException {
		String sql ="select count(*) from notice_comment where noticeId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, noticeId);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		}
	}
	
	public int commentDelete(Connection conn, int commentId) throws SQLException {
		String sql = "delete from notice_comment where commentId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, commentId);
			return pst.executeUpdate();
		}
	}
}
