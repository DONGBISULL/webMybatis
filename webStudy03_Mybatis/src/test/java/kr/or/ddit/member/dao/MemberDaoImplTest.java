package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ZiptbVO;

public class MemberDaoImplTest {
	MemberDaoImpl dao ;
	@Before
	public void setUp() throws Exception {
		dao = MemberDaoImpl.getInstance();
	}
	 

//	@Test
//	public void testSelectMemberById() {
//	MemberVO member =  dao.selectMemberById("a001");
//	System.out.println(member);
//	assertNotNull(member);
//	}
	@Test
	public void testInsertMember() {
		MemberVO vo = 	dao.selectMemberDetail("c001");
		vo.setMemId("dobi3");
		System.out.println(vo);
		int result = 	dao.insertMember(vo);
		assertEquals(1, result);
	}
	@Test
	public void selectZipList(){
		List<ZiptbVO> lit = dao.selectZipList();
		 System.out.println(lit);
	}
	
	/*
*/
//	@Test
//	public void testSelectMemebrList() {
//		List<MemberVO> list =  dao.selectMemebrList(PagingVO);
//		//System.out.println(list);
//		assertNotNull(list);
//	}

@Test
	public void testSelectMemberDetail() {
		 MemberVO vo = dao.selectMemberDetail("a001");
		 assertNotNull(vo);
		 //System.out.println("details : " + vo);
	}
		
	@Test
	public void testDeleteMember() {
		 int rowcnt = dao.deleteMember("a001");
		 assertEquals(1, rowcnt);
	}


//	@Test
//	public void testUpdateMember() {
//		/*MemberVO vo = MemberVO.builder().memId("1234").memPass("asd").memName("룰루랄라").memBir("2021-01-01").memZip("wqer")
//					 .memAdd1("sdafsf").memAdd2("qweqwe12").memMail("Sadf").build();
//	*/
//		MemberVO member =	dao.selectMemberDetail("a001");
//		member.setMemName("김은대");
//		int rowcnt = dao.updateMember(member);
//		assertEquals(1, rowcnt);
//	
//	}
	/*

*/
}
