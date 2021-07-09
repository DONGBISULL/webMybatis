<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>05/messageView.jsp</title>
</head>
<body>
<!-- 데이타 타입 -->
<button type="button" class="msgBtn" data-lang="ko">한글 메세지 가져오기</button>
<button type="button" class="msgBtn" data-lang="en">영문 메세지 가져오기</button>
<h4 ></h4>
<!-- 
 h4라는 일부만 변경 시킬 예정 --> 
<script type="text/javascript">
		$(".msgBtn").on("click" , function(){
			
			let data = {}
				let lang = 	$(this).data("lang")
			if(lang){
				data.lang =lang
			}
				 $.ajax({
					url : "<%=request.getContextPath()%>/05/messageServiceWithLocale",
					data :data, 
					method: "post",
					dataType : "json",//받아올 때 
					success : function(resp) {
						//console.log(resp.bow)
						$("h4").html(resp.bow)
					},
					error : function(errorResp) {
						console.log(errorResp)
					}
				}); 
			});
</script>
</body>
</html>