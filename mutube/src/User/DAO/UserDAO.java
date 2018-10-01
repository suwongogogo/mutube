package User.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import User.Model.User;

public class UserDAO {
	private static UserDAO instance = new UserDAO();
	
	private UserDAO() {}
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	public void insert(Connection conn, String loginId, String password, String email, String name) throws SQLException {
		String sql = "insert into User(loginId, password, email, name) values(?,  HEX(aes_encrypt(?,'mutube')) , ?, ?)";
		
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, loginId);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, name);
			
			pst.executeUpdate();
		}
	}
	
	public User selectByLoginId(Connection conn, String loginId) throws SQLException {
		String sql = "select userId, loginId, cast(aes_decrypt(unhex(password),'mutube') as char(50)) as password, email, name, register_date, authority"
				+ " from User where loginId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, loginId);
			
			try(ResultSet rs = pst.executeQuery()){
				User user = null;
				if(rs.next()) {
					user = new User(rs.getInt("userId"), rs.getString("loginId"), rs.getString("password").trim(), 
							rs.getString("email"), rs.getString("name"), rs.getTimestamp("register_date").toLocalDateTime(),
							rs.getBoolean("authority"));
				}
				return user;
			}
		}
	}
	
	public List<User> selectByName(Connection conn, String name, String email) throws SQLException {
		String sql = "select * from User where name = ? and email = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, name);
			pst.setString(2, email);
			try(ResultSet rs = pst.executeQuery()){
				User user = null;
				List<User> list = new ArrayList<>();
				if(rs.next()) {
					
					user = new User(rs.getInt("userId"), rs.getString("loginId"), rs.getString("password").trim(), 
							rs.getString("email"), rs.getString("name"), rs.getTimestamp("register_date").toLocalDateTime(),
							rs.getBoolean("authority"));
					list.add(user);
				}else {
					list = Collections.emptyList();
				}
				return list;
			}
		}
	}
	
	public User findPassword(Connection conn, String name, String email, String loginId) throws SQLException {
		String sql = "select userId, loginId, cast(aes_decrypt(unhex(password),'mutube') as char(50)) as password, email, name, register_date, authority"
				+ " from User where name = ? and email = ? and loginId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, loginId);
			try(ResultSet rs = pst.executeQuery()){
				User user = null;
				if(rs.next()) {
					user = new User(rs.getInt("userId"), rs.getString("loginId"), rs.getString("password").trim(), 
							rs.getString("email"), rs.getString("name"), rs.getTimestamp("register_date").toLocalDateTime(),
							rs.getBoolean("authority"));
				}
				
				return user;
			}
		}
	}
}
