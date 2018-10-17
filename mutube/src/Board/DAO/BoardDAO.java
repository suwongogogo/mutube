package Board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Board.Model.Board;
import Board.Service.BoardPage;
import Notice.Model.Notice;
import Post.Model.Post;
import Post.Model.Writer;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		return instance;
	}

	public void insertPostinBoard(Connection conn, Post post) throws SQLException {
		String sql = "insert into board(userId, loginId, name, title) values(?, ?, ?, ?)";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, post.getWriter().getUserId());
			pst.setString(2, post.getWriter().getLoginId());
			pst.setString(3, post.getWriter().getName());
			pst.setString(4, post.getTitle());
		}
	}
	
	public int insertNoticeinBoard(Connection conn, Notice notice) throws SQLException {
		String sql = "insert into board(userId, loginId, name, title) values(?, ?, ?, ?)";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, notice.getWriter().getUserId());
			pst.setString(2, notice.getWriter().getLoginId());
			pst.setString(3, notice.getWriter().getName());
			pst.setString(4, notice.getTitle());
			return pst.executeUpdate();
		}
	}
	
	public List<Board> getBoard(Connection conn, int startRow, int size) throws SQLException {
		String sql = "select boardId, userId, name, title, "
				+ "date_format(write_date, '%Y-%m-%d %H:%i'), date_format(update_date, '%Y-%m-%d %H:%i'), views, able "
				+ "from board "
				+ "where able=1 order by boardId desc limit ?, ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, startRow);
			pst.setInt(2, size);
			try (ResultSet rs = pst.executeQuery(sql)) {
				List<Board> boardList = new ArrayList<>();
				while (rs.next()) {
					boardList
							.add(new Board(rs.getString("title"), new Writer(rs.getInt("userId"), rs.getString("loginId"), rs.getString("name")),
									rs.getTimestamp("write_date").toLocalDateTime(),
									rs.getTimestamp("update_date").toLocalDateTime()));
				}
				return boardList;
			}
		}
	}

	public int getBoardCount(Connection conn) throws SQLException {
		String sql = "select count(*) from board where able = 1";
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			int Cnt = 0;
			if (rs.next()) {
				Cnt = rs.getInt(1);
			}
			return Cnt;
		}
	}
}
