package User.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserNotFoundException;
import User.Model.User;

public class FindLoginIdService {
	private static FindLoginIdService instance = new FindLoginIdService();
	private FindLoginIdService () {}
	public static FindLoginIdService getInstance() {
		return instance;
	}
	
	public List<User> checkId(String name, String email) throws UserNotFoundException {
		UserDAO userDAO = UserDAO.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			
			List<User> user = userDAO.selectByName(conn, name, email);
			if(user == null) {
				throw new UserNotFoundException("유저를 찾을수 없습니다.");
			}
		
			return user;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
