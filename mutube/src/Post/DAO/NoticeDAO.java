package Post.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Post.Model.Notice;

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
}
