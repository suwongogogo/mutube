package Post.DAO;

public class PostContentDAO {
	private static PostContentDAO instance = new PostContentDAO();
	private PostContentDAO() {};
	public static PostContentDAO getInstance() {
		return instance;
	}
}
