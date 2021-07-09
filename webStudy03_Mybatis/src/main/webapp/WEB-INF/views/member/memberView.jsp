<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.Set"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<style>

<style>
 
td,tr, th{
 border : 1px solid lightgray; 
padding :10px;

}
</style>

 

</head>
<body>
	<%
		//dispatch 방식으로 꺼내기 

		MemberVO member = (MemberVO) request.getAttribute("member");
		//컨텐츠 꺼내기 
	String message = (String)session.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		// flash attribute
		session.removeAttribute("message");
		%>
		<script type="text/javascript">
			alert("<%=message %>");
		</script>
		<%
	}
%>
	
	<%= member.getMemName()%>님의 마이페이지
			
	<table class='table table-bordered '>
		<tbody>
			<tr>
				<th>회원 id</th>
				<td><%=member.getMemId() %></td></tr>
			<tr> <th>비밀 번호</th><td><%=member.getMemPass() %></td>
			<tr>		<th>회원명</th>      <td><%=member.getMemName() %></td>   </tr>
			<tr>		<th>주민등록번호1</th>   <td><%=member.getMemRegno1() %></td></tr>
			<tr>		<th>주민등록번호2</th>  <td><%=member.getMemRegno2() %></td> </tr>
			<tr>		<th>생일</th>      <td><%=member.getMemBir() %></td>      </tr>
			<tr>		<th>우편번호</th>   <td><%=member.getMemZip() %></td>    </tr>
			<tr>		<th>주소 1</th>     <td><%=member.getMemAdd1() %></td>     </tr>
			<tr>		<th>주소 2</th>    <td><%=member.getMemAdd2() %></td>     </tr>
			<tr>		<th>집 전화 번호</th> 	<td><%=member.getMemHometel() %></td>   </tr>
			<tr>		<th>회사 전화번호</th><td><%=member.getMemComtel() %></td>  </tr>
			<tr>		<th>이동 전화번호</th> <td><%=member.getMemHp() %></td>  </tr>
			<tr>		<th>메일</th>     <td><%=member.getMemMail() %></td>      </tr>
			<tr>		<th>직업</th>       <td><%=member.getMemJob() %></td>     </tr>
			<tr>		<th>취미</th>     <td><%=member.getMemLike() %></td>       </tr>
			<tr>		<th>기념일 명</th> <td><%=member.getMemMemorial() %></td>     </tr>
			<tr>		<th>기념일 날짜</th>   <td><%=member.getMemMemorialday() %></td>  </tr>
			<tr>		<th>마일리지</th>   <td><%=member.getMemMileage() %></td>    </tr>
			<tr>		<th>삭제 여부</th>   	<td><%=member.getMemDelete() %></td>    </tr>
			<tr>
			<th>구매기록</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>상품 분류 </th>
							<th>거래처명</th>
							<th>거래처 주소</th>
							<th>상품명</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
						<%
							Set<ProdVO> prodList = member.getProdList();
							if(prodList.isEmpty()){
								%>		
								
								<tr>
								<td colspan="6">구매기록 없음</td>
								</tr>
								
						<%		
							}else{
								for(ProdVO prod : prodList){
								%>		
								<tr>
								<td><%=prod.getLprodNm() %></td>
								<td><%=prod.getBuyer().getBuyerName() %></td>
								<td><%=prod.getBuyer().getBuyerAdd1() %></td>
								<td><%=prod.getLprodNm()%></td>
								<td><%=prod.getProdCost() %></td>
								<td><%=prod.getProdPrice() %></td>
								<td><%=prod.getProdMileage() %></td>
								 
								
								</tr>								
								
							<%	}
							}
						%>
					</tbody>
					
				</table>
			</td>
			</tr>
			
<%
	MemberVO authMember = (MemberVO)	session.getAttribute("authMember");
	boolean rendering = member.equals(authMember);
	if(rendering){

%>

			<tr>
				<td colspan="2">
					<input type="button" value="수정" class="controlBtn" id="modify" data-url="<%=request.getContextPath() %>/member/memberUpdate.do"  > 
					<input type="button" value="탈퇴" id="deleteBtn"   > 
					<form method="post" id="deleteForm" action="<%=request.getContextPath()%>/member/memberDelete.do">
						<input  type="hidden"  name="memPass"  />
						
					</form>
			</tr>
 <%} %> 
		</tbody>

	</table>

	<script type="text/javascript">
	
		let modibutton = $("#modify");
		let deleteForm = $("#deleteForm");
		let deleteBtn = $("#deleteBtn")
		
		modibutton.on("click" , function(){
			url = $(this).data("url");
			
			 location.href=url;
		})
		deleteBtn.on("click" , function(){
			let memPass  = prompt("비밀번호");
			if(memPass){
				
			deleteForm[0].memPass.value = memPass;
			deleteForm.submit();
			} 
		});
		
		
 
	</script>
 
</body>
</html>