package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import com.mysql.cj.jdbc.exceptions.SQLError;

import Handler.CommandHandler;
import User.Exception.ComfirmPasswordNotMatchException;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.DeleteUserComfirmByPasswordService;

public class DeleteUserComfirmByPasswordHandler implements CommandHandler{
	private static final String FORM_VIEW = "/user/deleteUserComfirmByPassword";
	private static final String ERROR_PAGE = "/error.jsp";
	
	@Override
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		int userId = 0;
		if(req.getParameter("userId") != null) {
			userId = Integer.parseInt(req.getParameter("userId"));
		}
		
		String comfirmPassword = "";
		if(req.getParameter("comfirmPassword") != null) {
			comfirmPassword = req.getParameter("comfirmPassword");
		}
		if(comfirmPassword == null || comfirmPassword.isEmpty()) {
			errors.put("ComfirmPassword", true);
		}
		try {
			DeleteUserComfirmByPasswordService deleteUserService = DeleteUserComfirmByPasswordService.getInstance();
			User ComfirmUser = deleteUserService.getUser(userId);
			
			if(!ComfirmUser.getPassword().equals(comfirmPassword)) {
				throw new ComfirmPasswordNotMatchException("비밀번호 ");
			}
			if(userId == 0) {
				throw new UserNotFoundException("유저를 찾을 수 없습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "/myPage.jsp");
		} catch (ComfirmPasswordNotMatchException e) {
			e.printStackTrace();
			error.put("errorCode", "ComfirmPasswordNotMatch");
			error.put("from", "/myPage.jsp");
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "UserNotFound");
			error.put("from", "/myPage.jsp");
		}
		return ERROR_PAGE;
	}
	
}
