package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.commons.UserNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZiptbVO;

/**
 * 회원관리(CRUD) Business Logic Layer
 *
 */
public interface MemberService {
	/**
	 * 
	 * @param member
	 * @return PKDUPLICATED (검증 통과 id만 문제==> 아이디만 바꿔서 아까 작성했던 ), OK  , FAIL
	 */
	public ServiceResult createMemberVO(MemberVO member);
	
	public int retrieveMemberCount(PagingVO paingVO);
	
	public List<MemberVO>retrieveMemberList(PagingVO pagingVO);
	/**
	 * 
	 * @param mem_id
	 * @return 존재하지 않는 경우 {@link UserNotFoundException}
	 */
	public MemberVO retrieveMember(String mem_id);
	
	/**
	 * 
	 * @param vo
	 * @return 존재하지 않는 경우 ,{@link UserNotFoundException}
	 * 	INVALIDPASSWORD ,OK , FAIL
	 */
	public ServiceResult modifyMember(MemberVO vo);
	/**
	 * 
	 * @param vo
	 * @return 존재하지 않는 경우 ,{@link UserNotFoundException}
	 * 	INVALIDPASSWORD ,OK , FAIL
	 */
	public ServiceResult removeMember(MemberVO vo);
	
	
	public List<ZiptbVO> selectZipList();
}
