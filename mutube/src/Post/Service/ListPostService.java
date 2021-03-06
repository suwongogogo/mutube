package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import Post.DAO.PostDAO;
import Post.Model.Post;
import Post.Model.PostPage;

public class ListPostService {
	private static ListPostService instance = new ListPostService();
	private ListPostService() {}
	public static ListPostService getInstance() {
		return instance;
	}
	
	private int size = 15;
	private int blockSize = 10;
	
	public PostPage getPostPage(int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection();){
			PostDAO postDAO = PostDAO.getInstance();
			int total = postDAO.selectCount(conn);
			List<Post> postList = postDAO.select(conn, (pageNum - 1) * size , size);
			return new PostPage(postList, pageNum, total, size, blockSize);
		}
	}
}
