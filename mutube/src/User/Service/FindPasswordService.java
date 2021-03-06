package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class FindPasswordService {
	private static FindPasswordService instance = new FindPasswordService();
	private FindPasswordService() {}
	public static FindPasswordService getInstance() {
		return instance;
	}
	
	public User findPwd(User user) throws UserNotFoundException, SQLException {
		UserDAO userDAO = UserDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			User savedUser = userDAO.findPassword(conn, user);
			if(savedUser == null) {
				throw new UserNotFoundException("알 수 없는 사용자");
			}
			
			return savedUser;
		}
	}
}
