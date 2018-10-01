package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;

public class LoginService {
	private static LoginService instance = new LoginService();
	private LoginService() {}
	public static LoginService getInstance() {
		return instance;
	}
	
	public void login(User user) throws SQLException {
		UserDAO userDAO = UserDAO.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			User checkUser = userDAO.selectByLoginId(conn, user.getLoginId());
			
			if(user.getLoginId() == checkUser.getLoginId()) {
				
			}
		}
	}
}
