package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;

public class RegisterService {
	private static RegisterService instance = new RegisterService();
	private RegisterService() {}
	public static RegisterService getInstance() {
		return instance;
	}
	
	public void register(User user) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.insert(conn, user.getLoginId(), user.getPassword(), user.getEmail(), user.getName());
		}
	}
	
	
}
