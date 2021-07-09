<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<title>Insert title here</title>
<style type="text/css">

form{
border: 2px solid black;
padding: 5%;
} 

</style>
</head>

<body>
<pre>
left 입력을 통해 숫자를 입력받고 ,
값이 변경되는 순간 서버로 비동기 요청 발생시킴 
서버에서 factorial 연산을 수행후 
선택한 mime의 형태로 응답을 전송
plain ="2! = 2"
json : 
{
	left : 2 ,
	operater : !,
	expression : 2!=2

}
xml :
 &lt;result>
	 &lt;left> &lt;/left>
	 &lt;expression>2! =2 &lt;/expression>
 &lt;/result> 
</pre>
<form action="/05/factorial" id="factForm">
<input type="radio" name="mime" value="json"/>JSON
<input type="radio" name="mime" value="plain"/>PlAIN
<input type="radio" name="mime" value="xml"/>XML

<input type="number" name="left" min="1" max="10"/>!
</form>
<div id="resultArea"></div>

<!-- <result>
	<left></left>
	<expression>2! =2</expression>
</result> -->

<script type="text/javascript">
	let factForm = $("#factForm").on("change" , ":input" , function(){
		let area = $("#resultArea")[0]
		let data = {}
		let mime =$("input:radio[name='mime']:checked").val()
		let left= $("input[name='left']").val()
		let p =""
		
		
		let xmlPrnt ="xml : \n <result> \n <left>%s</left> \n<expression>%s</expression> \n</result>"
		
	 	if(mime=="json"){
			dataType="json"
		}else if(mime=="plain"){
			dataType="text"
		}else if(mime=="xml"){
			dataType="xml"
		}else{
			return false;
		}  
		
		if(left){
			data.left = left;
		}else{
			return false;
		}
		 
			
	 	$.ajax({
			url : "<%=request.getContextPath()%>/05/factorial",
			data : data,
			method : "post",//응답을 보내는 방식
			dataType :dataType,//받아올 때 
			success : function(resp) { 
				if(typeof resp=="string"){
					 p="plain=" + resp
					 
				}else if(typeof resp=='object'){ //제이슨과 xml 둘다 오브젝트로 인식 ==>  조건을 걸어줘야 함 --> 
					//라디오 버튼을 뭘 눌렀는지나 json만 가지고 있는 operater 라는 것으로 구분 가능 
						if(resp.operater!=null){
							p = "json : {  left " +  resp.left +", operater : " + resp.operater +" , expression :" +resp.expression+"}";
						}else{
							
							 $(resp).find("result").each(function(){
								 var left = $(this).find("left").text();
								 var expression = $(this).find("expression").text();
								 p = "<result> <left>" + left +"</left> <expression> "+expression + "</expression>";
							})
				  
						}
				}
					$("#resultArea").html(p)
				
			},
			error : function(errorResp) {

			}

		});  
		
	})//
	 

</script>
</body>
</html>