package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZiptbVO;

/**
 * 회원 관리(CRUD)를 위한 Persistense Layer 
 * 
 *
 */

 
public interface MemberDAO {
	public int insertMember(MemberVO member);
	/**
	 * 페이징 처리를 위해ㅐ total recode 조회 
	 * @param PagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO PagingVO);
	
	/**
	 * 페이징 처리를 위해 구간별 데이터를 조회
	 * @param PagingVO
	 * @return
	 */
	public List<MemberVO> selectMemebrList(PagingVO PagingVO); //alt shift c  ==> 한번에 바꾸기
	
	
	public MemberVO selectMemberDetail(String mem_id);
	
	/**
	 * 식별자(PK)로 레코드 조회 
	 * @param mem_id
	 * @return 존재하지 않을 경우 null 반환 
	 */
	public MemberVO selectMemberById(String mem_id); 
	
	 
	/**
	 * 우편번호 
	 * @return 우편번호 리턴
	 */
	public List<ZiptbVO> selectZipList();
	
	public int updateMember(MemberVO member);
	public int deleteMember(String mem_id); //업데이트로 delete 항목에 
}
