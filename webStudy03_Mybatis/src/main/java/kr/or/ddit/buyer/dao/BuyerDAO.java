package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface BuyerDAO {
	/**
	 * 페이징 처리를 위해 구간 별 데이터를 조회
	 * @param paging
	 * @return
	 */
	public List<BuyerVO> selectBuyerList(PagingVO paging);
	/**
	 * 페이징 처리를 위해 토탈 레코드 조회
	 * @param paging
	 * @return
	 */
	public int selectTotalCount(PagingVO paging );
	/**
	 * 새로운 바이어를 추가
	 * @param buyerVO
	 * @return 추가 성공 시 1 , 추가 실패 시 0
	 */
	public int insertBuyer(BuyerVO buyerVO);
	/**
	 *  기존 바이어를 수정 
	 * @param buyerVO
	 * @return 성공 시 1 , 실패 시 0
	 */
	public int updateBuyer(BuyerVO buyerVO);
	
	/**
	 * 기존 바이어를 삭제 
	 *  업데이트
	 * @param buyerId
	 */
	public int deleteBuyer(BuyerVO buyerVO);
	
	
	public BuyerVO seledctBuyerDetail(String buyerId);
	
}
