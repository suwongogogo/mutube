package Post.Model;

import java.time.LocalDateTime;

public class NoticeComment {
	private int commentId;
	private int noticeId;
	private int userId;
	private String comment;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	public NoticeComment(int commentId, int noticeId, int userId, String comment, LocalDateTime write_date,
			LocalDateTime update_date) {
		super();
		this.commentId = commentId;
		this.noticeId = noticeId;
		this.userId = userId;
		this.comment = comment;
		this.write_date = write_date;
		this.update_date = update_date;
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
