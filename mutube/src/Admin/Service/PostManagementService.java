package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Admin.DAO.AdminDAO;
import Connection.ConnectionProvider;
import Post.Model.Post;

public class PostManagementService {
	private static PostManagementService instance = new PostManagementService();
	private PostManagementService() {}
	public static PostManagementService getInstance() {
		return instance;
	}
	
	private int size = 7;
	private int blockSize = 6;
	
	public PostPageINF getPostList(int pageNum) throws SQLException{
		try (Connection conn = ConnectionProvider.getConnection()){
			AdminDAO adminDAO = AdminDAO.getInstance();
			int total = adminDAO.getPostCount(conn);
			List<Post> postList = adminDAO.getPostList(conn, (pageNum - 1) * size, size);
			
			return new PostPageINF(postList, pageNum, total, size, blockSize);		
		}
	}
}
