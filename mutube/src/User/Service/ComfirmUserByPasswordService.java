package User.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class ComfirmUserByPasswordService {
	private static ComfirmUserByPasswordService instance = new ComfirmUserByPasswordService();

	private ComfirmUserByPasswordService() {
	}

	public static ComfirmUserByPasswordService getInstance() {
		return instance;
	}

	public User confirmUser(String loginId) throws SQLException, UserNotFoundException {
		UserDAO userDAO = UserDAO.getInstance();
		try (Connection conn = ConnectionProvider.getConnection()) {
			User user = userDAO.selectByLoginId(conn, loginId);
			
			if(user == null) {
				throw new UserNotFoundException("없는 유저입니다.");
			}
			return user;
		}
	}
}
