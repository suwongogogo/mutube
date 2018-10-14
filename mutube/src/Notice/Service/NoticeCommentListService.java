package Notice.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import Notice.DAO.NoticeCommentDAO;
import Notice.Model.NoticeComment;
import Notice.Model.NoticeCommentPage;
import Post.DAO.PostCommentDAO;
import Post.Model.CommentPage;
import Post.Model.PostComment;
import Post.Service.CommentListService;
import User.DAO.UserDAO;

public class NoticeCommentListService {
	private static NoticeCommentListService instance = new NoticeCommentListService();
	private NoticeCommentListService() {}
	public static NoticeCommentListService getInstance() {
		return instance;
	}
	
	private int size = 10;
	private int blockSize = 5;
	
	public NoticeCommentPage commentList(int pageNum, int postId) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			NoticeCommentDAO noticeCommentDAO = NoticeCommentDAO.getInstance();
			
			int total = noticeCommentDAO.commentCount(conn, postId);
			
			List<NoticeComment> noticeCommentList = noticeCommentDAO.noticeCommentList(conn, postId, (pageNum - 1) * size, size);
			UserDAO userDAO = UserDAO.getInstance();
			for(int i = 0; i < noticeCommentList.size(); i++) {
				noticeCommentList.get(i).setWriter(userDAO.getWriter(conn, noticeCommentList.get(i).getUserId()));
			}
			
			
			return new NoticeCommentPage(noticeCommentList, pageNum, total, size, blockSize);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("");
		}
	}
}
