package User.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class FindLoginIdService {
	private static FindLoginIdService instance = new FindLoginIdService();

	private FindLoginIdService() {
	}

	public static FindLoginIdService getInstance() {
		return instance;
	}

	public List<User> checkId(String name, String email) throws SQLException {
		UserDAO userDAO = UserDAO.getInstance();

		try (Connection conn = ConnectionProvider.getConnection()) {
			
			return userDAO.selectByName(conn, name, email);
		}
	}
}
