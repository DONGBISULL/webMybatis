<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 지시자 환경설정을 하고 싶을 때 사용-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	String data = new Date().toString();
%>
<h4><%=data %></h4>
<!-- 
 스크립트리 기호?
 표현식이라고 함
 String data = new Date().toString(); ==> 자바 내에서는 지역 코드가 됨
 
 jsp에서  진짜로 실행되고 있는 것  
 D:\A_TeachingMaterial\6.JspSpring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\webStudy01\org\apache\jsp\_01
 
 서블릿 			jsp 
 컨테이너의 역할이 다름 
 
 -->


</body>
</html>