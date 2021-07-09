<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
폼에 접근하기 위한 서블릿 
bts 정보에 접근할 용도의 서블릿 
상대 경로로 현재 폴더를 사용하겠다 
--> 콜백 메서드 분리해서 접근가능하게 ㅐㅑ라 

<%
Map<String ,String[]> btsMap = (Map)application.getAttribute("btsMap");

%>



<!-- <form method="post" >
<select name="btsMember">
	<option  selected disabled >선택</option>
	<option value="rm">RM</option>
	<option value="bui">뷔</option>
	<option value="jimin">지민</option>
	<option value="jin">진</option>
	<option value="suga">슈가</option>
	<option value="jhop">제이홉</option>
	<option value="jungkuk">정국</option>
</select>
</form> -->

검색한 시간이 찍히도록 하고 싶음 
<form method="post" >
<select name="btsMember" onchange="this.form.submit()">
	<option value >선택</option>
	 <%
	 	for(Entry<String, String[]> entry:btsMap.entrySet()){
	 		String code = entry.getKey();
	 		String[] info = entry.getValue();
	 		%>
	 	<option value="<%=code%>"><%=info[0] %></option>
	 <%	}
	 %>
</select>
</form>

</body>

<script type="text/javascript">
	
/* 	$("select[name='btsMember']").on("change" , function(){
		 
	$(this).parent().submit()
	});
 */
</script>
</html>