package User.Handler;

import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.SQLException;
import java.util.Date;
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
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Email.Gmail;
import Email.SHA256;
import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.FindPasswordService;

public class FindPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/findPasswordForm.jsp";

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
		System.out.println("폼");
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp)
			throws UserNotFoundException, SQLException {

		System.out.println("들어옴.");

		String loginId = req.getParameter("loginId");
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		User user = new User();
		user.setLoginId(loginId);
		user.setName(name);
		user.setEmail(email);

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

		FindPasswordService passwordService = FindPasswordService.getInstance();
		try {
			user = passwordService.findPwd(name, email, loginId);

			// 이메일보내기에 필요한 정보들
			String host = "http://localhost:8080/mutube/";
			String from = "fltndnjs1234@gmail.com";
			String to = email;
			String subject = "너의 비밀번호를 찾아라!";
			String content = "링크로 들어가서 비밀번호를 찾으세요" +
				"<a href='" + host + "passwordConfirmForm.jsp?email=" + to + "'>비밀번호 확인</a>";

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


			System.out.println("메일 전송 완료");

			req.setAttribute("userPassword", user.getPassword());

			return "/WEB-INF/view/findPasswordSuccess.jsp";
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("없는 유저 입니다.");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
