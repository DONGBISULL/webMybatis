<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %> 
    <!-- 버퍼안에 담았다가 한번에 플레시 되는 형식 ==> 자바 한글자에 2바이트로 character 표현-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/07/implicitObject.jsp</title>
</head>
<body>
<!-- 
	final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;  ServletContext application; server인 톰캣과 소통하기 위해 사용
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this; 싱글톤
    request ,response 
    exception까지  
    
pageContext 에서 getter로 다른 객체를 가져온다
	  pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
    
    -->
<h4>기본 객체(내장객체)</h4>
<pre>
<%-- <%=request.getContextPath()%>
<%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%>
${pageContext.request.contextPath} //server-side--%>
	:jsp container에 의해 서블릿 소스가 파싱될 때 자동으로 생성되는 객체 
	사용되는 순
	
	1. request(*) : client 와 request 에 대한 정보를 가진 객체 
	2. response(*) : client와 전송될 response 에 대한 정보를 가진 객체 
	3. out(JspWriter ,*) : 응답 데이터를 버퍼에 기록할 출력스트림
		: buffer를 제어하거나 상태를 확인할 때도 활용됨
	4. session(HttpSession,*) : 하나의 클라이언트가 하나의 브라우저를 사용할 때 ,
							  해당 클라이언트를 식별할 용도로 사용됨
	<a href="../08/sessionDesc.jsp">sessionDesc.jsp</a>
	 세션의 트래킹 모드 ?--> 쿠키 
	5. application (ServletContext ,**) : 현재 서버와 어플리케이션 자체에 대한 정보를 가진 객체 
	<a href="../08/applicaionDesc.jsp">applicaionDesc.jsp</a>
		싱글톤으로 존재하게 됨 
	6. config (ServletConfig) --> init()
		서블릿 하나 당 하나씩 생성됨 ==> 싱글톤으로 관리됨
		 ==> jsp도 servlet이라서 servletConfig를 가지고 있음 
	
	7. page(Object)==this : jsp 인스턴스 자체 
		==> 커스텀 태그를 만들 때 사용  
	8. exception(Throwable) : 에러나 예외가 발생되었을 때 그 상황을 처리할 목적의 페이지에서 사용
		페이지 지시자의 isErrorPage="true"인 경우에만 활성화됨
	9. pageContext(*****) : 모든 객체 중 가장 먼저 생성되고 나머지 기본객체에 대한 참조를 가짐 
	
request
A------> P.C
 &lt;===		
		  |
B		B P.C
	리퀘스트가 더 넓은 사용 범위 범위

스코프 
어플리케이션>세션>리퀘스트> 페이지 컨텍스트 
어플리케이션 스코프>세션 스코프>리퀘스트 스코프> 페이지 컨텍스트 스코프
==================
버퍼 
버퍼 ==> flush했을 때 한번에 나감 	
버퍼가 없을 경우 에러가 났을 때 상태 코드를 바꿀 수 없다(이미 200이라고 상태 코드가 정해져있기 때문이다)
에러가 발생하는 순간 상태 코드를 보내줄 수 있음
-
-버퍼를 비우기전까지 버퍼의 내용 바꿀 수 있음
-dispatch 가 가능하게 됨

</pre>
</body>
</html>