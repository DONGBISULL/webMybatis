package kr.or.ddit.prop.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceTest {
	 
	@Test
	public void test() {
		DataBasePropertyService service = new DataBasePropertyServiceImpl();
		//List<DataBasePropertyVO> proList = service.retrieveDataBaseProperties();
		/*System.out.println(proList);*/
		List<DataBasePropertyVO> proList = service.searchDataBasePropertyList("e");
		System.out.println(proList);
		
	}

}
