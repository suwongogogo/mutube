package Board.Model;

import java.time.LocalDateTime;

import Post.Model.Writer;

public class Board {
	private int boardId;
	private String title;
	private Writer writer;
	private LocalDateTime write_date;
	private LocalDateTime update_date;
	private String wdateStr;
	private String udateStr;
	
	public Board(int boardId, String title, Writer writer, LocalDateTime write_date,
			LocalDateTime update_date) {
		this.boardId = boardId;
		this.title = title;
		this.writer = writer;
		this.write_date = write_date;
		this.update_date = update_date;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public LocalDateTime getWrite_date() {
		return write_date;
	}

	public void setWrite_date(LocalDateTime write_date) {
		this.write_date = write_date;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	public String getWdateStr() {
		return wdateStr;
	}

	public void setWdateStr(String wdateStr) {
		this.wdateStr = wdateStr;
	}

	public String getUdateStr() {
		return udateStr;
	}

	public void setUdateStr(String udateStr) {
		this.udateStr = udateStr;
	}
}
