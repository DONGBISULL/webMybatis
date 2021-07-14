package kr.or.ddit.enumtype;


import lombok.Getter;

@Getter
public enum StatusType {
	
	CONFIRM("승인") , REJECT("거절") ,DELETE("삭제"), HOLDING("대기")	,DONE("계약 만료") ;
	
	private String statusName;
	private String value ; 
	
	
	private StatusType(String statusName) {
		this.statusName = statusName;
	}
	
	public String getStatusName() {
		return this.statusName;
	}

    public String getValue() {
        return value;
    }
	
	
	public static String findStatusName (String satusList) {
		StatusType finded = StatusType.HOLDING;
		if(satusList !=null) {
			satusList = satusList.toUpperCase();
			for( StatusType tmp : values()) {
				if(satusList.indexOf(tmp.name())>0) {
					finded  = tmp;
					break;
				}
			}
		}
		return finded.getStatusName();
	}
}
