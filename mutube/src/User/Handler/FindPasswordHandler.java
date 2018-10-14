package User.Handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Email.Gmail;
import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.FindPasswordService;

public class FindPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/user/findPasswordForm.jsp";
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

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp)	throws IOException {

		Map<String, String> error = new HashMap<String, String>();
		req.setAttribute("error", error);
		
		String loginId = req.getParameter("loginId");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		if (loginId.isEmpty() || loginId == null) {
			errors.put("loginId", true);
		}
		if (name.isEmpty() || name == null) {
			errors.put("name", true);
		}
		if (email.isEmpty() || email == null) {
			errors.put("email", true);
		}
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		User user = new User();
		user.setLoginId(loginId);
		user.setName(name);
		user.setEmail(email);

		FindPasswordService passwordService = FindPasswordService.getInstance();
		try {
			user = passwordService.findPwd(user);

			// 이메일보내기에 필요한 정보들
			String from = "fltndnjs1234@gmail.com";
			String to = email;
			String subject = "Mutube:: 회원님의 비밀번호를 알려드립니다.";
			String content = "비밀 번호는 : " + user.getPassword()+"<br><br>"
					+ "보안 유지를 위해 로그인 후 꼭 비밀번호를 바꿔주시기 바랍니다.";
				

			// SMTP에 접속하기 위한 정보를 기입합니다.

			Properties p = new Properties();
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.host", "smtp.googlemail.com");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");
			
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF-8");
			Transport.send(msg);

			return "/WEB-INF/view/user/findPasswordSuccess.jsp";
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			error.put("errorCode", "userNotFound");
			error.put("from", "user/findPassword");
		} catch (MessagingException e) {
			e.printStackTrace();
			error.put("errorCode", "message");
			error.put("from", "user/findPassword");
		} catch (SQLException e) {
			e.printStackTrace();
			error.put("errorCode", "dbError");
			error.put("from", "user/findPassword");
		}

		return ERROR_PAGE;

	}

}
