package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 상품 관리 Domain Layer 
 *
 */
@Getter
@Data
@EqualsAndHashCode(of="prodId")
public class ProdVO {
	private String prodId;
	private String prodName;
	private String prodLgu;
	private String lprodNm;
	private String prodBuyer;
	
	//**** prod와 member 관계 
	private BuyerVO buyer; //has a 관계 --> association 관계
	
	//private String buyerName;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private List<MemberVO> memberList; //map에서 collection 필요 
	
}
