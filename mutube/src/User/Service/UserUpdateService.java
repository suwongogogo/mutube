package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class UserUpdateService {
	private static UserUpdateService instance = new UserUpdateService();

	private UserUpdateService() {
	}

	public static UserUpdateService getInstance() {
		return instance;
	}

	public void update(User user, int userId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			conn.setAutoCommit(false);
			UserDAO userDAO = UserDAO.getInstance();
			try {
				userDAO.update(conn, user, userId);
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
				throw new SQLException(e);
			}
		}
	}

}
