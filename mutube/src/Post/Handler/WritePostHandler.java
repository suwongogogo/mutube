package Post.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.glass.ui.Application;
import com.sun.jmx.snmp.Enumerated;

import Handler.CommandHandler;
import Post.Model.File;
import Post.Model.Post;
import Post.Model.PostContent;
import Post.Model.PostData;
import Post.Model.Writer;
import Post.Service.WritePostService;
import User.Model.User;

public class WritePostHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/post/writePostForm.jsp";

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

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// 세션에 있는 유저의 정보와 파라미터로 값을 받고
		User loginUser = (User)req.getSession().getAttribute("loginUser");
		
		
		
		//넘겨받은 이미지 파일에 대한 정보를 저장
		String directory = req.getServletContext().getRealPath("/upload/");
		int maxSize = 1024*1024*100;
		String encoding = "utf-8";
		MultipartRequest multipartRequest = new MultipartRequest(req, directory, maxSize, encoding, 
				new DefaultFileRenamePolicy());
		
		// 글 정보는 Post, 내용은 PostContent 객체에 담아 WriteRequest를 생성.
		Post post = new Post(new Writer(loginUser.getUserId(),loginUser.getName()),multipartRequest.getParameter("title"),multipartRequest.getParameter("genre"),multipartRequest.getParameter("country"),multipartRequest.getParameter("instrument"));
		
		PostContent postContent = null;
		ArrayList<String> imageNames = new ArrayList<>();
		
		Enumeration names = multipartRequest.getFileNames();
		while(names.hasMoreElements()) {
			String fname = (String)names.nextElement();
			String sysFname = multipartRequest.getFilesystemName(fname);
			System.out.println(fname);
			multipartRequest.getFile(sysFname);
		}
		
		if(multipartRequest.getFile("image")!= null ) {
			String imageName = multipartRequest.getOriginalFileName("image");
			System.out.println(imageName);
		
//			postContent = new PostContent(multipartRequest.getParameter("content"),multipartRequest.getParameter("video_link"), imageNames);
		}else{
		
//			postContent = new PostContent(multipartRequest.getParameter("content"),multipartRequest.getParameter("video_link"));
		}
		postContent.trimLink();
		
		
		PostData writeReq = new PostData(post, postContent);
		

		// WriteRequest의 무결성 검사를 진행하고 이상있으면 다시 FORM_VIEW로 이동
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		post.writeValidate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}

		// WriteService를 이용한다.
		WritePostService writePostService = WritePostService.getInstance();
		int postId = 0;
		try {
			postId = writePostService.write(writeReq);
			resp.sendRedirect(req.getContextPath()+"/post/view?no="+postId);
			
		} catch (RuntimeException | SQLException e) {			
			e.printStackTrace();
			return FORM_VIEW;
		}
	
		return null;
	}

}
