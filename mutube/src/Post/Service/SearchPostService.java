package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Admin.Service.PostPageINF;
import Connection.ConnectionProvider;
import Post.DAO.PostDAO;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostPage;

public class SearchPostService {
	private static SearchPostService instance = new SearchPostService();
	private SearchPostService() {}
	public static SearchPostService getInstance() {
		return instance;
	}
	
	private int size = 10;
	private int blockSize = 6;
	
	public PostPage searchTitle(String keyword, int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostDAO postDAO = PostDAO.getInstance();
			
			int total = postDAO.searchTitleCount(conn, keyword);
			
			List<Post> postList = postDAO.searchPostList(conn, keyword, (pageNum - 1) * size, size);
			
			if(postList == null) {
				throw new PostNotFoundException("검색된 게시글이 없습니다.");
			}
			
			return new PostPage(postList, pageNum, total, size, blockSize);
		}
	}
	
	public PostPage searchGenre(String keyword, int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostDAO postDAO = PostDAO.getInstance();
			
			int total = postDAO.searchGenreCount(conn, keyword);
			
			List<Post> postList = postDAO.searchGenreList(conn, keyword, (pageNum - 1) * size, size);
			
			if(postList == null) {
				throw new PostNotFoundException("검색된 게시글이 없습니다.");
			}
			
			return new PostPage(postList, pageNum, total, size, blockSize);
		}
	}
	
	public PostPage searchCountry(String keyword, int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostDAO postDAO = PostDAO.getInstance();
			
			int total = postDAO.searchCountryCount(conn, keyword);
			
			List<Post> postList = postDAO.searchCountryList(conn, keyword, (pageNum - 1) * size, size);
			
			if(postList == null) {
				throw new PostNotFoundException("검색된 게시글이 없습니다.");
			}
			
			return new PostPage(postList, pageNum, total, size, blockSize);
		}
	}
	
	public PostPage searchInstrument(String keyword, int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			PostDAO postDAO = PostDAO.getInstance();
			
			int total = postDAO.searchInstrumentCount(conn, keyword);
			
			List<Post> postList = postDAO.searchInstrumentList(conn, keyword, (pageNum - 1) * size, size);
			
			if(postList == null) {
				throw new PostNotFoundException("검색된 게시글이 없습니다.");
			}
			
			return new PostPage(postList, pageNum, total, size, blockSize);
		}
	}
}
