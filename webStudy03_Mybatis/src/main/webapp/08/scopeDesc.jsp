<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/08/scopeDesc.jsp </title>
</head>
<body>
<h4>웹 어플리케이션 데이터 공유(전달)</h4>
스코프 <br>
어플리케이션>세션>리퀘스트> 페이지 컨텍스트<br> 
어플리케이션 스코프>세션 스코프>리퀘스트 스코프> 페이지 컨텍스트 스코프<br>
<pre>
	Scope : 4개의 기본 객체가 소유한 map&lt;String ,Object&gt;
	 setAttribute(name , value) , getAttribute(name) , removeAttribute(name)	
		1. page scope (PageContext)
		2. request scope(request)
	----------------------------------	 
	사라지는 순간이 불명확함 ==> 적절한 시점에 지워주는 게 중요함 removeAttribute(name)
		3. session scope(session) 
		4. application scope (ServeltContext) ==> 서버가 살아있는한 계속 남아있음 ==> 서버의 과부하 야기
		==>세션이 소멸되면 session scope도 없어짐

</pre>

</body>
</html>