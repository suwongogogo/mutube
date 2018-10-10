package Post.Model;

import java.time.LocalDateTime;

public class PostComment {

	private int postId;
	private int userId;
	private String loginId;
	private String name;
	private String comment;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	
	public PostComment(int postId, int userId, String loginId, String name, String comment, LocalDateTime write_date,
			LocalDateTime update_date) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.loginId = loginId;
		this.name = name;
		this.comment = comment;
		this.write_date = write_date;
		this.update_date = update_date;
	}

	public PostComment(int postId, int userId, String loginId, String name, String comment) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.loginId = loginId;
		this.name = name;
		this.comment = comment;
	}

	public int getPostId() {
		return postId;
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

	public String getComment() {
		return comment;
	}

	public LocalDateTime getWrite_date() {
		return write_date;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

}
