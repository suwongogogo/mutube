package Post.Model;

import java.util.ArrayList;
import java.util.Map;

public class PostContent {
	private int postId;
	private String content;
	private String video_link;
	private ArrayList<String> imageNames;
	private String imageNamesStr;
	
	public PostContent(int postId, String content, String video_link, ArrayList<String> imageNames) {
		this.postId = postId;
		this.content = content;
		this.video_link = video_link;
		this.imageNames = imageNames;
	}
	
	public PostContent(int postId, String content, String video_link, String imageNamesStr) {
		this.postId = postId;
		this.content = content;
		this.video_link = video_link;
		this.imageNamesStr = imageNamesStr;
	}
	
	public String getImageNamesStr() {
		return imageNamesStr;
	}

	public void setImageNamesStr(String imageNamesStr) {
		this.imageNamesStr = imageNamesStr;
	}

	
	public void setImageNames(ArrayList<String> imageNames) {
		this.imageNames = imageNames;
	}

	public PostContent(int postId, String content, String video_link) {
		this.postId = postId;
		this.content = content;
		this.video_link = video_link;
	}
	public PostContent(String content, String video_link, ArrayList<String> imageNames) {
		this.content = content;
		this.video_link = video_link;
		this.imageNames = imageNames ;
	}

	public ArrayList<String> getImageNames() {
		return imageNames;
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
