package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class ConfirmUserByPasswordService {
	private static ConfirmUserByPasswordService instance = new ConfirmUserByPasswordService();

	private ConfirmUserByPasswordService() {
	}

	public static ConfirmUserByPasswordService getInstance() {
		return instance;
	}

	public User confirmUser(String loginId) throws SQLException, UserNotFoundException {
		UserDAO userDAO = UserDAO.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			User user = userDAO.selectByLoginId(conn, loginId);
			return user;
		}
	}
}
