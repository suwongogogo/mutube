package Post.Model;

import java.time.LocalDateTime;

public class PostComment {
	private int commentId;
	private int postId;
	private int userId;
	private Writer writer;
	private String comment;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	private String wdateStr;
	private String udateStr;
	
	public PostComment(int postId, int userId, String comment, LocalDateTime write_date,
			LocalDateTime update_date) {
		this.postId = postId;
		this.userId = userId;
		this.comment = comment;
		this.write_date = write_date;
		this.update_date = update_date;
	}
	public PostComment(int postId, int userId, String comment, String wdateStr,
			String udateStr) {
		this.postId = postId;
		this.userId = userId;
		this.comment = comment;
		this.wdateStr = wdateStr;
		this.udateStr = udateStr;
	}

	public PostComment(int postId, int userId, String comment) {
		this.postId = postId;
		this.userId = userId;
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}
	
	public int getPostId() {
		return postId;
	}

	public int getUserId() {
		return userId;
	}
	public Writer getWriter() {
		return writer;
	}
	public void setWriter(Writer writer) {
		this.writer = writer;
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
