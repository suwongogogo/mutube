package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Admin.DAO.AdminDAO;
import Admin.Exception.DeleteFailException;
import Connection.ConnectionProvider;

public class DeletePostService {
	private static DeletePostService instance = new DeletePostService();
	private DeletePostService() {}
	public static DeletePostService getInstance() {
		return instance;
	}
	
	public void DeletePost(int postId) throws SQLException, DeleteFailException {
		try(Connection conn = ConnectionProvider.getConnection()){
			AdminDAO adminDAO = AdminDAO.getInstance();
			conn.setAutoCommit(false);
			
			int count = adminDAO.deletePost(conn, postId);
			if(count == 0) {
				conn.rollback();
				throw new DeleteFailException("게시글을 찾을 수 없습니다.");
			}
			
			count =  adminDAO.deletePostContent(conn, postId);
			if(count == 0) {
				conn.rollback();
				throw new DeleteFailException("내용을 찾을 수 없습니다.");
			}
			
			count = adminDAO.deletePostComment(conn, postId);
			conn.commit();
			
		}
	}
}
