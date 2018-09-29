package Post.DAO;

public class PostCommentDAO {
	private static PostCommentDAO instance = new PostCommentDAO();
	private PostCommentDAO() {};
	public static PostCommentDAO getInstance() {
		return instance;
	}
}
