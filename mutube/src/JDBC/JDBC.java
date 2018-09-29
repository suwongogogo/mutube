package JDBC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class JDBC implements ServletContextListener {

	// 시작할 때 우리의 디비 커넥션 풀을 셋팅하도록 해보자
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 서블릿 컨텍스트를 받고
		ServletContext sc = sce.getServletContext();
		// 서블릿 컨텍스트를 통해서 init파라미터(디비 정보가 있는 properties)를 받음
		// 파일 주소로 파일을 읽어야 함. 시스템 상 주소!!
		String poolConfigFile = sc.getRealPath(sc.getInitParameter("poolConfigFile"));

		// 파일 주소로 프로퍼티스 객체에 파일에 있는 데이터를 넣을 것임!
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(poolConfigFile));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Not found poolConfigFile");
		} catch (IOException e) {
			throw new RuntimeException("Fail to read poolConfigFile");
		}
		// jdbc 드라이버 로드
		loadJDBCDriver(prop);

		// 커넥션 풀 초기화
		InitConnectionPool(prop);
	}

	private void InitConnectionPool(Properties prop) {
		// 디비 접속 정보를 스트링으로 받아 봄.
		String connectURI = prop.getProperty("jdbcURI");
		String User = prop.getProperty("User");
		String Pwd = prop.getProperty("Pwd");
		try {
			// 디비 접속정보를 인자로 넣고 커넥션을 만들어 주는 팩토리 객체를 생성.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(connectURI, User, Pwd);
			
			// 풀에서 쓸수있는 커넥션을 만들어주는 팩토리에 커넥션 팩토리를 넣고 생성!
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			// 커넥션이 유효한지 체크하기 위한 쿼리를 지정
			// getProperty의 첫번째 인자는 파일에 정의 되어있는 값이고, 두번째 인자는 없을시! 기본값으로 해줄 값!
			poolableConnFactory.setValidationQuery(prop.getProperty("validationQuery", "select 1"));
			// 커넥션 풀의 설정 정보를 다루는 객체 생성하고 설정정보 셋팅 
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(Integer.parseInt(prop.getProperty("minIdle", "5")));
			poolConfig.setMaxTotal(Integer.parseInt(prop.getProperty("maxIdle", "50")));
			
			// 커넥션 풀을 생성
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool);
		}catch(SQLException e) {
			throw new RuntimeException("can not get Driver PoolingDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Not found class");
		}
	}

	// jdbc 드라이버를 로드하는 메소드를 정의
	private void loadJDBCDriver(Properties prop) {
		// 프로퍼티에서 설정한 드라이버주소로 클래스 로드
		String driverClass = prop.getProperty("jdbcDriver");
		System.out.println(driverClass);
		try {
			Class.forName(driverClass);
		} catch (Exception e) {
			throw new RuntimeException("fail to load JDBC Driver");
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
