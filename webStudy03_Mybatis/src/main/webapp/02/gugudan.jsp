<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	private String pattern = "%d*%d=%d";
	private String gugudanProcess(int dan , int mul){
	//이런 형태랑 똑같음 --> 자기자신밖에 못 쓰니까 이걸 해결하기 위해 스코프 사용 requset.setAttribute()
		return String.format(pattern, dan , mul, (dan*mul));
}

%>   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/gugudan.jsp</title>
</head>
<body>

<table>
	<%
	
	for(int dan = 2; dan<=9 ;dan++){
	%>	
	
	<tr>
	<%	
		for(int mul =1 ; mul <=9 ;mul++){
	%>			
	
		<td><%=gugudanProcess(dan,mul)%></td>	
		<%			
		}
		%>
</tr>
<%
	}
	%>

</table>


</body>
</html>