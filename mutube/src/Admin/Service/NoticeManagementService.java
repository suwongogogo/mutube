package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Admin.DAO.AdminDAO;
import Connection.ConnectionProvider;
import Notice.Model.Notice;
import Post.Model.Post;

public class NoticeManagementService {
	private static NoticeManagementService instance = new NoticeManagementService();
	private NoticeManagementService() {}
	public static NoticeManagementService getInstance() {
		return instance;
	}
	
	private int size = 7;
	private int blockSize = 6;
	
	public NoticePageINF getNoticeList(int pageNum) throws SQLException{
		try (Connection conn = ConnectionProvider.getConnection()){
			AdminDAO adminDAO = AdminDAO.getInstance();
			int total = adminDAO.getNoticeCount(conn);
			List<Notice> noticeList = adminDAO.getNoticeList(conn, (pageNum - 1) * size, size);
			
			return new NoticePageINF(noticeList, pageNum, total, size, blockSize);		
		}
	}
}
