package Notice.Model;

import java.util.ArrayList;

public class NoticeContent {
	private int noticeId;
	private String content;
	private String video_link;
	private ArrayList<String> imageNames;
	private String imageNamesStr;
	
	public NoticeContent(int noticeId, String content, String video_link, ArrayList<String> imageNames) {
		super();
		this.noticeId = noticeId;
		this.content = content;
		this.video_link = video_link;
		this.imageNames = imageNames;
	}
	
	public NoticeContent(int noticeId, String content, String video_link, String imageNamesStr) {
		super();
		this.noticeId = noticeId;
		this.content = content;
		this.video_link = video_link;
		this.imageNamesStr = imageNamesStr;
	}
	
	public NoticeContent(String content, String video_link) {
		super();
		this.content = content;
		this.video_link = video_link;
	}

	public NoticeContent(String content, String video_link, ArrayList<String> imageNames) {
		super();
		this.content = content;
		this.video_link = video_link;
		this.imageNames = imageNames;
	}

	public NoticeContent(int noticeId, String content, String video_link) {
		super();
		this.noticeId = noticeId;
		this.content = content;
		this.video_link = video_link;
	}

	public String trimLink() {
		if (video_link != null) {
			if (video_link.length() < 20) {
				video_link = null;
			} else if (video_link.length() < 40) {
				video_link = video_link.substring(video_link.lastIndexOf('/') + 1);
			} else {
				video_link = video_link.substring(video_link.lastIndexOf('=') + 1);
			}
		}
		return video_link;
	}
		
	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideo_link() {
		return video_link;
	}

	public void setVideo_link(String video_link) {
		this.video_link = video_link;
	}

	public ArrayList<String> getImageNames() {
		return imageNames;
	}

	public void setImageNames(ArrayList<String> imageNames) {
		this.imageNames = imageNames;
	}

	public String getImageNamesStr() {
		return imageNamesStr;
	}

	public void setImageNamesStr(String imageNamesStr) {
		this.imageNamesStr = imageNamesStr;
	}
}
