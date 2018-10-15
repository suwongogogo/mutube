package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.DAO.PostDAO;
import Post.Exception.DeletePostFailException;
import Post.Exception.PostNotFoundException;

public class DeletePostService {
	private static DeletePostService instance = new DeletePostService();
	private DeletePostService() {}
	public static DeletePostService getInstance() {
		return instance;
	}
	
	public int delete(int postId) throws DeletePostFailException, SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			PostDAO postDAO = PostDAO.getInstance();
			try {
				int cnt = postDAO.delete(conn, postId);
				conn.commit();
				return cnt;
			}catch(SQLException e ) {
				conn.rollback();
				throw new DeletePostFailException("게시글 삭제 실패");
			}
		}
	}
}
