package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.DAO.PostCommentDAO;
import Post.DAO.PostDAO;
import Post.Exception.WriteCommentFailException;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostComment;
import Post.Model.PostContent;
import Post.Model.PostData;

public class WriteCommentService {
	private static WriteCommentService instance = new WriteCommentService();
	private WriteCommentService() {}
	public static WriteCommentService getInstance() {
		return instance;
	}
	
	public void writeComment(PostComment postComment) throws SQLException, WriteCommentFailException {
		PostCommentDAO commentDAO = PostCommentDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			PostComment savedpostComment = commentDAO.insert(conn, postComment);
			
			if(savedpostComment == null) {
				throw new WriteCommentFailException("댓글 작성 실패");
			}
			 
		}
	}
	
	public Post selectById(int postId) throws SQLException {
		PostDAO postDAO = PostDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			Post post = postDAO.selectById(conn, postId);
			if(post == null) {
				throw new PostNotFoundException("게시글을 찾을 수 없음.");
			}
			
			return post;
		}
	}
}
