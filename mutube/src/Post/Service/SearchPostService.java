package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Admin.Service.PostPageINF;
import Connection.ConnectionProvider;
import Post.DAO.PostDAO;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;

public class SearchPostService {
	private static SearchPostService instance = new SearchPostService();
	private SearchPostService() {}
	public static SearchPostService getInstance() {
		return instance;
	}
	
	private int size = 10;
	private int blockSize = 6;
	
	public PostPage searchPost(String keyword, int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostDAO postDAO = PostDAO.getInstance();
			
			int total = postDAO.searchPostCount(conn, keyword);
			
			List<Post> postList = postDAO.searchPostList(conn, keyword, (pageNum - 1) * size, size);
			
			if(postList == null) {
				throw new PostNotFoundException("검색된 게시글이 없습니다.");
			}
			
			return new PostPage(postList, pageNum, total, size, blockSize);
		}
	}
}
