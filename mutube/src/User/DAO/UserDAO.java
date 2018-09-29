package User.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
	private static UserDAO instance = new UserDAO();
	
	private UserDAO() {}
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	public void insert(Connection conn, String loginId, String password, String email, String name) throws SQLException {
		String sql = "insert into User(loginId, password, email, name) values(?,?,?,?)";
		
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, loginId);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, name);
			
			pst.executeQuery();
		}
	}
}
