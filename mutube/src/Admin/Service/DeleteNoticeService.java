package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Admin.DAO.AdminDAO;
import Connection.ConnectionProvider;

public class DeleteNoticeService {
	private static DeleteNoticeService instance = new DeleteNoticeService();
	private DeleteNoticeService() {}
	public static DeleteNoticeService getInstance() {
		return instance;
	}
	
	public int deleteNotice(int noticeId) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			AdminDAO adminDAO = AdminDAO.getInstance();
			int Cnt = adminDAO.deleteNotice(conn, noticeId);
			
			return Cnt;
		}
	}
}
