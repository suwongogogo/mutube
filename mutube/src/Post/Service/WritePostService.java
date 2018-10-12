package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;

import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Model.File;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Exception.WritePostFailException;

public class WritePostService {
	private static WritePostService instance = new WritePostService();
	private WritePostService() {}
	public static WritePostService getInstance() {
		return instance;
	}
	
	public int write(PostData writeReq) throws SQLException {
		PostDAO postDAO = PostDAO.getInstance();
		PostContentDAO contentDAO = PostContentDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			Post post = writeReq.getPost();
			postDAO.insert(conn, post);
			
			int postId = postDAO.selectLatestPostId(conn);
			if(postId == 0) {
				conn.rollback();
				throw new WritePostFailException("게시글 삽입 실패");
			}
			System.out.println("게시글 삽입 성공");
			
			PostContent postContent = null;
			if( writeReq.getPostContent().getImageNames()!= null) {
				String imageName = "";
				for(int i = 0; i < writeReq.getPostContent().getImageNames().size(); i++) {
					imageName += writeReq.getPostContent().getImageNames().get(i);
					
					if(i<writeReq.getPostContent().getImageNames().size()-1) {
						imageName+="?";
					}
				}
				System.out.println(imageName);
				postContent = new PostContent(postId, writeReq.getPostContent().getContent(), writeReq.getPostContent().getVideo_link(), imageName );
			}else {
				postContent = new PostContent(postId, writeReq.getPostContent().getContent(), writeReq.getPostContent().getVideo_link());
			}
			System.out.println("내용 삽입 시도");
			int ret = contentDAO.insert(conn, postContent);
			System.out.println("내용 삽입 성그옹");
			if(ret == 0) {
				conn.rollback();
				throw new WritePostFailException("Content 삽입 실패");
			}

			conn.commit();
			return postId;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
