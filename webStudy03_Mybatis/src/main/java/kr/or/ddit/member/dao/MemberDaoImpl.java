package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZiptbVO;

public class MemberDaoImpl implements MemberDAO {
	//	singleton 
	
	private static MemberDaoImpl self ; 
	
	private MemberDaoImpl() {
		super();
	}
	
	
	public static MemberDaoImpl getInstance() {
		if(self==null) {
			self = new MemberDaoImpl();
		}
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory 
	= CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	@Override
	public MemberVO selectMemberById(String mem_id)  {

	//아이바티스 #mem_id# ==> 인라인 파라미터
		 try(
				 SqlSession sqlSession =	sqlSessionFactory.openSession();	 
				 ){
			 return (MemberVO )sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberById", mem_id); 
			 //mybatis에서 이미 에러를 runtimeException으로 바꿔주ㅡㅁ 
		 }
	}

	@Override
	public int insertMember(MemberVO member) {
	       try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
					){
	    	   MemberDAO	mapper = sqlSession.getMapper(MemberDAO.class);
	    		int cnt = mapper.insertMember(member) ;
	    	   sqlSession.commit();
				return cnt;
			}                                                               
		 
	}

	@Override
	public int selectTotalRecord(PagingVO PagingVO) {

		try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
					){
			
				MemberDAO	mapper = sqlSession.getMapper(MemberDAO.class);
				return mapper.selectTotalRecord(PagingVO) ;
			}

	}
	
	@Override
	public List<MemberVO> selectMemebrList(PagingVO PagingVO) {
		   try(
				   SqlSession sqlSession= sqlSessionFactory.openSession();
				   ){
			   return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemebrList" ,PagingVO);
		   }
		                                                  
	}

	@Override
	public MemberVO selectMemberDetail(String mem_id) { //멤버가 존재하지 않으면 null 값으로 리턴 
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			//proxy 객체 생성
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			System.out.println(mapper.getClass());
			return mapper.selectMemberDetail(mem_id);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapper.updateMember(member);
			sqlSession.commit();
			
			return rowcnt;
		}
		
		
	 
	}

	
	@Override
	public int deleteMember(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
			){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt =  mapper.deleteMember(mem_id) ;
			sqlSession.commit();
			return rowcnt;
		}
		
	}


	@Override
	public List<ZiptbVO> selectZipList() {
		 try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			 MemberDAO	mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectZipList() ;
		}
	}

}
