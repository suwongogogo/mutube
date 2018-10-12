package Admin.Service;

import java.sql.Connection;
import java.sql.SQLException;

import com.sun.corba.se.pept.transport.ConnectionCache;

import Admin.DAO.AdminDAO;
import Admin.Exception.DeleteFailException;
import Connection.ConnectionProvider;

public class DeleteUserService {
	private static DeleteUserService instance = new DeleteUserService();
	private DeleteUserService() {}
	public static DeleteUserService getInstance() {
		return instance;
	}
	
	public void deleteUser(int userId) throws SQLException, DeleteFailException {
		try(Connection conn = ConnectionProvider.getConnection()){
			AdminDAO adminDAO = AdminDAO.getInstance();
			int count = adminDAO.deleteUser(conn, userId);
			if(count > 0) {
				throw new DeleteFailException("유저 삭제에 실패하였습니다.");
			}
		}
	}
}
