package Notice.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;
import Notice.Exception.NoticeNotFoundException;
import Notice.Model.NoticeCommentPage;
import Notice.Model.NoticeData;
import Notice.Service.NoticeCommentListService;
import Notice.Service.ReadNoticeService;
import Post.Service.CommentListService;
import Post.Service.CommentPage;

public class ReadNoticeHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int noticeId = 0;
		int pageNum = 1;
		if(req.getParameter("noticeId") !=  null) {
			noticeId = Integer.parseInt(req.getParameter("noticeId"));
		}
		if(req.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		try {
			if(noticeId == 0) {
				throw new NoticeNotFoundException("해당 공지사항을 찾을 수 없습니다.");
			}
			
			ReadNoticeService readNoticeService = ReadNoticeService.getInstance();
			NoticeData noticeData = readNoticeService.getNotice(noticeId);
			
			String repleaceNoticeContent = noticeData.getNoticeContent().getContent().replaceAll("<","&lt").replaceAll(">", "&gt").replaceAll(" ", "&nbsp").replaceAll("\n", "<br>");
			noticeData.getNoticeContent().setContent(repleaceNoticeContent);
			
			NoticeCommentListService noticeCommentList = NoticeCommentListService.getInstance();
			NoticeCommentPage noticeCommentPage = noticeCommentList.commentList(pageNum, noticeId);
			noticeData.setNoticeCommentPage(noticeCommentPage);
			
			req.setAttribute("noticeData", noticeData);
			
			return "/WEB-INF/view/notice/readNotice.jsp";
		}catch(NoticeNotFoundException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath()+ "/Main.jsp");
			return null;
		}
	}
	
}
