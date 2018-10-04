package Post.Model;

import java.util.Map;

//원래 WriteRequest였음
public class PostData {
	private Post post;
	private PostContent postContent;


	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public PostContent getPostContent() {
		return postContent;
	}

	public void setPostContent(PostContent postContent) {
		this.postContent = postContent;
	}

	public PostData(Post post, PostContent postContent) {
		this.post = post;
		this.postContent= postContent;
	}
	

}
