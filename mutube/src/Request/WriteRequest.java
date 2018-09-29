package Request;

public class WriteRequest {
	private int userId;
	private String title;
	private String content;
	public WriteRequest(int userId, String title, String content) {
		super();
		this.userId = userId;
		this.title = title;
		this.content = content;
	}
	public int getUserId() {
		return userId;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	
}
