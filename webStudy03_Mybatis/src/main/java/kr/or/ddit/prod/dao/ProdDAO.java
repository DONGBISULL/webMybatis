package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리(CRUD) Persistence Layer
 *
 */
public interface ProdDAO {
	/**
	 *  신규 상품 등록
	 * @param prod PK를 제외한 나머지 상품 데이터를 가진 VO
	 * @return rowcnt > 0 성공, PK는 call by reference 로 확인.
	 */
	public int insertProd(ProdVO prod);
	
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	
	/**
	 * 상품 상세 조회(구매자 목록 동시 조회)
	 * @param prodId
	 * @return
	 */
	public ProdVO selectProd(String prodId);
	
	public int updateProd(ProdVO prod);
	
}















