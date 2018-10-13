package Notice.Model;


import Post.Model.File;

public class NoticeData {
	private Notice notice;
	private NoticeContent noticeContent;
	private NoticeCommentPage noticeCommentPage;
	private File image;
	
	public NoticeData(Notice notice, NoticeContent noticeContent, File image) {
		super();
		this.notice = notice;
		this.noticeContent = noticeContent;
		this.image = image;
	}
	
	public NoticeData(Notice notice, NoticeContent noticeContent) {
		super();
		this.notice = notice;
		this.noticeContent = noticeContent;
	}
	
	public NoticeData() {
		// TODO Auto-generated constructor stub
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public NoticeContent getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(NoticeContent noticeContent) {
		this.noticeContent = noticeContent;
	}

	public NoticeCommentPage getNoticeCommentPage() {
		return noticeCommentPage;
	}

	public void setNoticeCommentPage(NoticeCommentPage noticeCommentPage) {
		this.noticeCommentPage = noticeCommentPage;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
}
