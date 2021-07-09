package kr.or.ddit.db.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlsessionFactory;
	//xml 한 번만 읽게 됨 -> singleton 
	static {
		String configFile = "kr/or/ddit/db/mybatis/Configuration.xml";
		try (
				Reader reader =	Resources.getResourceAsReader(configFile);
				){
			sqlsessionFactory =	new SqlSessionFactoryBuilder().build(reader);
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static SqlSessionFactory getSessionFactory() {
		return sqlsessionFactory;
	}
	
}
