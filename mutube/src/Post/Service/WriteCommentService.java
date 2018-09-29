package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.DAO.PostCommentDAO;
import Post.Model.Post;
import Post.Model.PostComment;
import Post.Model.PostContent;

public class WriteCommentService {
	private static WriteCommentService instance = new WriteCommentService();
	private WriteCommentService() {}
	public static WriteCommentService getInstance() {
		return instance;
	}
	
	public void writeComment(PostComment postComment) throws SQLException {
		PostCommentDAO commentDAO = PostCommentDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
						
			PostComment savedpostComment = new PostComment(postComment.getPostId(), postComment.getUserId()
					, postComment.getName(), postComment.getComment());
			if(savedpostComment == null) {
				throw new RuntimeException("댓글 작성 실패");
			}
			 
			conn.commit();
		}
	}
}
