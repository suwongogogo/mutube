package User.Model;

import java.time.LocalDateTime;

public class User {
	private int userId;
	private String loginId;
	private String password;
	private String confirm_password;
	private String email;
	private String name;
	private LocalDateTime register_date;
	
	
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	private boolean authority;
	
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
		return password.equals("confirmPassword");
	}
	
}
