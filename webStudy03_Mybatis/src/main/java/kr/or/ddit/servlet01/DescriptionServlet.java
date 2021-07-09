package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 알트 쉬프트 j 
 * Servlet Spec : Was(Servlet Container) 에 의해 관리(운영)될 수 있는 웹 객체에 대한 명세 
	  Container? 컨테이너에 의해서 관리되는 객체의 생명 주기 관리자 
	  
	  1.HttpServlet 의 상속 -> 필요한 콜백(callback)메서드의 재정의(overriding)
	  	callback 구조 ? 특정 이벤트가 발생하면 시스템 내부적으로 자동 호출되는 구조
	  2.compile -> /Web-INF/classes (classpath) 아래에 ㅐ포 
	  3. 컨테이너에 등록  
	 		2.x. web.xml -> servlet(servlet-name . servlet-class : qualified name)
			3.x. @webServlet(name ,urlPattern) 등록과 매핑 동시에 
	  4. 클라이언트의 요청과 매핑 설정 
			2.x web.xml -> servlet-mapping(servlet-name ,url-pattern)
	  5. restart

**Callball 메소드 종류 
*1. lifecycle callback
*		-init : servelt instance 생성 직후 한 번 호출 
*		-destroy : servlet instance 소멸 직전 한 번 호출
*2. request callback : 매 요청마다 반복 호출 
*		-service : 재정의 시 super.service 호출 코드를 제거하면 doXXX계열의 콜백 메서드가 무의미해짐 
*		-doXXX : 재정의시 반드시 super.doXXX 호출코드를 제거함 
*
**Servlet Container의 서블릿 관리 특성 
  1. 싱글턴 형태로 관리
  2. 요청이 발생하면 
  	1)정적 요청 여부 확인 : default servlet 을 통해 처리 
  	2.) 해당 요청의 url 패턴을 판단하고 일치하는 등록된  서블릿 검색 
  		- 검색 실패시 404 
  		- 검색 성공 : 싱글턴 객체를 찾고 존재하면 콜백 호출
  				 존재하지 않으면 싱글턴 객체를 생서하고 

 */
@WebServlet("/desc.do") // 속성명 생략가능 ""
public class DescriptionServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.printf("%s 서블릿 객체  생성 직후 초기화됨" , getClass().getName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("service의 첫 라인");
		super.service(req, resp); //현재 요청의 메서드를 확인 
		System.out.println("service의 마지막 라인");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.println("Get방식으로 요청이 처리됨. 현재쓰레드명 :"+Thread.currentThread().getName() );
		super.doGet(req, resp);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.printf("%s 서블릿 객체 소멸 직전 초기화됨" , getClass().getName());
		
	}
	
}
