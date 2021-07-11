package kr.or.ddit.buyer.service;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface BuyerService {
	/**
	 * 
	 * @param buyer
	 */
	public void retrieveBuyerList(PagingVO<BuyerVO> paging);
	/**
	 * 거래처 추가
	 * @param buyer
	 * @return 성공시 OK 실패시 FAIL
 	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	/**
	 * 거래처 수정
	 * @param buyer 
	 * @return 존재하지 않으면 {@link DataNotFoundException} 발생 OK , FAIL
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
	 
	public BuyerVO retrieveBuyerDetail(String buyerId);
}
