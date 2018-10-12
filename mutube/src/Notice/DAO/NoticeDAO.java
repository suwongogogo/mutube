package Notice.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Notice.Model.Notice;

public class NoticeDAO {
	private static NoticeDAO instance = new NoticeDAO();

	private NoticeDAO() {
	};

	public static NoticeDAO getInstance() {
		return instance;
	}
	
	public Notice insertNotice(Connection conn, Notice notice) throws SQLException {
		String sql = "insert into notice(userId, name, write_date) values(?, ?, now())";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, notice.getWrtier().getUserId());
			pst.setString(2, notice.getWrtier().getName());
			int count = pst.executeUpdate();
			
			if(count > 0) {
				return notice;
			}else {
				return null;
			}
		}
	}
	
	public int selectLatestNoticeId(Connection conn) throws SQLException {
		String query="select noticeId from notice order by write_date desc limit 1";
		try(Statement pst = conn.createStatement()){
			ResultSet rs = pst.executeQuery(query);
			if(rs.next()) {
			return rs.getInt(1);
			}
		}
		return 0;
	}
}
