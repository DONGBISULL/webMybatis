package kr.or.ddit.prop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

public class DataBasePropertyDaoImpl implements DataBasePropertyDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList(PagingVO pagingVO) {
		 try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
					){
			 	DataBasePropertyDAO	mapper = sqlSession.getMapper(DataBasePropertyDAO.class);
			 		//mapper.selectDataBasePropertyList(param);
				 System.out.println(mapper.selectDataBasePropertyList(pagingVO));
			 	return mapper.selectDataBasePropertyList(pagingVO) ;
			}
	}

	@Override
	public int selectTotalRecord(PagingVO<DataBasePropertyVO> pagingVO) {
		 try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
					){
			 DataBasePropertyDAO	mapper = sqlSession.getMapper(DataBasePropertyDAO.class);
				return mapper.selectTotalRecord(pagingVO) ;
			}
	}
	
	
	
	
	@Override
	public List<DataBasePropertyVO> searchDataBasePropertyList(String input) {
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("select * from DATABASE_PROPERTIES ");
//		sql.append("where property_name like '%' || ? || '%' ") ;
//		sql.append(	"or property_value like '%' || ? || '%' or description like '%' || ? || '%' ");
//		
//		try(
//				Connection conn = ConnectionFactory.getConnection();
//				PreparedStatement  pstmt = conn.prepareStatement(sql.toString());
//
//				) {
//			pstmt.setString(1, input);
//			pstmt.setString(2, input);
//			pstmt.setString(3, input);
//			
//			ResultSet rs = pstmt.executeQuery();
//			List<DataBasePropertyVO> propList = new ArrayList<>();
//			while(rs.next()) {
//				DataBasePropertyVO vo = new DataBasePropertyVO();
//				vo.setProperty_name(rs.getString("property_name"));
//				vo.setProperty_value(rs.getString("property_value"));
//				vo.setDescription(rs.getString("description"));
//				propList.add(vo);
//			}
//			
//			rs.close();
//			pstmt.close();
//			return propList;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		
		
		 return null;
	}

	@Override
	public int selectTotalDataProperty(PagingVO paging) {
		 try(
				 SqlSession sqlSession = sqlSessionFactory.openSession();
				 ){
			 DataBasePropertyDAO mapper = sqlSession.getMapper(  DataBasePropertyDAO.class);
			 return mapper.selectTotalDataProperty(paging);
		 }
	}

	
	
	
	
	
}
