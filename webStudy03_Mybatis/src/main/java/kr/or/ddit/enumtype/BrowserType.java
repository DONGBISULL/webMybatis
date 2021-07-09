package kr.or.ddit.enumtype;

public enum BrowserType {
	  //enum 열거형 상수 : 상수를 집합 형태로 가지고 있다
		MSIE("익스플로러 구버전"),
		TRIDENT("익스플로러 최근버전"),
		OPERA("오페라"),
		FIREFOX("파이어폭스"),
		EDG("엣지"),
		CHROME("크롬"),
		SAFARI("사파리"),
		OTHER("기타") ;
	//enum : 자기 타입의 상수를 배열의 형태로 가지고 있는 클래스 ==> 배열 이미 순서를 가지고 있음
	//enum은 private 형태 
	private String browserName;//상수 객체 생성할 떄 값이 이미 지정되어있어야 함 
	private BrowserType(String browserName){ //여기에 public 붙이면 오류 터짐 ==> 이넘은 자신이 가진 상수외에 더 객체 생성 불가
		//파라미터가 있을 경우 에러 ==> 기본 생성자가 없어짐
			this.browserName = browserName;
		}
	public String getBroserName(){
		return this.browserName;
	}
	
			
	public static String parseUserAgent(String userAgent) {
		userAgent = userAgent.toUpperCase();
		
		BrowserType finded = BrowserType.OTHER;
		if(userAgent!=null) {
			for(BrowserType tmp : values()){
				// 이넘 상수의 집합 values 로 받아올 수 있음 ==> 상수의 이름과 똑같은 Name 이라는 성질 가지고 있음
				if(userAgent.indexOf(tmp.name())>=0){
					finded = tmp;
					break;
				}
			}
			
		}
		return finded.getBroserName();
	}
}

