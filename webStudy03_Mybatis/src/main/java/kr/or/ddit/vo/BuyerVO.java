package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
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
	
	@NotBlank(groups= {UpdateGroup.class , DeleteGroup.class})
	private String buyerId;
	@NotBlank 
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	@Email
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	@NotBlank(groups= {UpdateGroup.class , DeleteGroup.class})
	private String buyerStatus;
	
	private String lprodNm;
	
	private List<ProdVO> prodList;
}
