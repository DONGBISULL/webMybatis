<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom.js"></script>
</head>
<body>
1 클라이언트 사이드에서 꼭 아이디 받도록! 없으면 못 넘어가게 
2 비밀번호 : 영문자와 특수 문자 a
3 제이쿼리 validate https://jqueryvalidation.org/files/demo/

<%-- <%reqeust.getParameter("")%> --%>
<form action="<%=request.getContextPath() %>/login/loginCheck.do" method="post" id="loginForm">
	<ul>
		<li>
			아이디 : <input type ="text" name="mem_id" value="<%=request.getAttribute("mem_id")==null ?"":request.getAttribute("mem_id")%>" >
		</li>
		<li>
			비밀번호 : <input type="text" name="mem_pass" />
			<input type="submit" value="로그인">
		</li>
	</ul>

</form>
<script type="text/javascript">

	
	let sub =  $("input[type='submit']")
	 /*  sub.on("click" , function(event){ */
		 // const pattern = "(?=.*[a-zA-Z])(?=.*[?#!@#$%^&*-]).{2,8}"
		 
		
		  
		let forms =$("#loginForm");
		//event.preventDefault();
		let memId =$("input[name='mem_id']").val().trim()
		let memPass =$("input[name='mem_pass']").val().trim()
	 
		
		$.validator.addMethod("regex", function(value, element, regexp) {
			let re = new RegExp(regexp);
			let res = re.test(value);
			console.log(res, value, regexp, re)
			return res;
		});
		
		if(memId==null  ||memPass==null){
			$.customAlert("필수 입력항목입니다")
			event.preventDefault();
		} 
			
			 $("#loginForm").validate({
					rules :{
						mem_id :{
							 	required : true,
							 	minlength : 2
								},
						mem_pass :{
								required :true ,
								regex : "(?=.*[a-zA-Z])(?=.*[!@#\$%\^\&\*]).{4,8}",
								minlength : 2
						}
						
					},
					
					messages : {
							mem_id : {required :"필수 입력 항목입니다",
									 minlenth :"길이가 맞지 않습니다"
							},
						mem_pass :{
							required :"필수 입력 항목입니다",
							regex :"정규식에 맞지 않습니다",
							minlenth :"길이가 맞지 않습니다"
						}
						
					}
					
				});
	
	
			
	 
	
</script>

</body>
</html>