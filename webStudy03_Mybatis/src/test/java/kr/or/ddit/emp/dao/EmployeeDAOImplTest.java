package kr.or.ddit.emp.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImplTest {

	EmployeeDAO empDAO ;
	Map<String ,Object> pMap;
	
	@Before //테스트 케이스 실행전 무조건 실행 즉 5번 실행됨
	public void setUp() throws Exception {
		empDAO = EmployeeDAOImpl.getInstanse();
		pMap = new HashMap<>();
	}

	@Test
	public void testInsertEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectEmplyeeList() {
	List<EmployeeVO> empList = empDAO.selectEmplyeeList(pMap);
	assertNotNull(empList);
	assertEquals(1, empList.size());
	assertEquals(false, empList.get(0).isLeaf());
	System.out.println(empList.get(0));
	}

	@Test
	public void testSelectEmplyee() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEmplyee() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmplyee() {
		fail("Not yet implemented");
	}

}
