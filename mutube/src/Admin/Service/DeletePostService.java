package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Admin.DAO.AdminDAO;
import Connection.ConnectionProvider;
import Post.Exception.PostNotFoundException;

public class DeletePostService {
	private static DeletePostService instance = new DeletePostService();

	private DeletePostService() {
	}

	public static DeletePostService getInstance() {
		return instance;
	}

	public void DeletePost(int postId) throws PostNotFoundException, SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			AdminDAO adminDAO = AdminDAO.getInstance();
			conn.setAutoCommit(false);

			try {
				int count = adminDAO.deletePost(conn, postId);
				if (count == 0) {
					conn.rollback();
					throw new PostNotFoundException("게시글을 찾을 수 없습니다.");
				}

				count = adminDAO.deletePostContent(conn, postId);
				if (count == 0) {
					conn.rollback();
					throw new PostNotFoundException("내용을 찾을 수 없습니다.");
				}
				count = adminDAO.deletePostComment(conn, postId);
				conn.commit();
				
			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
				throw new SQLException();
			}
		}
		
	}
}
