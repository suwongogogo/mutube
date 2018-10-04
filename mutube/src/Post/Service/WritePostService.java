package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Model.Post;
import Post.Model.PostContent;
import Request.WriteRequest;

public class WritePostService {
	private static WritePostService instance = new WritePostService();
	private WritePostService() {}
	public static WritePostService getInstance() {
		return instance;
	}
	
	public int write(WriteRequest writeReq) throws SQLException {
		PostDAO postDAO = PostDAO.getInstance();
		PostContentDAO contentDAO = PostContentDAO.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			Post post = writeReq.getPost();
			postDAO.insert(conn, post);
			
			int postId = postDAO.selectLatestPostId(conn);
			if(postId == 0) {
				conn.rollback();
				throw new RuntimeException("게시글 삽입 실패");
			}
			
			PostContent postContent = new PostContent(postId, writeReq.getPostContent().getContent(), writeReq.getPostContent().getVideo_link());
			int ret = contentDAO.insert(conn, postContent);
			if(ret == 0) {
				conn.rollback();
				throw new RuntimeException("Content 삽입 실패");
			}
			
			conn.commit();
			return postId;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
