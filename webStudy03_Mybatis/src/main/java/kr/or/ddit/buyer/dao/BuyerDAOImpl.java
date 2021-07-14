package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements BuyerDAO{
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	@Override
	public List<BuyerVO> selectBuyerList(PagingVO paging) {
			try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
					){
				BuyerDAO	mapper = sqlSession.getMapper(BuyerDAO.class);
				return mapper.selectBuyerList(paging) ;
			}
		
	}

	@Override
	public int selectTotalCount(PagingVO paging) {
		try(
				
				SqlSession sqlSession = sqlSessionFactory.openSession();
				
				){
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.selectTotalCount(paging);
		}
	}

	@Override
	public int insertBuyer(BuyerVO buyerVO) {
		 try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				){
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			int cnt =  mapper.insertBuyer(buyerVO) ;
			//sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyerVO) {
		 try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				){
			 BuyerDAO 	mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.updateBuyer(buyerVO);
		}
	}

	@Override
	public int deleteBuyer(BuyerVO buyerVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				){
			BuyerDAO	mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.deleteBuyer(buyerVO) ;
		}
	}

	@Override
	public BuyerVO seledctBuyerDetail(String buyerId) {
		 try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
				){
			 BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.seledctBuyerDetail(buyerId) ;
		}
	}
	
	
 

}
