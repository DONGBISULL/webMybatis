<%@page import="kr.or.ddit.servlet01.imageListServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourceidentify.jsp</title>
</head>
<body>
<h4>자원의 식별</h4>
<pre>
		1. file system resource : d:/contents/cart01.jpg
		2. web resource : web resource : (URL/URI), http://localhost:port/contextPath/images/cart01.jpg 
			url 가진 파일 web resource 라고 함
		3. classpath resource : /kr/or/ddit/servlet01/cat01.jpg
		
		파일이 어디에있느냐에 따라 resource의 접근 방식이 달라짐 
	<%
		File fileSystemRes = new File("d:/contents/jj.jpg"); 
		String realPath = application.getRealPath(request.getContextPath() + "images/jj.jpg"); //서버의 위치를 통해서 얻어온다
		//서버사이드의 정보를 받아올 때 contextPath 필요 없음 
		File webRes = new File(realPath);//파일 시스템 패스가 필요함 ==> 변경되는 경로는 사용 되지 않는다
		//A a = new A(); ==>  앞에서 A a 부터 읽어옴 
		String realPath2 = 	imageListServlet.class.getResource("/kr/or/ddit/servlet01/jj.jpg").getFile();
		File classPathRes = new File(realPath2);
		%>	
		파일 크기 <br>
		<%=fileSystemRes.length() %> ==> 시스템 루트부터 표기 
		<%=realPath%> : <%=webRes.length() %>
		<%=realPath2 %> : <%=classPathRes.length() %>
		<br>
		core/tmp0/wtpwebapps/webStudy01/WEB-INF/classes/==> 하드 코딩 하지 않아도 경로 얻어올 수 있음 

	** web resource 식별 방법 
		URI(Uniform Resource Identifier)
		--> 자원을 식별하는 방식을 통칭(URI는 URL URN URC를 모두 포함==> URI가 더 포괄적 )
		URL(Uniform Resource Locator): 자원의 위치를 기준으로 식별 
		URN(Uniform Resource Name) : 자원의 이름을 기준으로 식별
		URC(Uniform Resource Content) : 자원의 등록된 컨텐츠로 식별

		<%=request.getRequestURI() %> ==> localhost 생략 ==> 논리적 
		<%=request.getRequestURL() %> ==> 위치자 최상위 경로부터 모두 나타남 ==> 물리적 
		
		주소를 써야 하는 경우 클라이언트 측 자료인지 서버가 사용하는 자료인지 구분이 먼저 
		
		자원에 접근하는 경로 표기법 
		1. 상대 경로 : 경로 생략되는 구조 .wild card (. , ..)
				    현재 위치(브라우저의 주소)를 기준으로 실제 자원의 경로를 판단함 
		2. 절대 경로: "/"로 시작 
			1) client side : context root부터 시작되는 경로 표기 
			2) server side : context root 이후의 경로를 표기함	
				 
				 
	<h4>리퀘스트 </h4>		
			Http	 
	서버 				클라이언트 	 
	request Line ---> 식별  URL Method(get (R), post(C) , put(U) , delete(D)) ==> 수정인지 삭제인지 뭘 할지를 get Post를 통해 알 수 있음 
			options(preply) --> 사전 요청을 보내서 서버가 현재 메서드를 지원하는지 확인함 --
			지원 안하는 경우 본 요청 취소
	request Header ---> 클라이언트가 원하는 응답 데이터를 확인 
			요청에 대한 메타 데이터(부가 데이터를 확인)해서 보냄
	trace(추종) : 관리 목적으로만 사용 디버깅하려는 의도(인트라넷 안에서만 사용 )
	request Body : 보낼 정보가 없으면 사용할 필요 없음
	body가 필요한 경우에만 생성()
	---> 클라이언트가 get 메서드를 사용한다고 할 때 가져오는게 목적이기 때문에 body 필요 없음 
	
	
	
</pre>

<img src="../images/jjun2.jpg"/>
<img src="http://localhost/webStudy01/images/jjun2.jpg"/>
<img src="<%=request.getContextPath() %>/images/jjun2.jpg"/>
</body>
</html>