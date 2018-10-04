package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class UserDeleteService {
	private static UserDeleteService instance = new UserDeleteService();
	private UserDeleteService() {}
	public static UserDeleteService getInstance() {
		return instance;
	}
	
	public void delete(int userId) throws SQLException, UserNotFoundException {
		UserDAO userDAO = UserDAO.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()){
			User user = userDAO.selectByUserId(conn, userId);
			
			if(user == null) {
				throw new UserNotFoundException("없는 유저입니다.");
			}
			
			userDAO.delete(conn, userId);
		}
		
	}
}
