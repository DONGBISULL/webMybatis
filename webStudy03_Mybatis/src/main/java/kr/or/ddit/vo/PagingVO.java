package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이징 처리에 관련된 집합 
 *
 */
@NoArgsConstructor
@Getter
public class PagingVO<T> {
	private int screenSize =10;
	private int blockSize =5;
	private int totalRecord; 
	private int totalPage;
	private int currentPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private DataBasePropertyVO searchData ;
	private SearchVO simpleSearch; //단순 키워드
	private T detailSearch;//상세 키워드
	private List<T> dataList;
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	 
	
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int) (Math.ceil(totalRecord/(double)screenSize));
	}

	 
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		endRow = currentPage * screenSize;
		startRow =endRow - (screenSize-1);
		startPage = blockSize * ((currentPage -1)/blockSize) +1;
		endPage = startPage + (blockSize-1);
	}
	private static final String LINKPTRN = "<a class='pageLink' href='#' data-page='%d'>%s</a>\n";
	public String getPagingHTML() {
//		<a href ="?page=1">1</a>
		StringBuffer html = new StringBuffer();
		
		if(startPage>1) {
			html.append(String.format(LINKPTRN,	startPage-1,"이전"));
		}
		
		endPage =endPage > totalPage ? totalPage : endPage ; 
		
		for(int page= startPage ; page<=endPage;page++) {
			if(page ==currentPage) {
				html.append("[" +page +"]");
			}else {
				html.append(String.format(LINKPTRN,page,page));
			}
		}
		if(endPage<totalPage) {
			html.append(String.format(LINKPTRN, endPage +1 , "다음"));
		
	}
		return html.toString();
	
	}
	
	
	
	
	public void setSimpleSearch(SearchVO simpleSearch) {
		this.simpleSearch = simpleSearch;
	}

	public void setDetailSearch(T detailSearch) {
		this.detailSearch = detailSearch;
	}

	public void setSearchData(DataBasePropertyVO searchData) {
		this.searchData = searchData;
	}
	
}
