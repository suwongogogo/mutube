package File.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connection.ConnectionProvider;
import Post.Model.File;
import Post.Service.WriteCommentService;

public class FileDAO {
	private static FileDAO instance = new FileDAO();
	private FileDAO() {}
	public static FileDAO getInstance() {
		return instance;
	}
	
	public int upload(Connection conn, File file ) {
		String query= "insert into file value(?, ?)";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, file.getFileName());
			pst.setString(2, file.getFileRealName());
			return pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
