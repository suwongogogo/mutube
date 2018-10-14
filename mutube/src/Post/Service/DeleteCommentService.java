package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.DAO.PostCommentDAO;
import Post.Exception.CommentNotFoundException;

public class DeleteCommentService {
	private static DeleteCommentService instance = new DeleteCommentService();
	private DeleteCommentService() {}
	public static DeleteCommentService getInstance() {
		return instance;
	}
	
	public void deleteComment(int commentId) throws SQLException, CommentNotFoundException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostCommentDAO postCommentDAO = PostCommentDAO.getInstance();
			int count = postCommentDAO.commentDelete(conn, commentId);
			
			if(count == 0) {
				throw new CommentNotFoundException("댓글을 찾을 수 없음");
			}
		}
	}
}
