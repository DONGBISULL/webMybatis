package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.commons.UserNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImplTest {
	private MemberService service = MemberServiceImpl.getInstance();
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateMemberVO() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMemberList() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test 
	public void testRemoveMemberPassError() {

		MemberVO member = MemberVO.builder()
				.memId("a001")
				.memPass("asjdldf")
				.build();
		//		
	ServiceResult result = service.removeMember(member);
	assertEquals(ServiceResult.INVALIDPASSWORD, result);
	}
	
	@Test 
	public void testRemoveMember() {

		MemberVO member = MemberVO.builder()
				.memId("a001")
				.memPass("asdfsd")
				.build();
		//		
	ServiceResult result = service.removeMember(member);
	assertEquals(ServiceResult.OK, result);
	}


	@Test(expected=UserNotFoundException.class)
	public void testRemoveMemberException() {

		MemberVO member = MemberVO.builder()
				.memId("asdjlkdf")
				.build();
		//		
		service.removeMember(member);
	
	}
	
	
	@Test
	public void testRetrieveMember() {
		fail("Not yet implemented");
	}

}
