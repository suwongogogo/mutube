package Notice.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Notice.DAO.NoticeDAO;
import Post.DAO.PostDAO;
import Post.Exception.PostNotFoundException;
import Post.Service.DeletePostService;

public class DeleteNoticeService {
	private static DeleteNoticeService instance = new DeleteNoticeService();
	private DeleteNoticeService() {}
	public static DeleteNoticeService getInstance() {
		return instance;
	}
	
	public void deleteNotice(int noticeId) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			try {
				int cnt = noticeDAO.deleteNotice(conn, noticeId);
				if(cnt == 0 ) {
					conn.rollback();
					throw new PostNotFoundException("게시글 삭제 실패");
				}
			
				conn.commit();
			}catch(SQLException e) {
				e.printStackTrace();
				conn.rollback();
				throw new SQLException("");
			}
		}
	}
}
