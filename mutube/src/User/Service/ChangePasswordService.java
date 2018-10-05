package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;

public class ChangePasswordService {
	private static ChangePasswordService instance = new ChangePasswordService();
	private ChangePasswordService() {}
	public static ChangePasswordService getInstance() {
		return instance;
	}
	
	public User changePwd(int password, String loginId) throws SQLException {
		UserDAO userDAO = UserDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			userDAO.changePwd(conn, password, loginId);
			
			User user = userDAO.selectByLoginId(conn, loginId);
			return user;
		}
	}
	
	public User selectByUserId(int userId) throws SQLException {
		UserDAO userDAO = UserDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			User user = userDAO.selectByUserId(conn, userId);
			return user;
		}
	}

}
