package Board.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.Service.BoardPage;
import Board.Service.BoardService;
import Handler.CommandHandler;
import Notice.Exception.PageNotFoundException;

public class BoardHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<>();
		req.setAttribute("error", error);
			
		try {
			int pageNum = 1;
			if(req.getParameter("pageNum") != null && !req.getParameter("pageNum").equals("")) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}
			if(pageNum == 0) {
				throw new PageNotFoundException("페이지를 찾을 수 없습니다.");
			}
			System.out.println("페이지 : " + pageNum);
			
			BoardService boardService = BoardService.getInstance();
			BoardPage boardPage = boardService.getBoardList(pageNum);
			
			req.setAttribute("boardPage", boardPage);
			return "/WEB-INF/view/board/board.jsp";
		}catch(SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/Main.jsp");
		}catch(PageNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PageNotFound");
			error.put("from", "/Main.jsp");
		}
		return "/error.jsp";
	}

}
