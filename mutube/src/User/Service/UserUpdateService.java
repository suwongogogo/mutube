package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class UserUpdateService {
	private static UserUpdateService instance = new UserUpdateService();
	private UserUpdateService () {}
	public static UserUpdateService getInstance(){
		return instance;
	}
	
	public void update(User user) throws SQLException, UserNotFoundException {
		try(Connection conn = ConnectionProvider.getConnection()){
			UserDAO userDAO = UserDAO.getInstance();
			
			userDAO.update(conn, user);
		}
	}
	
	public User selectByUserId(int userId) throws SQLException, UserNotFoundException {
		try(Connection conn = ConnectionProvider.getConnection()){
			UserDAO userDAO = UserDAO.getInstance();
			User user = userDAO.selectByUserId(conn, userId);
			
			if(user == null) {
				throw new UserNotFoundException("없는 유저입니다.");
			}
			
			return user;
		}
	}
}
