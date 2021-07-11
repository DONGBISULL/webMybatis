<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
<title>입력폼</title>
</head>
<body>
<c:if test="${not empty message }">
<script type="text/javascript">
		alert("${message}");
</script>
</c:if>
	<form method="post" id="buyerForm">
		<table>
			<tbody>
				<%-- <tr>
					<th>거래처 ID</th>
					<td><input type="text" name="buyerId" required
						value="${buyer.buyerId}" /><label id="buyerId-error" class="error"
						for="buyerId">${errors.buyerId}</label></td>
				</tr> --%>
				<tr>
					<th>거래처 이름</th>
					<td><input type="text" name="buyerName" required
						value="${buyer.buyerName}" /><label id="buyerName-error"
						class="error" for="buyerName">${errors.buyerName}</label></td>
				</tr>
				<tr>
					<th>거래처 분류</th>
					<td>
					<select name="buyerLgu" required >
						<option>분류</option>
						<c:forEach items="${lprodList }" var="lprod">
							<option value="${lprod.lprod_gu }" >
								${lprod.lprod_nm }
							</option>
						</c:forEach>
					</select>
					 <label id="buyerLgu-error"
						class="error" for="buyerLgu">${errors.buyerLgu}</label></td>
						
						
				</tr>
				<tr>
					<th>거래처 은행</th>
					<td><input type="text" name="buyerBank"
						value="${buyer.buyerBank}" /><label id="buyerBank-error"
						class="error" for="buyerBank">${errors.buyerBank}</label></td>
				</tr>
				<tr>
					<th>거래처 계좌번호</th>
					<td><input type="text" name="buyerBankno"
						value="${buyer.buyerBankno}" /><label id="buyerBankno-error"
						class="error" for="buyerBankno">${errors.buyerBankno}</label></td>
				</tr>
				<tr>
					<th>거래처 계좌 명의</th>
					<td><input type="text" name="buyerBankname"
						value="${buyer.buyerBankname}" /><label id="buyerBankname-error"
						class="error" for="buyerBankname">${errors.buyerBankname}</label></td>
				</tr>
				<tr>
					<th>거래처 우편 번호</th>
					<td><input type="text" name="buyerZip"
						value="${buyer.buyerZip}" /><label id="buyerZip-error"
						class="error" for="buyerZip">${errors.buyerZip}</label></td>
				</tr>
				<tr>
					<th>거래처 주소</th>
					<td><input type="text" name="buyerAdd1"
						value="${buyer.buyerAdd1}" /><label id="buyerAdd1-error"
						class="error" for="buyerAdd1">${errors.buyerAdd1}</label></td>
				</tr>
				<tr>
					<th>거래처 상세 주소</th>
					<td><input type="text" name="buyerAdd2"
						value="${buyer.buyerAdd2}" /><label id="buyerAdd2-error"
						class="error" for="buyerAdd2">${errors.buyerAdd2}</label></td>
				</tr>
				<tr>
					<th>거래처 전화번호</th>
					<td><input type="text" name="buyerComtel" required
						value="${buyer.buyerComtel}" /><label id="buyerComtel-error"
						class="error" for="buyerComtel">${errors.buyerComtel}</label></td>
				</tr>
				<tr>
					<th>거래처 팩스 번호</th>
					<td><input type="text" name="buyerFax" required
						value="${buyer.buyerFax}" /><label id="buyerFax-error"
						class="error" for="buyerFax">${errors.buyerFax}</label></td>
				</tr>
				<tr>
					<th>거래처 메일</th>
					<td><input type="text" name="buyerMail" required
						value="${buyer.buyerMail}" /><label id="buyerMail-error"
						class="error" for="buyerMail">${errors.buyerMail}</label></td>
				</tr>
				<tr>
					<th>거래처 책임자</th>
					<td><input type="text" name="buyerCharger"
						value="${buyer.buyerCharger}" /><label id="buyerCharger-error"
						class="error" for="buyerCharger">${errors.buyerCharger}</label></td>
				</tr>
				<tr>
					<th>Telext</th>
					<td><input type="text" name="buyerTelext"
						value="${buyer.buyerTelext}" /><label id="buyerTelext-error"
						class="error" for="buyerTelext">${errors.buyerTelext}</label></td>
				</tr>
				<tr>
					<td>
					<input type="submit" value="저장" class="btn">
					<input type="reset" value="초기화" class="btn">
					<input type="button" value="목록으로" class="btn controlBtn" data-gopage="${pageContext.request.contextPath }/buyer/buyerList.do">
					</td>
				</tr>

			</tbody>

		</table>

	</form>


	<jsp:include page="/includee/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	$(function(){
		
	$("#buyerForm").validate();
		
	$(".controlBtn").on("click" , function(){
		
			let gopage = $(this).data("gopage");
			if(gopage){
				location.href = gopage;
			}
		
		})	
	})//펑션
	
	
	</script>
	
</body>
</html>