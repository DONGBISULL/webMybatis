<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/performance.jsp</title>
</head>
<body>
<h4>성능체크</h4>
<pre>
	소요시간 : process time + latency time pooling 데이터를 담아둔다 --> 연결을 끊는게 아니라 담아뒀다가 반납을 받아서 재사용을 함 
	
	<a href="oneConnOneProcess.jsp">한 번의 연결과 한 번의 처리 :28ms</a>
	<a href="100Conn100Process.jsp">100 번의 연결과 100번의 처리 :1100ms</a>
	<a href="oneConn100Process.jsp">1번의 연결과 100번의 처리 :40ms</a>


	int 대신 integer 
	String 대신 StringBuffer 쓰기 

</pre>
</body>
</html>