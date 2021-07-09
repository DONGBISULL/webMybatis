<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/cacheControl.jsp</title>
</head>
<body>

<%
	response.setHeader("Cache-Control", "no-cache");	
	response.addHeader("Cache-Control", "no-store"); //하나가 덮어씌워지니까 cache 지원하는 곳에서 문제 생김 	
	response.setHeader("Pragma", "no-cache");	
	response.addHeader("Pragma", "no-store"); //하나가 덮어씌워지니까 cache 지원하는 곳에서 문제 생김 	
	response.setDateHeader("Expires", 0);
%>
  	
<img src="<%=request.getContextPath() %>/resources/images/jj.jpg">  
</body>
</html>