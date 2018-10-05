package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Exception.UpdatePostFailExcpetion;

public class UpdatePostService {
	private static UpdatePostService instance = new UpdatePostService();
	private UpdatePostService() {}
	public static UpdatePostService getInstance() {
		return instance;
	}
	
	PostDAO postDAO = PostDAO.getInstance();
	PostContentDAO postContentDAO = PostContentDAO.getInstance();
	
	public PostData getPost(int postId) throws SQLException {
		PostData postData = null;

		try(Connection conn = ConnectionProvider.getConnection()){
			
			Post post = postDAO.selectById(conn, postId);
			PostContent postContent = postContentDAO.selectByPostId(conn, postId);
			postData = new PostData(post, postContent);
		}
		
		return postData;
	}
	
	public void update(PostData postData) throws UpdatePostFailExcpetion, SQLException{
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			

			int updateCnt = postDAO.update(conn, postData.getPost());
			if(updateCnt==0) {
				conn.rollback();
				throw new UpdatePostFailExcpetion("게시글 삽입 실패");
			}
			
			postContentDAO.update(conn, postData.getPostContent(), postData.getPost().getPostId());
			if(updateCnt==0) {
				conn.rollback();
				throw new UpdatePostFailExcpetion("내용 삽입 실패");
			}
			
			conn.commit();
		}
	}
}
