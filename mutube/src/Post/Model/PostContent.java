package Post.Model;

public class PostContent {
	private int postId;
	private String content;
	
	public PostContent(int postId, String content) {
		super();
		this.postId = postId;
		this.content = content;
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
}
