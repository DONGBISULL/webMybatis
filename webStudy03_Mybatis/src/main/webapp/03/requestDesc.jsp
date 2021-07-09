<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestDesc</title>
<style>
pre{
font-size: 1.3em;
}

</style>
</head>
<body>
	<h4>HttpServletRequest reqeust</h4>
	
<pre>
	: 클라이언트와 그로부터 발생한 요청에 대한 모든 정보를 가진 객체 
	
	http request spec 
	1. Request Line : Protocol URL Method - 명령 식별
		Method :요청 발생시킨 목적(의도) , 요청 패키징 방법 ㅆ
		R - GET 
		C - POST
		U - PUT : 수정하지 않아도 되는 정보까지 전부 수정할 수 있도록 함 
			PATCH : 이메일을 수정할 경우 이메일만 수정한다고 보내줌 
		D - DELETE
		
    ================================================
    
		options :preflight 요청으로 특정 메소드의 지원 여부를 확인할 목적의 요청에서 사용 
		head : response 를 받아올 때 body를 제외하고 싶은 요청에 사용 
		trace : servlet debugging 
	2. Request Header : 클라이언트에 대한 메타 정보 영역.(meta data )
	3. Request Body(Message Body , Contents Body) : 서버로 전송할 메시지 영역(only POST)
		==> 포스트에서만 만들어짐 
	Line : <%=request.getProtocol() %> <%=request.getRequestURI() %> <%=request.getMethod() %>
	Body : <%=request.getInputStream().available() %>
		   <%=request.getCharacterEncoding() %>
		   <%=request.getContentLength() %>
	Mime : <%=request.getContentType() %>
</pre>
<table>
	<thead>
		<tr>
			<th>헤더 이름</th>
			<th>헤더 값 </th>
		</tr>
	</thead>
	<tbody>
		<%
		Enumeration<String> names =	request.getHeaderNames(); //컬랙션 뷰?
		while(names.hasMoreElements()){
		%>
		<tr>
		<%
			String headerName = names.nextElement();
			String headerValue = request.getHeader(headerName);
		%>
			<th><%=headerName %></th>
			<td><%=headerValue %></td>
		<%
		}
		%>
	</tbody>
</table>

</body>
</html>