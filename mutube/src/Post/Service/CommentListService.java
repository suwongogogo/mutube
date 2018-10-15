package Post.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import Post.DAO.PostCommentDAO;
import Post.Model.CommentPage;
import Post.Model.PostComment;
import User.DAO.UserDAO;

public class CommentListService {
	private static CommentListService instance = new CommentListService();
	private CommentListService() {}
	public static CommentListService getInstance() {
		return instance;
	}
	
	private int size = 10;
	private int blockSize = 5;
	
	public CommentPage commentList(int pageNum, int postId) throws SQLException{
		try(Connection conn = ConnectionProvider.getConnection()){
			PostCommentDAO postCommentDAO = PostCommentDAO.getInstance();
			
			int total = postCommentDAO.commentCount(conn, postId);
			
			List<PostComment> commentList = postCommentDAO.commentList(conn, postId, (pageNum - 1) * size, size);
			UserDAO userDAO = UserDAO.getInstance();
			for(int i = 0; i < commentList.size(); i++) {
				commentList.get(i).setWriter(userDAO.getWriter(conn, commentList.get(i).getUserId()));
				String replaceComment = commentList.get(i).getComment().replaceAll("<", "&lt")
						.replaceAll(">", "&gt").replaceAll(" ", "&nbsp").replaceAll("\n", "<br>");
				commentList.get(i).setComment(replaceComment);
			}
			
			
			return new CommentPage(commentList, pageNum, total, size, blockSize);
		}
	}
}
