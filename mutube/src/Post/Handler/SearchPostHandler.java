package Post.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.PostNotFoundException;
import Post.Model.Post;
import Post.Service.SearchPostService;

public class SearchPostHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			String keyword = req.getParameter("keyword");
			
			keyword = "%"+keyword+"%"; 
			
			System.out.println(keyword);
			
			SearchPostService searchService = SearchPostService.getInstance();
			List<Post> postList = searchService.searchPost(keyword);
		
			Map<String, Boolean> errors = new HashMap<String, Boolean>();
			req.setAttribute("errors", errors);
			
			if(postList == null || postList.isEmpty()) {
				errors.put("postList", true);
			}
			
			req.setAttribute("postList", postList);
			
			return "/WEB-INF/view/post/searchResult.jsp";
		}catch(PostNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
