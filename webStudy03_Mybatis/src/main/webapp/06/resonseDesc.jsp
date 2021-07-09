<%@ page language="java"  pageEncoding="UTF-8"%>

<%-- <%
	response.setContentType("text/html; charset=UTF-8");
	response.setHeader("Content-type", "text/html; charset=UTF-8");
	//서버가 가지고 있는기본 형태로 번역해줌

%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/responseDesc</title>
</head>
<body>
<h4>HttpServletResponse(response 기본객체)</h4>
	<pre>
		: 서버에서 클라이언트로 전송되는 데이터를 캡슐화한 객체 
		1. Response Line : protocol , status code (상태코드)
	<%-- 	<%
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"강제 서버 에러");
			return;
		%> --%>
		
		<!-- 날짜 데이터가 long 타입인 이유 
		자바도 오라클도 자바 스크립트도 밀리세컨드를 사용하고 있음 이 밀리 세컨드가 long 타입이기 때문
		 -->
		
				** 상태 코드 : 요청 처리의 결과를 표현하는 숫자 체계 
				100 ~ :	ing...
						서버와 클라이언트 사이에 통로가 연결됨
						사용이 끝나면 바로 연결을 끊어버림(Connectless)
						상태 유지 안 함(Stateless) 
						A(이용자) ---> S(서버) 요청을 보내면서 이용자가 보낸 정보를 바로 없애버림
						==> Http 실시간 양방향 통신 안됨 
				웹소켓 통로를 계속 연결해둘 수 있음(서버의 단점을 보완)
		
				200 ~ : OK  
				300 ~ :  처리 완료를 위해 클라이언트로부터 추가적인 액션이 필요한 경우
						304(Not Modified) , 302/307(Moved &lt; Location)
						
				400 ~ : client side fail 
						404(Not Found) , 405(Not support method)
						415 (Not support Media type) 
						400 (Bad Request) : 필수 파라미터 누락 /파라미터의 형식에 맞지 않게 넘겨줬을 때 / 파라미터의 길이에 어긋 날 경우 
						==> 파라미터 검증시 나타남 
						401 (UnAuthorized) : 인가 받지 않았다
						403 (Forbidden) : 금지됨
						
				500 ~ : server side fail 
						500(Internal Server Error) ==> 서버의 상황을 클라이언트에게 알리지 않기 위해서 하나로만 보여줌 
		2. Response Header : Meta data , setHeader(name , value);
				* Content-Type : body의 데이터 mime 
				* Cache-Control(1.1) , Pragma(1.0) , Expires(캐시의 만료 시간을 제어할 떄 사용): 캐시를 제어할 때 사용 
					<a href="cacheControl.jsp">캐시제어 예제</a>
						캐시 관리의 중요성 
					1. 캐시 데이터 ==> 정보 유출의 문제
					2. 갱신의 문제가 생김 
						실시간성을 보장해야 하는 경우 캐시를 사용하지 못하도록 설정을 해줘야 함
				* Refresh : auto request 
				<a href="autoRequest.jsp">Refresh를 통한 자동요청</a>
				* Location : 페이지 이동 
				<a href="flowControl.jsp">페이지 이동 예제</a>
					
		3. Response Body 
	
	</pre>

</body>
</html>