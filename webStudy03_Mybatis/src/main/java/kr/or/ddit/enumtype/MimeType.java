package kr.or.ddit.enumtype;

public enum MimeType {
	JSON("application/json;charset=UTF-8") , SCRIPT("text/javascript") , 
	PLAIN("text/plain;charset=UTF-8"),HTML("text/html;charset=UTF-8"), 
	XML("application/xml;charset=UTF-8");//일치하는 애 없으면 html로 받을 수 있도록 맞춰줌 
	private String mimeText;
	
	private MimeType(String mimeText) {
		this.mimeText = mimeText;
	}


	public String getMimeText() {
		return mimeText;
	}
	
	public static MimeType findMimeType(String accept) {
		accept = accept.toUpperCase();
		MimeType finded = MimeType.HTML;
		
		 if(accept!=null) {
			 for(MimeType tmp : values()) {
				 if(accept.indexOf(tmp.name())!=-1) {
					 finded=tmp;
					 break;
				 }
			 }//for
			 
		 }
 		return finded;
	}
	
	public static String  findMimeText(String accept) {
			return findMimeType(accept).getMimeText();
	}
	
}
