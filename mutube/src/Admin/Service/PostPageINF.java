package Admin.Service;

import java.util.List;

import Post.Model.Post;
import User.Model.User;

public class PostPageINF {
	private List<Post> postList;
	private int currentPage;
	private int totalPage;
	private int total;
	private int startPage;
	private int endPage;
	
	public PostPageINF(List<Post> postList, int currentPage, int total, int size, int blockSize) {
		super();
		this.postList = postList;
		this.currentPage = currentPage;
		this.total = total;
		if(total == 0) {
			totalPage = 0;
			startPage = 0;
			endPage = 0;
		}else {
			totalPage = total/size;
			if(total % size > 0) {
				totalPage++;
			}
		}
		
		startPage = currentPage / blockSize * blockSize + 1;
		if((currentPage % blockSize) == 0) {
			startPage -= blockSize;
		}
		
		endPage = startPage + (blockSize - 1);
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	public boolean hasPost() {
		return total > 0;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotal() {
		return total;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
}
