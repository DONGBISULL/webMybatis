<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
도착 페이지
<pre>

request scope : <%=request.getAttribute("contents") %> ==> sendRedierect 과정에서 원본 요청은 사라짐
session scope : <%=session.getAttribute("contents") %>

</pre>
</body>
</html>