package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 거래처 정보 Domain Layer
 *
 */
@Getter
@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable{
	

	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private String lprodNm;
	
	private List<ProdVO> prodList;
}
