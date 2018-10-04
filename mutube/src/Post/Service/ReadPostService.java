package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;


import Post.Exception.PostNotFoundException;
import Connection.ConnectionProvider;
import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;

public class ReadPostService {
	private static ReadPostService instance = new ReadPostService();
	private ReadPostService() {}
	public static ReadPostService getInstance() {
		return instance;
	}
	
	public PostData getPost(int postId) throws SQLException {
		PostData postData = null;
		
		try(Connection conn = ConnectionProvider.getConnection()){
			
			System.out.println("postId : "+postId);
			PostDAO postDAO = PostDAO.getInstance();
			Post post = postDAO.selectById(conn, postId);
			conn.setAutoCommit(false);
			if(post == null) {
				conn.rollback();
				throw new PostNotFoundException("게시글을 찾을 수 없음");
			}
			
			PostContentDAO postContentDAO = PostContentDAO.getInstance();
			PostContent postContent = postContentDAO.selectByPostId(conn, postId);
			if(postContent ==null) {
				conn.rollback();
				throw new PostNotFoundException("게시글 내용을 찾을 수 없음");
			}
			postDAO.increaseReadCount(conn, postId);
			conn.commit();
			
			postData = new PostData(post, postContent);
			
		}
		
		return postData;
	}
}
