package User.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Exception.UserAlreadyExistException;
import User.Model.User;

public class RegisterService {
	private static RegisterService instance = new RegisterService();
	private RegisterService() {}
	public static RegisterService getInstance() {
		return instance;
	}
	
	public void register(User user) throws SQLException, UserAlreadyExistException {
		
		// 아이디가 이미 있는지 검사하고 회원가입 진행
		try(Connection conn = ConnectionProvider.getConnection()){
			
			conn.setAutoCommit(false);
			
			UserDAO userDAO = UserDAO.getInstance();
			
			User dbuser = userDAO.selectByLoginId(conn, user.getLoginId());
			
			if(dbuser!=null) {
				conn.rollback();
				throw new UserAlreadyExistException("이미 있는 아이디");
			}
			
			userDAO.insert(conn, user.getLoginId(), user.getPassword(), user.getEmail(), user.getName());
			conn.commit();
		}
	}
	

	
	
}
