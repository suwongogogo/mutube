package Post.Model;

//원래 WriteRequest였음
public class PostData {
	private Post post;
	private PostContent postContent;
	private CommentPage commentPage;
	
	public CommentPage getCommentPage() {
		return commentPage;
	}

	public void setCommentPage(CommentPage commentPage) {
		this.commentPage = commentPage;
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

	public PostData(Post post, PostContent postContent) {
		this.post = post;
		this.postContent= postContent;
	}

	public PostData() {}

	

}
