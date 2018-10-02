package User.Handler;

import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

import Email.MyAuthentication;
import Email.SHA256;
import Handler.CommandHandler;
import User.Exception.UserNotFoundException;
import User.Model.User;
import User.Service.FindPasswordService;


public class FindPasswordHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/findPasswordForm.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, resp);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, resp);
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse resp) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse resp) throws UserNotFoundException, SQLException {
		
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
		
		if(loginId.isEmpty() || loginId == null) {
			errors.put("loginId", true);
		}
		if(name.isEmpty() || name == null) {
			errors.put("name", true);
		}
		if(email.isEmpty() || email == null) {
			errors.put("email", true);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		FindPasswordService passwordService = FindPasswordService.getInstance();
		try {
			user = passwordService.findPwd(name, email, loginId);
			
			//이메일보내기에 필요한 정보들
			String host = "http://localhost/mutube/";
			String from = "fltndnjs1234@gmail.com";
			String to = email;
			String subject = "비밀번호 찾기";
			String content = "링크에 들어가서 비밀번호를 확인하세요!" + 
					"<a href='" + host + "passwordConfirmForm.jsp?email=" + new SHA256().getSHA256(to) + "'>비밀번호 확인</a>";
			String charSet = "utf-8";
			
			Properties p = System.getProperties();
	        p.put("mail.smtp.starttls.enable", "true");    
	        p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 호스트
	        p.put("mail.smtp.auth","true");
	        p.put("mail.smtp.port", "587");                 // gmail 포트

	        Authenticator auth = new MyAuthentication(); //구글 계정 인증
	        
	        // session 생성 및 MimeMessage생성
	        Session session = Session.getDefaultInstance(p, auth);
	        MimeMessage msg = new MimeMessage(session);
			
	        // 편지를 보낸 시간설정
	        msg.setSentDate(new Date());
	         
	        // 송신자 설정
	        InternetAddress fromIP = new InternetAddress(from);
	        fromIP =new InternetAddress(new String(from.getBytes(charSet), "8859_1") + "<발신자 구글 이메일@gmail.com>");
	        msg.setFrom(fromIP);
	        
	        // 수신자 설정
	        InternetAddress toIP = new InternetAddress(to);
	        msg.setRecipient(Message.RecipientType.TO, toIP);
	        
	        // 제목 설정
	        msg.setSubject(subject, charSet);
	        
	        // 내용 설정
	        msg.setContent(content, charSet);
	        
	        // 메일 송신
	        Transport.send(msg);
	        System.out.println("메일 전송 완료");
	        
	        req.setAttribute("userPassword", user.getPassword());
	        
	        return "/WEB-INF/view/findPasswordSuccess.jsp";
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("없는 유저 입니다.");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
