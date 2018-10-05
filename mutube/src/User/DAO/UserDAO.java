package User.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import User.Model.User;

public class UserDAO {
	private static UserDAO instance = new UserDAO();

	private UserDAO() {
	}

	public static UserDAO getInstance() {
		return instance;
	}

	public void insert(Connection conn, String loginId, String password, String email, String name)
			throws SQLException {
		String sql = "insert into User(loginId, password, email, name) values(?,  HEX(aes_encrypt(?,'mutube')) , ?, ?)";

		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, loginId);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, name);

			pst.executeUpdate();
		}
	}
	
	public User selectByUserId(Connection conn, int userId) throws SQLException {
		String sql = "select userId, loginId, cast(aes_decrypt(unhex(password),'mutube') as char(50)) as password, email, name, register_date, authority"
				+ " from User where userId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, userId);
			try (ResultSet rs = pst.executeQuery()) {
				User user = null;
				if (rs.next()) {
					user = getUserModel(rs);
				}
				return user;
			}
		}
	}
	
	public User selectByLoginId(Connection conn, String loginId) throws SQLException {
		String sql = "select userId, loginId, cast(aes_decrypt(unhex(password),'mutube') as char(50)) as password, email, name, register_date, authority"
				+ " from User where loginId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, loginId);

			try (ResultSet rs = pst.executeQuery()) {
				User user = null;
				if (rs.next()) {
					user = getUserModel(rs);
				}
				return user;
			}
		}
	}

	public List<User> selectByName(Connection conn, String name, String email) throws SQLException {
		String sql = "select * from User where name = ? and email = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, name);
			pst.setString(2, email);
			try (ResultSet rs = pst.executeQuery()) {
				User user = null;
				List<User> list = new ArrayList<>();
				if (rs.next()) {

					user = getUserModel(rs);
					list.add(user);
				} else {
					list = Collections.emptyList();
				}
				return list;
			}
		}
	}

	public User selectByEmail(Connection conn, String email) throws SQLException {
		String sql = "select userId, loginId, cast(aes_decrypt(unhex(password),'mutube') as char(50)) as password, email, name, register_date, authority"
				+ " from User where email = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, email);
			try (ResultSet rs = pst.executeQuery()) {
				User user = null;
				if (rs.next()) {
					user = getUserModel(rs);
				}
				return user;
			}
		}
	}
		
	public User findPassword(Connection conn, User user) throws SQLException {
		String sql = "select userId, loginId, cast(aes_decrypt(unhex(password),'mutube') as char(50)) as password, email, name, register_date, authority"
				+ " from User where name = ? and email = ? and loginId = ?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getLoginId());
			try (ResultSet rs = pst.executeQuery()) {
				user = null;
				if (rs.next()) {
					user = getUserModel(rs);
				}
				return user;
			}
		}
	}
	
	public void changePwd(Connection conn, String password , String loginId) throws SQLException {
		String sql = "update user set password = HEX(aes_encrypt(?,'mutube')) where loginId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, password);
			pst.setString(2, loginId);
			pst.executeUpdate();
		}
	}
	
	public void update(Connection conn, User user) throws SQLException {
		String sql = "update user set loginId = ? , email = ? , name = ? where loginId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, user.getLoginId());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getName());
			pst.setString(4, user.getLoginId());
			pst.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int userId) throws SQLException {
		String sql = "delete from user where userId = ?";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, userId);
			int count = pst.executeUpdate();
			return count;
		}
	}

	private User getUserModel(ResultSet rs) throws SQLException {
		User user = new User(rs.getInt("userId"), rs.getString("loginId"), rs.getString("password").trim(),
				rs.getString("email"), rs.getString("name"), rs.getTimestamp("register_date").toLocalDateTime(),
				rs.getBoolean("authority"));

		return user;
	}
}
