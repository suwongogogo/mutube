package Post.Model;

import Post.Service.CommentPage;

//원래 WriteRequest였음
public class PostData {
	private Post post;
	private PostContent postContent;
	private CommentPage commentPage;
	private File image;
	
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

	public File getImage() {
		return image;
	}

	public void getImage(File image) {
		this.image = image;
	}

	public void setPostContent(PostContent postContent) {
		this.postContent = postContent;
	}

	public PostData(Post post, PostContent postContent, File image) {
		this.post = post;
		this.postContent= postContent;
		this.image = image;
	}
	public PostData(Post post, PostContent postContent) {
		this.post = post;
		this.postContent= postContent;
	}

	public PostData() {}

	

}
