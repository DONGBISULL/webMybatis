<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<jsp:useBean id="prod" class="kr.or.ddit.vo.ProdVO" scope="request" />
<table class="table table-bordered">
		<tr>
			<th>상품 코드</th>
			<td>${prod.prodId }</td>
		</tr>
		<tr>
			<th>상품 명</th>
			<td>${prod.prodName }</td>
		</tr>
		<tr>
			<th>상품 분류 코드</th>
			<td>${prod.prodLgu }</td>
		</tr>
		<tr>
			<th>거래처 정보</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-light">
						<tr>
							<th>거래처명</th>
							<th>담당자</th>
							<th>지역</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${prod.buyer.buyerName }</td>
							<td>${prod.buyer.buyerCharger }</td>
							<td>${prod.buyer.buyerAdd1 }</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>매입가</th>
			<td>${prod.prodCost }</td>
		</tr>
		<tr>
			<th>소비자가</th>
			<td>${prod.prodPrice }</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prodSale }</td>
		</tr>
		<tr>
			<th>상품 개략 설명</th>
			<td>${prod.prodOutline }</td>
		</tr>
		<tr>
			<th>상품 상세 설명</th>
			<td>${prod.prodDetail }</td>
		</tr>
		<tr>
			<th>이미지(소)</th>
			<td>${prod.prodImg }</td>
		</tr>
		<tr>
			<th>재고수량</th>
			<td>${prod.prodTotalstock }</td>
		</tr>
		<tr>
			<th>신규일자(등록일)</th>
			<td>${prod.prodInsdate }</td>
		</tr>
		<tr>
			<th>안전 재고수량</th>
			<td>${prod.prodProperstock }</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize }</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor }</td>
		</tr>
		<tr>
			<th>배달 특기 사항</th>
			<td>${prod.prodDelivery }</td>
		</tr>
		<tr>
			<th>단위(수량)</th>
			<td>${prod.prodUnit }</td>
		</tr>
		<tr>
			<th>총 입고 수량</th>
			<td>${prod.prodQtyin }</td>
		</tr>
		<tr>
			<th>총 판매 수량</th>
			<td>${prod.prodQtysale }</td>
		</tr>
		<tr>
			<th>개당 마일리지 점수</th>
			<td>${prod.prodMileage }</td>
		</tr>
		<tr>
			<td colspan="2">
					<c:url value="/prod/prodUpdate.do" var="updateURL">
						<c:param name="what" value="${prod.prodId }" />
					</c:url>
				<a class="btn btn-primary" href="${updateURL }">수정</a> 
				<a class="btn btn-secondary" href="#" onclick="history.back();">뒤로가기</a> 
				<input type="button" value="목록으로" class="btn btn-secondary controlBtn" 
					data-gopage="${pageContext.request.contextPath }/prod/prodList.do " />
			</td>
		</tr>
		
		<tr>
			<th>구매자 정보</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>휴대폰</th>
							<th>메일</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="memberList" value="${prod.memberList }" />
						<c:choose>
							<c:when test="${not empty memberList}">
								<c:forEach items="${memberList }" var="member">
									<tr>
										<td>${member.memId}</td>								
										<td>${member.memName}</td>								
										<td>${member.memHp}</td>								
										<td>${member.memMail}</td>								
										<td>${member.memMileage}</td>								
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5"> 구매정보 없음. </td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
<jsp:include page="/includee/footer.jsp" />
</body>
</html>