package Post.Model;

import java.time.LocalDateTime;

public class Notice {
	private int noticeId;
	private Writer wrtier;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	private int views;
	private boolean able;
	
	public Notice(int noticeId, Writer wrtier, LocalDateTime write_date, LocalDateTime update_date, int views,
			boolean able) {
		super();
		this.noticeId = noticeId;
		this.wrtier = wrtier;
		this.write_date = write_date;
		this.update_date = update_date;
		this.views = views;
		this.able = able;
	}

	public Notice(Writer wrtier) {
		super();
		this.wrtier = wrtier;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public Writer getWrtier() {
		return wrtier;
	}

	public void setWrtier(Writer wrtier) {
		this.wrtier = wrtier;
	}

	public LocalDateTime getWrite_date() {
		return write_date;
	}

	public void setWrite_date(LocalDateTime write_date) {
		this.write_date = write_date;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public boolean isAble() {
		return able;
	}

	public void setAble(boolean able) {
		this.able = able;
	}

	
	
}
