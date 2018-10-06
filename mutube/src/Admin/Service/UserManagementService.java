package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Admin.DAO.AdminDAO;
import Connection.ConnectionProvider;
import User.DAO.UserDAO;
import User.Model.User;
import User.Service.ChangePasswordService;

public class UserManagementService {
	private static UserManagementService instance = new UserManagementService();
	private UserManagementService() {}
	public static UserManagementService getInstance() {
		return instance;
	}
	
	private int size = 7;
	private int blockSize = 6;
	
	public UserPageINF getUserList(int pageNum) throws SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			AdminDAO adminDAO = AdminDAO.getInstance();
			int total = adminDAO.getUserCount(conn);
			List<User> userList = adminDAO.userList(conn, (pageNum - 1)*size, size);
			
			return new UserPageINF(userList, pageNum, total, size, blockSize);
		}
	}
}
