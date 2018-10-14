package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.DAO.PostDAO;
import Post.Exception.PostNotFoundException;

public class DeletePostService {
	private static DeletePostService instance = new DeletePostService();
	private DeletePostService() {}
	public static DeletePostService getInstance() {
		return instance;
	}
	
	public int delete(int postId) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			PostDAO postDAO = PostDAO.getInstance();
			
			int cnt = postDAO.delete(conn, postId);
			if(cnt == 0 ) {
				conn.rollback();
				throw new PostNotFoundException("게시글 삭제 실패");
			}
			conn.commit();
			return cnt;
		}
	}
}
