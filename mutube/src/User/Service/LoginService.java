package User.Service;

import java.sql.Connection;
import java.sql.SQLException;
import User.Exception.PasswordNotMatchException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;

public class LoginService {
	private static LoginService instance = new LoginService();
	private LoginService() {}
	public static LoginService getInstance() {
		return instance;
	}
	
	public void login(User user) throws SQLException, PasswordNotMatchException {
		UserDAO userDAO = UserDAO.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			User checkUser = userDAO.selectByLoginId(conn, user.getLoginId());
			
			if(user.getPassword() != checkUser.getPassword()) {
				
				throw new PasswordNotMatchException("올바르지 않은 패스워드");
			}
			
		}
	}
}
