<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/includeDesc.jsp</title>
<jsp:include page="../includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>include의 종류 </h4>
<pre>
	: include  되는 시점과 대상에 따른 분류
	1. 정적 include : jsp 소스 파싱 단계 , 소스 코드가 내포됨 
		코드의 중복을 제거하는데 활용됨 (비추!!!)
		<%-- <%@ include file="/02/standard.jsp" %> --%>
		<%--<%
			String testResult = test() ;
		%> --%>
	web.xml도 활용 가능 
	
	2. 동적 include : runtime , 실행 결과가 내포됨
		 <%
			String dest ="/02/standard.jsp";//서버 안에서만 사용하는 방식 
			//request.getRequestDispatcher(dest).include(request, response);
			
			
			//pageContext.include(dest); //서블릿에선 사용 불가==> 서블릿엔 pageCOn
		 	//pageContext는 버퍼가 한 번 flush가 됨 
		 	//페이지를 모듈화 하고 싶을 경우 pageContext 사용 
		 	//중간에 에러가 생겼을 경우 처리가 어려움 
		 %> 
	 액션태그 ==> 서버 side
	 <jsp:include page="/02/standard.jsp"></jsp:include>
	 	==> pageContext.include(dest);와 동일하게 표현됨
	 
	남은 잔여 코드
	<%-- <%=testResult %> --%>
</pre>
</body>
</html>