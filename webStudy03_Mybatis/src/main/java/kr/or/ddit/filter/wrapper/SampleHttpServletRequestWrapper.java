package kr.or.ddit.filter.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SampleHttpServletRequestWrapper extends HttpServletRequestWrapper{
	private String customData = "커스텀 데이터";
	//바꿔주는 것 adaptor 당하는 것 adaptee
	//어댑터 패턴은 클래스의 인터페이스를 사용자가 기대하는 인터페이스 형태로 변환시킴
	//감쌀 수 있는 원본 요청 받을 수 있음
	public SampleHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	public String getParameter(String name) {
		if("what".equals(name)) { //이름 파라미터 동일할 때 
			return "P101000001"; //리턴
		}else {
			return super.getParameter(name);
		}
	}
	
	public String getCutomData() {
		return customData;
		
	}

}
