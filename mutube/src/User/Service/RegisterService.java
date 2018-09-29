package User.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;

public class RegisterService {
	private static RegisterService instance = new RegisterService();
	private RegisterService() {}
	public static RegisterService getInstance() {
		return instance;
	}
	
	public void register(User user) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			UserDAO userDAO = UserDAO.getInstance();
			userDAO.insert(conn, user.getLoginId(), user.getPassword(), user.getEmail(), user.getName());
		}
	}
	
	public void validate(Map<String, Boolean> errors, User user) {
		if(user.getLoginId()==null || user.getLoginId().isEmpty()) {
			errors.put("loginId", true);
		}
		if(user.getPassword()==null || user.getPassword().isEmpty()) {
			errors.put("password", true);
		}
		if(user.getConfirm_password()==null ||user.getConfirm_password().isEmpty()) {
			errors.put("confirmPassword", true);
		}				
		if(user.getEmail()==null|| user.getEmail().isEmpty()) {
			errors.put("email", true);
		}
		if(user.getName()==null || user.getName().isEmpty()) {
			errors.put("name", true);
		}
	}
	
	
}
