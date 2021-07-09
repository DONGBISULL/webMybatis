<%@page import="kr.or.ddit.enumtype.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/userAgent.jsp</title>
<!-- 클라이언트가 해석==> contextPath 필요함 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
 $(function(){
		const PATTERN = "당신의 브라우저는  %s입니다 OS의 종류는 %o입니다"; //단말기 정보를 받아서 내보내라..enum 사용해서
		let resultArea = $("#resultArea");
		
		//받아올 때 ==> 보낼 때 응답 타입에 의해 request헤더 & response 의 accept가 달라진다
		//데이터 타입이 json일 경우 Accept: application/json ==> accept 헤더를 참고해서 mime을 결정해야 함
		
		$("a:first").on("click" , function(event){
			event.preventDefault();
			$.ajax({ 
				url : "<%=request.getContextPath()%>/04/getBroserName", 
				//'/'가 들어가는 건 절대 경로즤~!
				dataType : "json", //request header(Accept) / response header(Content-Type)
				//text : text/plain , html:text/html ; json : application/json script: text/javascript
				//ajax ==> 문자열을 기반으로 하는 방식 에이젝스만으로 파일 업로드 실행 불가 
				
				
				//서로 다른 나라사람들끼리 대화할 때 번역 필요한 것처럼 마샬링 언 마샬링이 필요함
				success : function(resp) { //resp 타입은 String -> 일반적 text 형태로 받아오기 때문!
					let message = null;
					let os = null;
				if(typeof resp =="string"){
					arr =resp.split(",");
					message = arr[0];
					os =arr[1]
				}else{
					message = resp.browser;
					os =resp.os;
				}
				resultArea.empty();
				resultArea.append(
						$("<p>").html(PATTERN.replace("%s" ,message).replace("%o" , os)) 
					
						 				
					) 	
			 
				},
				error : function(errorResp) {
					console.log(errorResp);
				}

			});
			return false;
		});
	 
 })
</script>
</head>
<body>
<a href="#">브라우저의 이름 받아오기(비동기)</a>
<div id="resultArea">

</div>
<!-- 사용자의 브라우저를 식별하고 각 브라우저에 맞는 메시지 출력 -->
<!-- "당신의 브라우저는 ooo입니다 형식으로 포맷팅 메시지를 사용함"
비동기와 동기 메서드 차이 
==> 화면에 락이 걸리지 않느냐 차이 
다른 차이 없다
-->

<%-- <%

/*Map<String , String> browserMap = new LinkedHashMap<>();//이전 엔트리와 지금 엔트리 사이에 순서를 가지고 있음 
  	browserMap.put("MSIE" , "익스플로러 구버전");
	browserMap.put("TRIDENT" , "익스플로러 최근버전");
	browserMap.put("OPERA" , "오페라");
	browserMap.put("FIREFOX" , "파이어폭스");
	browserMap.put("EDG" , "엣지");
	browserMap.put("CHROME" , "크롬");
	browserMap.put("SAFARI" , "사파리");
	browserMap.put("OTHER" , "기타"); */

%> --%>

</body>
</html>