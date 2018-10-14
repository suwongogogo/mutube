package Notice.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionProvider;
import Notice.DAO.NoticeContentDAO;
import Notice.DAO.NoticeDAO;
import Notice.Exception.NoticeNotFoundException;
import Notice.Model.Notice;
import Notice.Model.NoticeContent;
import Notice.Model.NoticeData;
import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Service.ReadPostService;

public class ReadNoticeService {
	private static ReadNoticeService instance = new ReadNoticeService();
	private ReadNoticeService() {}
	public static ReadNoticeService getInstance() {
		return instance;
	}
	
	public NoticeData getNotice(int noticeId) throws SQLException, NoticeNotFoundException {
		NoticeData NoticeData = null;
		
		try(Connection conn = ConnectionProvider.getConnection()){
			try {
			System.out.println("noticeId : "+noticeId);
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			Notice notice = noticeDAO.selectById(conn, noticeId);
			conn.setAutoCommit(false);
			if(notice == null) {
				conn.rollback();
				throw new NoticeNotFoundException("게시글을 찾을 수 없음");
			}
			
			NoticeContentDAO noticeContentDAO = NoticeContentDAO.getInstance();
			NoticeContent noticeContent = noticeContentDAO.selectByNoticeId(conn, noticeId);
			if(noticeContent ==null) {
				conn.rollback();
				throw new NoticeNotFoundException("게시글 내용을 찾을 수 없음");
			}
			noticeDAO.increaseReadCount(conn, noticeId);
			
			if(noticeContent.getImageNamesStr()!=null) {
				String[] names = noticeContent.getImageNamesStr().split("\\?");
				ArrayList<String> imageNames = new ArrayList<String>();
				for(String name : names) {
					imageNames.add(name);
				}
				noticeContent.setImageNames(imageNames);
			}else {
				noticeContent.setImageNames(null);
			}
			
			conn.commit();
			NoticeData = new NoticeData(notice, noticeContent);
			}catch(SQLException e) {
				e.printStackTrace();
				conn.rollback();
				throw new SQLException("");
			}
		}
		return NoticeData;
	}
}
