package kr.or.ddit.prop.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		DataBasePropertyDAO dao = new DataBasePropertyDaoImpl();
		/*List<DataBasePropertyVO> propList =dao.selectDataBasePropertyList();
		assertNotNull(propList);
		System.out.println(propList);
//		*/
		List<DataBasePropertyVO> propList = dao.searchDataBasePropertyList("e");
		assertNotNull(propList);
		System.out.println(propList);
	}

}
