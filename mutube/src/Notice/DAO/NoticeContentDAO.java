package Notice.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Notice.Model.Notice;
import Notice.Model.NoticeContent;
import Post.Model.PostContent;

public class NoticeContentDAO {
	private static NoticeContentDAO instance = new NoticeContentDAO();

	private NoticeContentDAO() {
	};

	public static NoticeContentDAO getInstance() {
		return instance;
	}
	
	public int insertNoticeContent(Connection conn, NoticeContent noticeContent) throws SQLException {
		String sql = "insert into notice_content(noticeid, content, video_link, imageName) values(?,?,?,?)";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, noticeContent.getNoticeId());
			pst.setString(2, noticeContent.getContent());
			pst.setString(3, noticeContent.getVideo_link());
			pst.setString(4, noticeContent.getImageNamesStr());
			return pst.executeUpdate();
		}
	}

	public NoticeContent selectByNoticeId(Connection conn, int noticeId) throws SQLException {
		String sql = "select * from notice_content where noticeId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, noticeId);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return new NoticeContent(rs.getInt("noticeId"), rs.getString("content"), rs.getString("video_link"), rs.getString("imageName"));
				}
			}
		}
		return null;
	}

	public int updateNoticeContent(Connection conn, NoticeContent noticeContent, int noticeId) throws SQLException {
		String sql ="update notice_Content set content = ?, video_link = ? where noticeId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, noticeContent.getContent());
			pst.setString(2, noticeContent.getVideo_link());
			pst.setInt(3, noticeId);
			return pst.executeUpdate();
		}
	}

	public int updateWithImage(Connection conn, NoticeContent noticeContent, int noticeId) throws SQLException {
		String sql = "update noticeContent set content = ?, video_link = ?, ImageName = ? where noticeId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, noticeContent.getContent());
			pst.setString(2, noticeContent.getVideo_link());
			pst.setString(3, noticeContent.getImageNamesStr());
			pst.setInt(4, noticeId);
			return pst.executeUpdate();
		}
	}
}
