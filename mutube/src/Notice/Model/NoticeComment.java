package Notice.Model;

import java.time.LocalDateTime;

import Post.Model.Writer;

public class NoticeComment {
	private int commentId;
	private int noticeId;
	private int userId;
	private Writer writer;
	private String comment;
	private LocalDateTime write_date;
	private LocalDateTime update_date;

	public NoticeComment(int commentId, int noticeId, int userId, String comment, LocalDateTime write_date,
			LocalDateTime update_date) {
		this.commentId = commentId;
		this.noticeId = noticeId;
		this.userId = userId;
		this.comment = comment;
		this.write_date = write_date;
		this.update_date = update_date;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public NoticeComment(int commentId, int noticeId, int userId, String comment) {
		this.commentId = commentId;
		this.noticeId = noticeId;
		this.userId = userId;
		this.comment = comment;
	}
	public NoticeComment(int noticeId, int userId, String comment) {
		this.noticeId = noticeId;
		this.userId = userId;
		this.comment = comment;
	}


	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public int getCommentId() {
		return commentId;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public int getUserId() {
		return userId;
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
