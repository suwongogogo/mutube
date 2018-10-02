package Post.Model;

public class Writer {
	private int userId;
	private String name;
	public Writer(int userId, String name) {
		this.userId = userId;
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}
	
}
