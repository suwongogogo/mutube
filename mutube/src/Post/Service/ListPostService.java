package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import Post.DAO.PostDAO;
import Post.Model.Post;

public class ListPostService {
	private static ListPostService instance = new ListPostService();
	private ListPostService() {}
	public static ListPostService getInstance() {
		return instance;
	}
	
	private int size = 20;
	private int blockSize = 10;
	
	public PostPage getArticlePage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection();){
			PostDAO postDAO = PostDAO.getInstance();
			int total = postDAO.selectCount(conn);
			List<Post> postList = postDAO.select(conn, (pageNum - 1) * size , size);
			return new PostPage(postList, pageNum, total, size, blockSize);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
