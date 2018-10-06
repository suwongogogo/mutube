package Admin.Service;

import java.util.List;

import User.Model.User;

public class PageINF {
	private List<User> userList;
	private int currentPage;
	private int totalPage;
	private int total;
	private int startPage;
	private int endPage;
	
	public PageINF(List<User> userList, int currentPage, int total, int size, int blockSize) {
		super();
		this.userList = userList;
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
	
	public boolean hasNoUser() {
		return total == 0;
	}
	
	public boolean hasUser() {
		return total > 0;
	}

	public List<User> getUserList() {
		return userList;
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
