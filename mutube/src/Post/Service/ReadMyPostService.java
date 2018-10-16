package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import Post.DAO.PostDAO;
import Post.Model.Post;
import Post.Model.PostPage;
import User.DAO.UserDAO;

public class ReadMyPostService {
	private static ReadMyPostService instance = new ReadMyPostService();

	private ReadMyPostService() {
	}

	public static ReadMyPostService getInstance() {
		return instance;
	}
	
	private int size = 10;
	private int blockSize = 5;
	
	public PostPage readMyPost(int pageNum, int userId) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostDAO postDAO = PostDAO.getInstance();
			int total = postDAO.selectCountByUserId(conn, userId);
			List<Post> postList = postDAO.selectByUserId(conn, userId, (pageNum - 1) * size, size);
			return new PostPage(postList, pageNum, total, size, blockSize); 
		}
	}

}
