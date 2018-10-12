package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Post.Model.NoticeContent;

public class NoticeContentDAO {
	private static NoticeContentDAO instance = new NoticeContentDAO();

	private NoticeContentDAO() {
	};

	public static NoticeContentDAO getInstance() {
		return instance;
	}
	
	public NoticeContent insertNotice(Connection conn, NoticeContent noticeContent) throws SQLException {
		String sql = "insert into notice_content(noticeid, content, video_link, imageName) values(?,?,?,?)";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, noticeContent.getNoticeId());
			pst.setString(2, noticeContent.getContent());
			pst.setString(3, noticeContent.getVideo_link());
			pst.setString(4, noticeContent.getImageNamesStr());
			int count = pst.executeUpdate();
			
			if(count > 0) {
				return noticeContent;
			}else {
				return null;
			}
		}
	}
}
