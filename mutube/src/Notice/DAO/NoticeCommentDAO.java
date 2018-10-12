package Notice.DAO;

public class NoticeCommentDAO {
	private static NoticeCommentDAO instance = new NoticeCommentDAO();

	private NoticeCommentDAO() {
	};

	public static NoticeCommentDAO getInstance() {
		return instance;
	}
}
