package Post.Service;

import Post.DAO.PostContentDAO;
import Post.DAO.PostDAO;
import Post.Model.PostContent;
import Request.WriteRequest;

public class WritePostService {
	private static WritePostService instance = new WritePostService();
	private WritePostService() {}
	public static WritePostService getInstance() {
		return instance;
	}
	
	public void write(WriteRequest writeReq) {
		PostDAO postDAO = PostDAO.getInstance();
		PostContentDAO contentDAO = PostContentDAO.getInstance();
	}
}
