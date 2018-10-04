package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class FindPasswordByEmail {
	private static FindPasswordByEmail instance = new FindPasswordByEmail();

	private FindPasswordByEmail() {
	}

	public static FindPasswordByEmail getInstance() {
		return instance;
	}

	public String findPasswordByEmail(String email) throws SQLException, UserNotFoundException {

		try (Connection conn = ConnectionProvider.getConnection()) {
			UserDAO userDAO = UserDAO.getInstance();
			User user = userDAO.selectByEmail(conn, email);
			
			if(user == null) {
				throw new UserNotFoundException("없는 유저입니다.");
			}
			
			return user.getPassword();
		}
	}
}
