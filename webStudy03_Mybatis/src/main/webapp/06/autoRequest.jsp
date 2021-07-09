<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=http://naver.com">  -->
<%--클라이언트 사이드 요청 ; 값을 여러개 설정하겠다
5초 자체도 클라이언트 사이드의 방식 
--%>
<title>06/autoRequest.jsp</title>
<<%-- script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script> --%>
</head>
<body>
<%-- <%
	response.setIntHeader("Refresh", 1); //서버사이드 방식
%>--%>
<h4><span id="timer"></span>초 뒤에 네이버로 이동</h4>
<h4>현재 서버의 시간 : <%=new Date() %></h4>
<h4> 자동요청을 통해 데이터를 갱신하는 방법</h4>
<pre>
	1. server side : refresh 응답 헤더를 통해 자동 요청 
	2. client side :
		1. HTML : meta 태그의 http- equiv를 통해 refresh라는 html 헤더를 설정 
		2. Javascript : 스케줄링 함수의 활용
			** 스케줄링 함수 
			- setInterval : 주기적으로 반복 작업에 활용할 때 
			- setTimeout : setTimeout : 작업에 지연 시간을 설정하고 한 번 실행하는 구조

</pre>

<h4 id="watch">현재 클라이언트의 시간 : <span></span></h4>
<script>
/* 	let timer =$("#timer");
	let initValue = 5;
	setInterval(function(){ //람다 식	
	$("#watch span").text(new Date());
		timer.html(--initValue)
	}, 1000);
	 */
</script>
</body>
</html>