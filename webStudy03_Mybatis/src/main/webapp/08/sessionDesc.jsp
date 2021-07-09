<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>세션 (HttpSession)</h4>
<pre>
	: Http가 가진 Stateless의 단점을 보완하기 위한 최소한의 상태정보를 저장하는 개념 
	단 해당 상태정보가 서버에 저장된 경우 session
	클라이언트에 저장된 경우 Cookie 
	
	session lifecycle
	1. 클라이언트로부터 최초의 요청이 발생하면 생성 (ID)
		ID : <%=session.getId() %> , <%=new Date(session.getCreationTime()) %>
	2. 최초 요청에 대한 응답에 ID가 실려서 클라이언트로 전송

	3. 다음 요청이 발생할 때 서버로 ID가 재전송되면 세션이 유지됨 
	<%=new Date(session.getLastAccessedTime()) %>	
	
	2번과 3번 단계에서 세션ID를 주고 받는 방법(tracking mode)
		- COOKIE :JSESSIONID와 같은 형태의 쿠키로 세션 아이디를 주고 받는 방법 
		- URL : jsessionid와 같은 세션 파라미터의 형태로 주고 받는 방법  
			<a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션을 유지하는 방법</a>
			==> 여기에 붙어있는 아이디는 보안에 매우 취약함(request line에 붙어있어서 위험성이 존재)
			==> 세션 하이재킹 
		- SSL(Session Socket Layer --> Transfer Layer Secure)
		 
	 4. 세션의 소멸 이벤트 timeout 이내에 새로운 요청이 발생하지 않으면 소멸
	 	1) 명시적인 로그아웃 
	 	2) 브라우저 종료 
	 	3) 쿠키 삭제 
	 	4) timeout
	 	
	
<!-- 서버에 저장공간이 하나만 있음 
한 사람의 클라이언트를 구분하기 위한 정보를 저장할 저장공간이 필요
	
	각 개인과 사용하는 단말기를 구분하기 위해 ID가 부여 됨 

 응답 데이터 보낼 때 세션의 아이디를 보냄
  
a --->	<!-- A세션 (A가 최초의 요청을 보낸 시점에 생성) 
		클라이언트의 마지막 요청을 확인할 수 없음 ==> 만료 시간
요청을 보낼 때 세션의 아이디를 같이 보냄 		

요청안에 세션 아이디가 있느냐 없느냐를 통해 최초 요청인지 아닌지 구분 가능
세션 아이디 , 마지막 요청 시간, 생성된 시간 가지고 있어야 함 
 --> -->
=========================================
b   B세션
</pre>
</body>
</html>