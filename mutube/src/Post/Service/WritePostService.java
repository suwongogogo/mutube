package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import File.DAO.FileDAO;
import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Model.File;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;

public class WritePostService {
	private static WritePostService instance = new WritePostService();
	private WritePostService() {}
	public static WritePostService getInstance() {
		return instance;
	}
	
	public int write(PostData writeReq) throws SQLException {
		PostDAO postDAO = PostDAO.getInstance();
		PostContentDAO contentDAO = PostContentDAO.getInstance();
		FileDAO fileDAO = FileDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			Post post = writeReq.getPost();
			postDAO.insert(conn, post);
			
			int postId = postDAO.selectLatestPostId(conn);
			if(postId == 0) {
				conn.rollback();
				throw new RuntimeException("게시글 삽입 실패");
			}
			
			PostContent postContent = null;
			if( writeReq.getImage()!= null) {
				postContent = new PostContent(postId, writeReq.getPostContent().getContent(), writeReq.getPostContent().getVideo_link(), writeReq.getImage().getFileName());
			}else {
				postContent = new PostContent(postId, writeReq.getPostContent().getContent(), writeReq.getPostContent().getVideo_link());
			}
			int ret = contentDAO.insert(conn, postContent);
			if(ret == 0) {
				conn.rollback();
				throw new RuntimeException("Content 삽입 실패");
			}
			
			File image = null;
			if(writeReq.getImage()!= null) {
				image = writeReq.getImage();
				fileDAO.upload(conn, image);
			}
			
			
			
			conn.commit();
			return postId;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
