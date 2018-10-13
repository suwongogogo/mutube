package Notice.Model;

import java.time.LocalDateTime;
import java.util.Map;

import Post.Model.Writer;

public class Notice {
	private int noticeId;
	private Writer writer;
	private String title;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	private String wdateStr;
	private String udateStr;
	private int views;
	private boolean able;

	
	public Notice(int noticeId, Writer writer, String title) {
		super();
		this.noticeId = noticeId;
		this.writer = writer;
		this.title = title;
	}

	public Notice(Writer writer, String title) {
		super();
		this.writer = writer;
		this.title = title;
	}

	public Notice(int noticeId, Writer writer, String title, LocalDateTime write_date, LocalDateTime update_date,
			int views, boolean able) {
		this.noticeId = noticeId;
		this.writer = writer;
		this.title = title;
		this.write_date = write_date;
		this.update_date = update_date;
		this.views = views;
		this.able = able;
	}
	
	public Notice(int noticeId, Writer writer, String title, String wdateStr, String udateStr,
			int views, boolean able) {
		this.noticeId = noticeId;
		this.writer = writer;
		this.title = title;
		this.wdateStr = wdateStr;
		this.udateStr = udateStr;
		this.views = views;
		this.able = able;
	}

	public Notice(Writer writer) {
		super();
		this.writer = writer;
	}

	public void writeValidate(Map<String, Boolean> errors) {
		if(title==null||title.isEmpty()) {
			errors.put("title", true);
		}
		
	}
		
	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWrtier(Writer wrtier) {
		this.writer = wrtier;
	}

	public LocalDateTime getWrite_date() {
		return write_date;
	}

	public void setWrite_date(LocalDateTime write_date) {
		this.write_date = write_date;
	}

	public String getWdateStr() {
		return wdateStr;
	}

	public String getUdateStr() {
		return udateStr;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
