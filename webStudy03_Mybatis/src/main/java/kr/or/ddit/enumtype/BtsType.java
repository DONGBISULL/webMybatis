package kr.or.ddit.enumtype;

public enum BtsType {
	
BUI("/WEB-INF/views/bts/bui.jsp"), JHOP("/WEB-INF/views/bts/jhop.jsp" ),
JINMIN("/WEB-INF/views/bts/jimin.jsp" ),
JIN("/WEB-INF/views/bts/jin.jsp" ),
JUNGKUK("/WEB-INF/views/bts/jungkuk.jsp" ),
RM("/WEB-INF/views/bts/rm.jsp" ),
SUGAR("/WEB-INF/views/bts/suga.jsp" );
	                        
private String btsName ;

private BtsType(String btsName) {
	this.btsName = btsName;
}

private String getBtsName() {
	return this.btsName;
}

public static String BtsName(String name) {
	name = name.toUpperCase();
	BtsType finded = BtsType.SUGAR;
	if(name!=null) {
		for(BtsType tmp : values()) {
			if(name.indexOf(tmp.name())>=0) {
				finded = tmp;
				break;
			}
		}
	}
	return finded.getBtsName();
}


}
