package kr.or.ddit.prop.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.prop.dao.DataBasePropertyDAO;
import kr.or.ddit.prop.dao.DataBasePropertyDaoImpl;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService {
	private DataBasePropertyDAO dao = new DataBasePropertyDaoImpl();
	
	@Override
	public List<DataBasePropertyVO> retrieveDataBaseProperties(PagingVO pagingVO) {
		
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		//==============================================
		List<DataBasePropertyVO> propList = dao.selectDataBasePropertyList(pagingVO);
		pagingVO.setDataList(propList);
		 
		//logic --> information
		Calendar cal = Calendar.getInstance();
		String pattern = "%s , %tc";
		//param.getDetaList();
		System.out.println("propList " + propList );
		for(DataBasePropertyVO prop : propList) {
			String infoValue=	String.format(pattern, prop.getPropertyValue() ,cal);
			prop.setPropertyValue(infoValue);
		}
		return propList;
	}

	@Override
	public List<DataBasePropertyVO> searchDataBasePropertyList(String input) {
		
		List<DataBasePropertyVO> propList = dao.searchDataBasePropertyList(input);
		Calendar cal = Calendar.getInstance();
		String pattern = "%s , %tc";
		for(DataBasePropertyVO prop : propList) {
			String infoValue=	String.format(pattern, prop.getPropertyValue() ,cal);
			prop.setPropertyValue(infoValue);
		}
		return propList;
	}

	@Override
	public int selectTotalDataProperty(PagingVO paging) {
		return dao.selectTotalDataProperty(paging);
	}

 
}
