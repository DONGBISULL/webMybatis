<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionTimer.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom.js"></script>

<script type="text/javascript">
	//$.customAlert("메시지");
	
	$(function(){ //클라이언트 사이드에서 사용될 데이터들
		let element = $("#timerArea").sessionTimer({
			timeout : <%=session.getMaxInactiveInterval() %>
		<%-- , url :"<%=request.getContextPath()%>/sessionExtend" --%>
		});
	
		<%-- let noBtn= $("#noBtn")
		let yesBtn= $("#yesBtn")
		let mArea =$("#messageArea");
		
		let timeout = parseInt(element.text());
		let timeset = <%=session.getMaxInactiveInterval() %>	
		
		setInterval(function(){
 			let minute = Math.floor(timeout/60);
			let second = timeout%60;
			timeout --; 	
			element.html(minute +":" +second )
				 if(timeout==60){
					mArea.show();
				} 
				 if(timeout<0){
				timeout = timeset
				} 
				 
		}, 1000); --%>
	 
		
		<%-- noBtn.on("click" , function(){
			mArea.hide()
		})
		
		
		
		
		
		yesBtn.on("click" , function(){
			
			mArea.hide()
			
			$.ajax({
				url : "<%=request.getContextPath()%>/sessionExtend",
				method : "head",//응답을 보내는 방식
				success : function(resp) {
					timeout = parseInt(timeset);
					console.log(timeout)
				},
				error : function(errorResp) {
					alert(errorResp)
				}	

			}); 
		}); 
		
		 --%>
		
	});
	
</script>
</head>
<style>
#messageArea{
display: none;
}

</style>
<body>
<h4>세션 타이머</h4>
 <%=session.getId() %>    <!-- 30분까지세션유지 -->
<h4 id ="timerArea"></h4>
<p></p>
<!-- 1. 1초마다 출력되는 시간을 디스카운트

2. 1분 남은 시점에 메시지 출력 
	세션 연장 여부 확인 
3.세션 연장시
1) 예를 선택한 경우 타이머 리셋  
2) 세션 연장하기위한 새로운 요청 발생(비동기 요청 - /sessionExtend, body가 없는 응답)//body가 없을 경우 jsp라는 스펙은 필요가 없음==> 서블릿을 통해 만들기 
 -->


</body>
</html>