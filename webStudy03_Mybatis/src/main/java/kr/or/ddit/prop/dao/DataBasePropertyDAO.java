package kr.or.ddit.prop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

/**
 * Dao(Data Access Object) : 데이터 저장 계층(Persistence Layer)에 접근하는 역할 수행 
 *
 */
 
public interface DataBasePropertyDAO {
	//@Param("param") //r검색을 하기 위해 param을 사용 
	public List<DataBasePropertyVO >selectDataBasePropertyList(PagingVO pagingVO );
	public int selectTotalRecord(PagingVO<DataBasePropertyVO>pagingVO);
	//-----------------------------------------------
	public int selectTotalDataProperty(PagingVO paging);
	public List<DataBasePropertyVO>searchDataBasePropertyList(String input);
}
