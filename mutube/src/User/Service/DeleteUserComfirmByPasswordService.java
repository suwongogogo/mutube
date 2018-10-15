package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;

public class DeleteUserComfirmByPasswordService {
	private static DeleteUserComfirmByPasswordService instance = new DeleteUserComfirmByPasswordService();
	private DeleteUserComfirmByPasswordService() {}
	public static DeleteUserComfirmByPasswordService getInstance() {
		return instance;
	}
	
	public User getUser(int userId) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			UserDAO userDAO = UserDAO.getInstance();
			User user = userDAO.selectByUserId(conn, userId);
			
			return user;
		}
	}
}
