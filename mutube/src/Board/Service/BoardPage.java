package Board.Service;

import java.util.List;

import Board.Model.Board;
import Post.Model.Post;

public class BoardPage {
	private List<Board> boardList;
	private int currentPage;
	private int totalPages;
	private int total;
	private int startPage;
	private int endPage;

	public BoardPage(List<Board> boardList, int currentPage, int total, int size, int blockSize) {
		// size는 한 페이지에 보여줄 글의 개수, blockSize는 하단 페에지의 링크블럭 개수
		this.boardList = boardList;
		this.currentPage = currentPage;
		this.total = total;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			startPage = currentPage / blockSize * blockSize + 1;
			// 페이지 번호와 블럭사이즈가 같아 페이지가 넘어가는 것을 막기 위함
			if ((currentPage % blockSize) == 0) {
				startPage -= blockSize;
			}
			endPage = startPage + (blockSize - 1);
			if (endPage > totalPages) {
				endPage = totalPages;
			}
		}

	}

	public boolean hasBoard() {
		return total > 0;
	}

	public List<Board> getBoardList() {
		return boardList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
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
