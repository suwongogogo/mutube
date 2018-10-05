package User.Service;


import java.sql.Connection;
import java.sql.SQLException;
import User.Exception.PasswordNotMatchException;
import User.Exception.UserNotFoundException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;

public class LoginService {
	private static LoginService instance = new LoginService();
	private LoginService() {}
	public static LoginService getInstance() {
		return instance;
	}
	
	public User login(User user) throws SQLException, PasswordNotMatchException, UserNotFoundException {
		UserDAO userDAO = UserDAO.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			User checkUser = userDAO.selectByLoginId(conn, user.getLoginId());
			
			if(checkUser==null) {
				throw new UserNotFoundException("유저를 찾을 수 없습니다.");
			}
			if(!user.getPassword().equals(checkUser.getPassword())) {
				throw new PasswordNotMatchException("올바르지 않은 패스워드");
			}
			System.out.println(checkUser.getLoginId());
			return checkUser;
		}
	}
}
