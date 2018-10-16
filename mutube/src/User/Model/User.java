package User.Model;

import java.time.LocalDateTime;
import java.util.Map;

public class User {
	private int userId;
	private String loginId;
	private String password;
	private String confirm_password;
	private String email;
	private String name;
	private LocalDateTime register_date;
	private boolean authority;
	
	
	public User() {};
	
	public User(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public User(int userId, String loginId, String password, String email, String name, LocalDateTime register_date,
			boolean authority) {
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.register_date = register_date;
		this.authority = authority;
	}
	
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getRegister_date() {
		return register_date;
	}
	public void setRegister_date(LocalDateTime register_date) {
		this.register_date = register_date;
	}
	public boolean isAuthority() {
		return authority;
	}
	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	
	public boolean matchPassword() {
		return password.equals(confirm_password);
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(loginId==null || loginId.isEmpty()) {
			errors.put("loginId", true);
		}
		if(password==null || password.isEmpty()) {
			errors.put("password", true);
		}
		if(confirm_password==null ||confirm_password.isEmpty()) {
			errors.put("confirmPassword", true);
		}				
		if(email==null|| email.isEmpty()) {
			errors.put("email", true);
		}
		if(name==null || name.isEmpty()) {
			errors.put("name", true);
		}
		if(email.indexOf("@") == -1) {
			System.out.println("email0");
			errors.put("emailForm", true);
		}
		if(email.indexOf(".com") == -1) {
			if(email.indexOf(".kr") == -1) {
				errors.put("emailForm", true);
			} else {}
		} else if(email.indexOf(".kr") == -1) {
			if(email.indexOf(".com") == -1) {
				errors.put("emailForm", true);
			} else {}
		}
	}
	public void loginValidate(Map<String, Boolean> errors) {
		if(loginId==null || loginId.isEmpty()) {
			errors.put("loginId", true);
		}
		if(password==null || password.isEmpty()) {
			errors.put("password", true);
		}
	}

}
