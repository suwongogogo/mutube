package Post.Model;

import java.time.LocalDateTime;

public class PostComment {

	private int postId;
	private int userId;
	private String name;
	private String Comment;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	
	public PostComment(int postId, int userId, String name, String comment, LocalDateTime write_date,
			LocalDateTime update_date) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.name = name;
		Comment = comment;
		this.write_date = write_date;
		this.update_date = update_date;
	}

	public PostComment(int postId, int userId, String name, String comment) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.name = name;
		this.Comment = comment;
	}

	public int getPostId() {
		return postId;
	}

	public int getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}

	public String getComment() {
		return Comment;
	}

	public LocalDateTime getWrite_date() {
		return write_date;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

}
