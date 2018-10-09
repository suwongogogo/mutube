package Post.Model;

import java.util.Map;

public class PostContent {
	private int postId;
	private String content;
	private String video_link;
	private String imageName;

	public PostContent(int postId, String content, String video_link, String imageName) {
		this.postId = postId;
		this.content = content;
		this.video_link = video_link;
		this.imageName = imageName;
	}
	public PostContent(String content, String video_link, String imageName) {
		this.content = content;
		this.video_link = video_link;
		this.imageName = imageName ;
	}

	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public PostContent(String content, String video_link) {
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

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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
}
