<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elDesc.jsp</title>
</head>
<body>
<h4> 표현 언어 (Expression Language : EL) </h4>
<pre>
	: (속성)값을 출력(표현)할 목적만 가진 언어.
	\${속성명 }
	<%
		String sample = "데이터";
		pageContext.setAttribute("sampleAttr", "페이지 데이터");
		request.setAttribute("sampleAttr", sample);
		session.setAttribute("sampleAttr", "세션 데이터");
		application.setAttribute("sampleAttr", "어플리케이션 데이터");
		
		pageContext.setAttribute("text1", "  ");
		pageContext.setAttribute("array1", new String[]{});
	%>
	<%=sample %> , ${requestScope.sampleAttr }, <%=request.getAttribute("sampleAttr") %>
	${sampleAttraa }, <%=request.getAttribute("sampleAttraa") %>
	
	지원 기능
	1. 연산자
		산술연산 : +-*/% ${5+2 } ${"5"+"2" } \${"a"+"1" } ${ 5/2 } ${1 - def }
		논리연산 : &&(and) ||(or) !(not) ${true and true } ${"true" and "true" } 
									${true and abc } ${abc and def } ${not abc }
		비교연산 : >(gt), <(lt), >=(ge), <=(le), ==(eq), !=(ne)
				${ 3 lt 4 } ${3 gt abc },  ${sampleAttr eq "페이지 데이터" }
		단항연산자 : empty ${empty sampleAttraa}, ${empty text1 }, ${empty array1 }
		삼항연산자 : 조건식 ? 참 : 거짓
			${not empty abc ? "없다" : "있다" }
	2. 자바 객체의 메소드 호출 : ${applicationScope.sampleAttr.length() }
	<%
		MemberVO member = MemberVO.builder().memName("김은대").build();
		pageContext.setAttribute("member", member);
	%>
	${member.getMemName() }, ${member.memName }, ${member.getMemTest() }, ${member.memTest }
	3. collection 접근 방법 <a href="elCollection.jsp">collection 접근 방법</a>
	4. 기본 객체 (11-Map)
		Scope : pageScope, requestScope, sessionScope, applicationScope
				${sessionScope.sampleAttr }, ${sessionScope['sampleAttr'] }
				${pageScope.member.memName }, ${pageScope['member'].memName }, ${pageScope['member']['memName'] }
		요청parameter : param(Map&lt;String,String&gt;), paramValues(Map&lt;String,String[]&gt;) 	
				${param.param1 }, ${paramValues.param1[1] }
		요청header : header(Map&lt;String,String&gt;), headerValues(Map&lt;String,String[]&gt;)
			${header.accept }, ${header['accept'] }
			${header.user-agent }, ${header['user-agent'] }
		<%
			Cookie[] cookies = request.getCookies();
			for(Cookie tmp : cookies){
				if(tmp.getName().equals("JSESSIOND")){
					out.println(tmp.getValue());
				}
			}
		%>	
		cookie(Map&lt;String,Cookie&gt;) : 
			${cookie.JESSIONID.value }	, ${cookie['JESSIONID']['value'] }
		컨텍스트 파라미터 : initParam		
			<%=application.getInitParameter("cParam1") %>
			${initParam.cParam1 }, ${initParam['cParam1'] }	
		pageContext
			<%=pageContext.getRequest() %>, ${pageContext.request.contextPath }	
</pre>
</body>
</html>











