<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/flowControl.jsp</title>
</head>
<body>
<h4>웹 어플리케이션의 흐름 제어</h4>
1. Request Dispatch : 도착지로 이동하는 과정에서 원본 요청에 대한 정보를 가지고 분기 
	공통점 : buffer를 사용
	1) forward : 이동하기 전에 버퍼의 내용을 clear 시킴 
	2) included : 도착지에서 만들어진 결과 데이터를 가지고 복귀
	
<%-- <%		String dest ="/07/desination.jsp";//server side==
		RequestDispatcher rd = 	request.getRequestDispatcher(dest);
//		rd.forward(request, response);//살아있는 request를 이용하기 위해 response 같은 버퍼를 사용하기 위해 버퍼의 제어권을 가진 response를 보내야 함 
		rd.include(request, response);
		//B에서 만든 응답 데이터와 기존에 있던 A가 동시에 나타남
%> --%>
2. Redirect : Http의 stateless 특성에 따라 이동하는 과정에서 원본 요청에 대한 정보 삭제됨
	이동하는 과정에서 원본 요청에 대한 응답이 먼저 전송(*****)
	- body가 없고 상태 코드(302) + header(Location)로 구성된 응답이 전송
	--> Location 방향으로 클라이언트의 새로운 요청이 발생함
<%
	String dest = request.getContextPath() + "/07/desination.jsp"; //클라이언트 방식이라서 contextPath 필요
	response.sendRedirect(dest);

%>


stateless 
request 1번만 존재 
=======================
forward
req ----------->A
				|
				↓
resq<---------B (request를 가지고 분기한다)

include
resq
req ----------->A
				|↑ == >dispatcher로 가져감
				↓|
				B (request를 가지고 분기한다)

		req B
req ----------->A
	<==---------	
request가 2개 
	----------->	
resq<-----------B 
		respB(Location을 숨겨서 전송 ) 




</body>
</html>