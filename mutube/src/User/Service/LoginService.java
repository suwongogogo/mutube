package User.Service;

import User.Model.User;

public class LoginService {
	private static LoginService instance = new LoginService();
	private LoginService() {}
	public static LoginService getInstance() {
		return instance;
	}
	
	public void login(User user) {
		
	}
}
