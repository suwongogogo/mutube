package Request;

import java.util.Map;

import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.Writer;

public class WriteRequest {
	private Writer writer;
	private Post post;
	private PostContent postContent;

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

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

	public WriteRequest(Post post, PostContent postContent) {
		this.post = post;
		this.postContent= postContent;
	}
	

}
