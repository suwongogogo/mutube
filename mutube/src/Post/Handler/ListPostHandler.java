package Post.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Post.Service.ListPostService;
import Post.Service.PostPage;



public class ListPostHandler implements Handler.CommandHandler{

	//페이지 번호를 파라미터로 받고 서비스를 이용해서 페이지 목록을 받아옴
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ListPostService postService = ListPostService.getInstance();
		String pageNumStr = req.getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr != null) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		PostPage postPage = postService.getArticlePage(pageNum);
		req.setAttribute("postPage", postPage);
		
		return "/WEB-INF/view/list.jsp";
	}
	
}
