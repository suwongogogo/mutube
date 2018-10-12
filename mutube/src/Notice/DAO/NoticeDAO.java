package Notice.DAO;

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

	public int selectCount(Connection conn) throws SQLException {
		String sql = "select count(*) from notice where able = 1";
		try(Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql)){
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}

	public List<Notice> selectNoticeList(Connection conn, int startRow, int size) throws SQLException {
		String sql = "select * from notice where able=1 order by noticeId desc limit ?, ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, startRow);
			pst.setInt(2, size);
			try(ResultSet rs = pst.executeQuery()){
				List<Notice> noticeList = new ArrayList<Notice>();
				while(rs.next()) {
					noticeList.add(getNotice(rs));
				}
				return noticeList;
			}
		}
	}
	
	private Notice getNotice(ResultSet rs) throws SQLException {
		Notice notice = new Notice(rs.getInt("noticeId"), new Writer(rs.getInt("userId"),rs.getString("name")),
				rs.getString("title"), rs.getTimestamp("write_date").toLocalDateTime(), rs.getTimestamp("update_date").toLocalDateTime()
				, rs.getInt("views"), rs.getBoolean("able"));

		return notice;
	}
}
