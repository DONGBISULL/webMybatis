<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
 
</head>
<body>
	<table class="table">
	 <tr>
			<th>거래처 아이디</th>
			<td>${buyer.buyerId }</td>
		</tr>
		<tr>
			<th>거래처 이름</th>
			<td>${buyer.buyerName }</td>
		</tr>
		<tr>
			<th>거래처 분류</th>
			<td>${buyer.lprodNm }</td>
		</tr>
		<tr>
		 <tr>
			<th>거래처 거래 상태</th>
			<td>
			${buyer.buyerStatus }
		 	</td>
		<tr>
			<th>거래처 은행</th>
			<td>${buyer.buyerBank }</td>
		</tr>
		<tr>
			<th>거래처 계좌번호</th>
			<td>${buyer.buyerBankno }</td>
		</tr>
		<tr>
			<th>거래처 계좌 명의</th>
			<td>${buyer.buyerBankname }</td>
		</tr>
		<tr>
			<th>거래처 우편 번호</th>
			<td>${buyer.buyerZip }</td>
		</tr>
		<tr>
			<th>거래처 주소</th>
			<td>${buyer.buyerAdd1 }</td>
		</tr>
		<tr>
			<th>거래처 상세 주소</th>
			<td>${buyer.buyerAdd2 }</td>
		</tr>
		<tr>
			<th>거래처 전화번호</th>
			<td>${buyer.buyerComtel }</td>
		</tr>
		<tr>
			<th>거래처 팩스 번호</th>
			<td>${buyer.buyerFax }</td>
		</tr>
		<tr>
			<th>거래처 메일</th>
			<td>${buyer.buyerMail }</td>
		</tr>
		<tr>
			<th>거래처 책임자</th>
			<td>${buyer.buyerCharger }</td>
		</tr>
		<tr>
			<th>Telext</th>
			<td>${buyer.buyerTelext }</td>
		</tr>
		
		<tr>
			<td colspan="2">
					<c:url value="/buyer/buyerUpdate.do" var="updateURL">
						<c:param name="what" value="${buyer.buyerId }" />
					</c:url>
				<a class="btn btn-primary" href="${updateURL }">수정</a> 
				<input type="button" value="삭제" class ="btn btn-danger controlBtn" id="deleteBtn" /> 
				<a class="btn btn-secondary" href="#" onclick="history.back();">뒤로가기</a> 
				<input type="button" value="목록으로" class="btn btn-secondary controlBtn" 
					data-gopage="${pageContext.request.contextPath }/buyer/buyerList.do" />
				<form id="deleteForm" method="Post" action="${pageContext.request.contextPath }/buyer/buyerdelete.do">
					<input type="hidden" value="${buyer.buyerId }" name="what">
				</form>
			
			</td>
		</tr>
		
		<tr>
			<th>상품 정보</th>
			<td>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th>상품 ID</th>
							<th>상품 이름</th>
							<th>매입가</th>
							<th>소비자가</th>
							<th>판매가</th>
							<th>신규일자(등록일)</th>
						</tr>
					</thead>
					<tbody>
					<!--  -->
					<c:set var="prodList" value="${ buyer.prodList}"></c:set>
						<c:choose>
							<c:when test="${not empty prodList }">
								<c:forEach items="${prodList }" var="prod">
									<tr>
										<td>${prod.prodId }</td>
										<td>${prod.prodName }</td>
										<td>${prod.prodCost }</td>
										<td>${prod.prodPrice }</td>
										<td>${prod.prodSale }</td>
										<td>${prod.prodInsdate }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6"> 등록된 상품 없음</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/includee/footer.jsp" />
	
	
	<script type="text/javascript">
	
	let deleteForm = $("#deleteForm");
	let deleteBtn = $("#deleteBtn")
	
	
	deleteBtn.on("click" , function(){
		let check = confirm("삭제하시겠습니까?")
		
		if(check){
		deleteForm.submit();
		}else{
			return false;
		}
		
	})
	
	</script>
</body>
</html>