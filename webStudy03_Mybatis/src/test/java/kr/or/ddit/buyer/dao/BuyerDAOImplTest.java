package kr.or.ddit.buyer.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumtype.StatusType;
import kr.or.ddit.prod.dao.ProdDAOImplTest;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImplTest {
	
	BuyerDAO dao = new BuyerDAOImpl();
	Logger logger = LoggerFactory.getLogger(ProdDAOImplTest.class);
	@Before
	public void setUp() throws Exception {
		
	}

//	@Test
//	public void testSelectBuyerList() {
//		PagingVO paging = new PagingVO();
//		BuyerVO buyerVO = new BuyerVO();
//		paging.setCurrentPage(1);
//		int count = dao.selectTotalCount(paging);
//		paging.setTotalRecord(count);
//		List<BuyerVO> list =	dao.selectBuyerList(paging);
//		
//		System.out.println(list);
//	}

//	@Test
//	public void testSelectTotalCount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInsertBuyer() {
//		BuyerVO vo = 	dao.seledctBuyerDetail("P10103");
//		int cnt = 	dao.insertBuyer(vo);
//		System.out.println(cnt);
//	}

/*	@Test
	public void testUpdateBuyer() {
		BuyerVO vo = dao.seledctBuyerDetail("P10104");
		vo.setBuyerName("11111");
		int cnt = dao.updateBuyer(vo);
		assertEquals(1, cnt);
	}
*/
	@Test
	public void testDeleteBuyer() {
		BuyerVO buyer = dao.seledctBuyerDetail("P10104");
		//buyer.setBuyerStatus(StatusType.DELETE.getStatusName());
		int cnt  = dao.deleteBuyer("P10104");
		assertEquals(1, cnt);
	}

	@Test
	public void testSeledctBuyerDetail() {
		BuyerVO buyerVO = 	dao.seledctBuyerDetail("P10102");
		//assertNotNull(buyerVO);
		assertNotNull(buyerVO.getProdList());
		if(logger.isInfoEnabled()) {
			logger.info("Buyer : {}", buyerVO.getProdList());
		}
	
	}

}
