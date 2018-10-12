package Post.Model;

public class Writer {
	private int userId;

	private String loginId;
	private String name;
	
	public Writer(int userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	public Writer(String loginId, String name) {
		this.loginId = loginId;
		this.name = name;
	}


	public int getUserId() {
		return userId;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public String getName() {
		return name;
	}
	
}
