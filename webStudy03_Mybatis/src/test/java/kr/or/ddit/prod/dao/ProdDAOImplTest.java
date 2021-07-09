package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {
	ProdDAO dao = new ProdDAOImpl();
	Logger logger = LoggerFactory.getLogger(ProdDAOImplTest.class);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
	//ProdVO vo = dao.selectProd("P101000001");
		String prodId = "P101000001";
		ProdVO prod = dao.selectProd(prodId);
		assertNotNull(prod);
		if(logger.isInfoEnabled()) {
			logger.info(" prod : {}" , prod);
		}
		//System.out.println(prod.getBuyer());
		assertNotNull(prod.getBuyer());
		if(logger.isInfoEnabled()) {
			logger.info(" buyer : {}" , prod.getBuyer());
		}
		
 		assertEquals(2, prod.getMemberList().size());
//	//	if(logger.isInfoEnabled()) { //false가 들어가서 
//			logger.debug(" prod.getMemberList().size() : {}" , prod.getMemberList().size());
//		//}
//		
	}

}
