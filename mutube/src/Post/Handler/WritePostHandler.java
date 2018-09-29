package Post.Handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.CommandHandler;

public class WritePostHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/writeForm.jsp";

	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		// 세션에 있는 유저의 정보와 파라미터로 받을 Content와 Title값을 WriteRequest에 담는다.
		
		// WriteRequest에 있는 오류체크를 해 이상있으면 다시 FORM_VIEW로 이동
		
		// WriteService를 이용한다.
		
		return "/WEB-INF/view/writeSuccess.jsp";
	}

}
