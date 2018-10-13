package Post.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.MultipartParser;
import com.sun.glass.ui.Application;
import com.sun.net.httpserver.HttpsParameters;

import java.io.File;
import Handler.CommandHandler;
import Post.Exception.WritePostFailException;
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
		User loginUser = (User) req.getSession().getAttribute("loginUser");

		// 넘겨받은 이미지 파일에 대한 정보를 저장
		String directory = req.getServletContext().getRealPath("/upload/");
		int maxSize = 1024 * 1024 * 5;
		String encoding = "utf-8";

		ArrayList<String> imageNames = new ArrayList<>();
		Map<String, String> params = new HashMap<>();

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		if (ServletFileUpload.isMultipartContent(req)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {// 파일일때..
						String name = item.getName();
						if (!name.equals("")) {
							if (name.endsWith(".jpg") ||name.endsWith(".png")||name.endsWith(".PNG") || name.endsWith(".gif")
									|| name.endsWith(".jpeg")) {
								String date = sdf.format(new Date());
								item.write(new File(directory + date + name));
								imageNames.add(date + name);
							}else {
								errors.put("imageType", true);
							}
						}
					} else {
						String name = item.getFieldName();
						String value = item.getString("UTF-8");
						params.put(name, value);
						System.out.println(name + ", " + value);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (params.get("title").trim().equals("")) {
			errors.put("title", true);
			return FORM_VIEW;
		}
		// 글 정보는 Post, 내용은 PostContent 객체에 담아 WriteRequest를 생성.
		Post post = new Post(new Writer(loginUser.getUserId(), loginUser.getLoginId(), loginUser.getName()), params.get("title"),
				params.get("genre"), params.get("country"), params.get("instrument"));
	
		System.out.println(loginUser.getLoginId());
		PostContent postContent = null;
		if (!imageNames.isEmpty()) {

			postContent = new PostContent(params.get("content"), params.get("video_link"), imageNames);
		} else {

			postContent = new PostContent(params.get("content"), params.get("video_link"));
		}
		postContent.trimLink();

		PostData writeReq = new PostData(post, postContent);

		// WriteRequest의 무결성 검사를 진행하고 이상있으면 다시 FORM_VIEW로 이동

		post.writeValidate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		// WriteService를 이용한다.
		WritePostService writePostService = WritePostService.getInstance();
		int postId = 0;
		try {
			postId = writePostService.write(writeReq);
			resp.sendRedirect(req.getContextPath() + "/post/list");

		} catch(WritePostFailException e) {
			e.printStackTrace();
			return FORM_VIEW;
		} catch (RuntimeException | SQLException e) {
			e.printStackTrace();
			return FORM_VIEW;
		}

		return null;
	}

}
