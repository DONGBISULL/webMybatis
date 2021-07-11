package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements BuyerService {
	BuyerDAO dao = new  BuyerDAOImpl();
	@Override
	public void retrieveBuyerList(PagingVO<BuyerVO> paging) {
		 
		int totalRecord = dao.selectTotalCount(paging);
		paging.setTotalRecord(totalRecord);
		List<BuyerVO> list = dao.selectBuyerList(paging);
		paging.setDataList(list);
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		int cnt = dao.insertBuyer(buyer);
		ServiceResult result = null;
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		int cnt = dao.updateBuyer(buyer);
		ServiceResult result = null; 
	
		
		
		return null;
	}

	@Override
	public BuyerVO retrieveBuyerDetail(String buyerId) {
		BuyerVO buyerVO = dao.seledctBuyerDetail(buyerId);
		
		
		return buyerVO;
	}

	
	
	
}
