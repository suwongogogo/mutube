package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	
	public List<Post> searchPost(String keyword) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostDAO postDAO = PostDAO.getInstance();
			List<Post> postList = postDAO.searchPostList(conn, keyword);
			
			if(postList == null) {
				throw new PostNotFoundException("검색된 게시글이 없습니다.");
			}
			
			return postList;
		}
	}
}
