package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * Factory Object[Method] Pattern
 *  : 객체의 생성을 전담하는 factory 객체 운영.
 *
 */ 
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	
	static {
		Properties dbProps = new Properties();
		InputStream is = ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/dbInfo.properties");
		try {
			dbProps.load(is);
			//Class.forName(dbProps.getProperty("driverClassName"));
			url = dbProps.getProperty("url");
			user = dbProps.getProperty("user");
			password = dbProps.getProperty("password");
			
			
//			OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();//Class.forName 쓸 필요 없어짐
//			//db가 항상 오라클일거라고 확신할 수 없음--> 종속되는 코드가 아니라 어느 db나 사용할 수 있는 라이브러리 사용하자
//			ds.setURL(url);
//			ds.setUser(user);
//			ds.setPassword(password);
			
			BasicDataSource ds  = new BasicDataSource(); //오라클외에도 mysql을 쓸 때도 사용가능 
			
			ds.setDriverClassName(dbProps.getProperty("driverClassName"));
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			int initialSize = Integer.parseInt(dbProps.getProperty("initialSize"));
			long maxWait = Long.parseLong(dbProps.getProperty("maxWait"));
			int maxTotal = Integer.parseInt(dbProps.getProperty("maxTotal"));
			int maxIdle = Integer.parseInt(dbProps.getProperty("maxIdle"));
			ds.setInitialSize(initialSize);
			ds.setMaxWaitMillis(maxWait); //
			ds.setMaxTotal(maxTotal); //최대 10개까지 다시 만들기
			ds.setMaxIdle(maxIdle);
			dataSource = ds;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
	}

	public static Connection getConnection() throws SQLException {
//	 	2. 드라이버 클래스 로딩
//	 	3. Connection 생성
			//Connection conn = DriverManager.getConnection(url, user, password);
			Connection conn = dataSource.getConnection();

			return conn;
	}
}








