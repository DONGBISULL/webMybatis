<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<h4>JSP(Java Server Page) 표준 구성 요소</h4>
<pre>
	1. 정적 텍스트(Front-End , Client side) : html ,javascript , css
	2. Back-End , Server side 
		1) scriptlet : <% //java code %> , 지역 코드화 
		==> 지역 변수화가 되기 때문에 메서드 안에 메서드가 들어가는 형태가 됨 이 때 declaration 사용 
		2) expression : <%="출력데이터" %>
		3) direcrive : <%--@ 지시자명--%>
		<%!
			public String test(){
			return "테스트맨~~~!!";
		}
		%>
			- page : 현재 jsp 페이지에대한 설정 정보 (mime, import,errorPage) ==> 필수 지시
			- include : 정적 내포
			- taglib : 커스텀 태그 라이브러리 로딩
		4) declaration : 선언문 <%! //전역 멤버 선언 %>
		5) comment :<%-- --%>
			-client side comment ; HTML , Javascript , CSS
<!-- 			<div></div>  -->
			<script type="text/javascript">
// 			 자바스크립트 주석 
			</script>
	<style>
/* 	table{ */
/* 	} */
	</style>			
			-server side comment : Java , jsp 
			<%
				//single line
				/*
				multi line
				--> 사용자에게 보이지 않음 
				--> 서버에 부하가 걸리지 않음
				*/
				/**
				doc 
				**/
			%>
			<%--
			JSP Comment
			 --%>
buffer ==> 데이터를 버퍼에 담아서 보관하고 있다가 한 번에 flush를 해줌 
autoFlush ==> 차있는 상태에서 더 들어올 경우 자동으로 비울지 말지 선택 
import ==> 데이터 서블릿으로 바뀔 때 자동으로 import 됨 
isErrorPage 에러가 났을 때 처리할 페이지일 경우 그걸 선택할 수 있음 
trimDrectiveWhitespace() ==>contentType image/jpeg;일경우 
==> 한줄이 뚤려있으면 이미지에서 에러가 날 수 있음
	3. 기본 객체 
	4. 액션 태그 
	5. EL(표현언어)
	6. JSTL(tag library)

주석 ==> 서로간의 소통할 목적으로 주석 사용 
BUT 응답 데이터에도 포함됨 
==> 클라이언트 사이드 주석을 사용하지 말아라!!!! 다 보임 


</pre>

