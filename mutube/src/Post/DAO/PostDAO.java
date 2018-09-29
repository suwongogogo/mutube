package Post.DAO;

public class PostDAO {	
	private static PostDAO instance = new PostDAO();
	private PostDAO() {};
	public static PostDAO getInstance() {
		return instance;
	}
	
	
}
