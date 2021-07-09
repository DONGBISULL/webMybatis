<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

.error{

color: red;
}
 
</style>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom.js"></script>
</head>
<body>
1 클라이언트 사이드에서 꼭 아이디 받도록! 없으면 못 넘어가게 
2 비밀번호 : 영문자와 특수 문자 a
3 제이쿼리 validate https://jqueryvalidation.org/files/demo/
 
<%-- <%
	String failId = request.getParameter("mem_id");
	
%>
 --%>
 <%
 //flash attribute 방식
 	String message = (String) session.getAttribute("message");
 	session.removeAttribute("message");
 %>
 
 
<div class="error">
<%-- <%=session.getAttribute("errors") %> --%>
<%=message %>
${errors }

</div>
<%-- <%reqeust.getParameter("")%> --%>
<form action="<%=request.getContextPath()%>/login/loginCheckT.do" method="post" name="loginForm">
	<ul>
		<li>
			아이디 : <input type ="text" required="required" data-msg-required="필수 데이터" name="mem_id" value="${failId}"/>
		</li>
		<li>
			비밀번호 : <input type="text" name="mem_pass" required="required" data-msg-pattern="형식 확인"/>
			<!-- pattern="^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8}$"  -->
			<input type="submit" value="로그인" >
		</li>
	</ul>

</form>
<script type="text/javascript">
	/* $("[name='loginForm']").validate(); */

	/* $("[name='loginForm']").on("submit" , function(){
		let regexPtrn = this.mem_pass.pattern;
		let password = this.mem_pass.value;
		let regexp = new RegExp(regexPtrn,"gm")
		let result = regexp.test(password);
		console.log(regexp.exec(password));
		
		//return result;
		return true;
	}); */


	/* 
 
	 */
	
			
	 
	
</script>

</body>
</html>