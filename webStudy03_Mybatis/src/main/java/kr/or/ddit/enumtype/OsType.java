package kr.or.ddit.enumtype;

public enum OsType { //얘 배열

		MAC("맥"),
		DOS("도스"),
		SUNOS("솔라리스"),
		WINDOWS("윈도우"),
		LINUX("리눅스"),
		NIX("유닉스"),
		NUX("유닉스"),
		AIX("유닉스"),
		OTHER("기타");
		
	private String OsName;
	private OsType(String OsName) {
		this.OsName = OsName;
	}
	
	public String getOsName() {
		return OsName;
	}
	
	
	public static OsType findOsType(String accept) {
		accept = accept.toUpperCase();
		OsType finded = OsType.OTHER;
		
		if(accept!=null) {
			for(OsType tmp : values()) {
				if(accept.indexOf(tmp.name())!=-1) {
					finded = tmp;
					break;
				}
			}
		}
		return finded;
	}
	
	public static String findeOsName(String accept) {
		return findOsType(accept).getOsName();
	}
	
	
}
