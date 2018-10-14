package Notice.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import Notice.DAO.NoticeDAO;
import Notice.Exception.NothingException;
import Notice.Model.Notice;
import Notice.Model.NoticePage;
import Post.DAO.PostDAO;
import Post.Model.Post;
import Post.Model.PostPage;

public class NoticeListService {
	private static NoticeListService instance = new NoticeListService();

	private NoticeListService() {
	}

	public static NoticeListService getInstance() {
		return instance;
	}
	
	private int size = 20;
	private int blockSize = 10;
	
	public NoticePage getNoticePage(int pageNum) throws SQLException, NothingException {
		try(Connection conn = ConnectionProvider.getConnection();){
			try {
				NoticeDAO noticeDAO = NoticeDAO.getInstance();
				int total = noticeDAO.selectCount(conn);
				if(total == 0) {
					throw new NothingException("공지가 존재하지 않음.");
				}
				List<Notice> noticeList = noticeDAO.selectNoticeList(conn, (pageNum - 1) * size , size);
				return new NoticePage(noticeList, pageNum, total, size, blockSize);
			}catch(SQLException e) {
				e.printStackTrace();
				throw new SQLException("");
			}	
		}
	}
}
