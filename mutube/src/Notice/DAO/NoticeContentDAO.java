package Notice.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Notice.Model.NoticeContent;

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
}
