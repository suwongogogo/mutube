package Post.Handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Model.PostPage;
import Post.Service.SearchPostService;

public class SearchPostHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		try {
			SearchPostService searchService = SearchPostService.getInstance();
			
			Map<String, Boolean> errors = new HashMap<String, Boolean>();
			req.setAttribute("errors", errors);
			
			String keyword = req.getParameter("keyword");

			String category = req.getParameter("category");
			String pageNumStr = req.getParameter("pageNum");

			System.out.println("key"+keyword);
			System.out.println("ca"+category);
				 
			

			int pageNum = 1;
			if (pageNumStr != null) {
				pageNum = Integer.parseInt(pageNumStr);
			}
			if (keyword != null) {
				keyword = "%" + keyword + "%";
			}
			if (category != null) {
				if (category.trim().equalsIgnoreCase("genre")) {
					PostPage postPage = searchService.searchGenre(keyword, pageNum);

					if (postPage == null) {
						errors.put("postPage", true);
					}

					req.setAttribute("postPage", postPage);
					return "/WEB-INF/view/post/searchResult.jsp";
				}
				if (category.trim().equalsIgnoreCase("instrument")) {
					PostPage postPage = searchService.searchInstrument(keyword, pageNum);

					if (postPage == null) {
						errors.put("postPage", true);
					}

					req.setAttribute("postPage", postPage);
					return "/WEB-INF/view/post/searchResult.jsp";
				}
				if (category.trim().equalsIgnoreCase("country")) {
					PostPage postPage = searchService.searchCountry(keyword, pageNum);

					if (postPage == null) {
						errors.put("postPage", true);
					}

					req.setAttribute("postPage", postPage);
					return "/WEB-INF/view/post/searchResult.jsp";
				}
			}
			PostPage postPage = searchService.searchTitle(keyword, pageNum);

			if (postPage == null) {
				errors.put("postPage", true);
			}

			req.setAttribute("postPage", postPage);
			
			return "/WEB-INF/view/post/searchResult.jsp";
		} catch (PostNotFoundException e) {
			error.put("errorCode", "PostNotFound");
			error.put("from", "/post/searchResult");
		}catch (SQLException e) {
			error.put("errorCode", "dbError");
			error.put("from", "/post/searchResult");
		}
		return null;
	}

}
