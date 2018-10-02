package User.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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
	
	public User findPwd(String name, String email, String loginId) throws UserNotFoundException, SQLException {
		UserDAO userDAO = UserDAO.getInstance();
		try(Connection conn = ConnectionProvider.getConnection()){
			User savedUser = userDAO.findPassword(conn, name, email, loginId);
			if(savedUser == null) {
				throw new UserNotFoundException("User를 찾을 수 없다");
			}
			
			return savedUser;
		}
	}
}
