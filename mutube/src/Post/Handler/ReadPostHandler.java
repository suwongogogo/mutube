package Post.Handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Post.Exception.PostNotFoundException;
import Post.Model.CommentPage;
import Post.Model.PostData;
import Post.Service.CommentListService;
import Post.Service.ReadPostService;

public class ReadPostHandler implements CommandHandler {
	private static final String ERROR_PAGE = "/error.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		// postId를 매개변수로 받아서 해당하는 게시글 정보를 조회 후 request의 속성값으로 등록하고 화면을 전환
		int postId = 0;
		int pageNum = 1;
		if (req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		if (req.getParameter("no") != null) {
			postId = Integer.parseInt(req.getParameter("no"));
		}
		try {
			if (postId == 0) {
				throw new PostNotFoundException("유효하지 않은 게시글");
			}

			ReadPostService readPostService = ReadPostService.getInstance();
			PostData postData = readPostService.getPost(postId);
			
			System.out.println(pageNum);
			if(postData.getPostContent().getContent() != null) {
				String replacedContent = postData.getPostContent().getContent().replaceAll("<","&lt").replaceAll(">", "&gt").replaceAll(" ", "&nbsp").replaceAll("\n", "<br>");
				postData.getPostContent().setContent(replacedContent);
			}

			
			CommentListService commentList = CommentListService.getInstance();
			CommentPage commentPage = commentList.commentList(pageNum, postId);
			postData.setCommentPage(commentPage);
			
			
			req.setAttribute("postData", postData);
			
			return "/WEB-INF/view/post/readPost.jsp";

		} catch (PostNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "PostNotFound");
			error.put("from", "/Main.jsp");
			return ERROR_PAGE;
		}
	}

}
