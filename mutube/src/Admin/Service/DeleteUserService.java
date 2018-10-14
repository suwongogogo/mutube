package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;

import Admin.DAO.AdminDAO;
import Connection.ConnectionProvider;
import User.Exception.UserNotFoundException;

public class DeleteUserService {
	private static DeleteUserService instance = new DeleteUserService();
	private DeleteUserService() {}
	public static DeleteUserService getInstance() {
		return instance;
	}
	
	public void deleteUser(int userId) throws UserNotFoundException, SQLException {
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			AdminDAO adminDAO = AdminDAO.getInstance();
			int count = 0;
			try {
				count = adminDAO.deleteUser(conn, userId);
				if(count > 0) {
					throw new UserNotFoundException("유저 삭제에 실패하였습니다.");
				}
				
				conn.commit();
				
			}catch(SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}			
			
		}
	}
}
