package Board.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Board.DAO.BoardDAO;
import Board.Model.Board;
import Connection.ConnectionProvider;

public class BoardService {
	private static BoardService instance = new BoardService();
	private BoardService() {}
	public static BoardService getInstance() {
		return instance;
	}
	
	private int size = 15;
	private int blockSize = 10;
	
	public BoardPage getBoardList(int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			BoardDAO boardDAO = BoardDAO.getInstance();
			int total = boardDAO.getBoardCount(conn);
			List<Board> boardList = boardDAO.getBoard(conn, (pageNum - 1)*size, size);
			return new BoardPage(boardList, pageNum, total, size, blockSize);
		}
	}
}
