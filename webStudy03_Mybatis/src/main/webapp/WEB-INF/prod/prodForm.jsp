<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.min.js"></script>
</head>
<body>
	<form method="post" id="prodForm">
	<table class="table table-bordered">
		<tr>
			<th>상품 코드</th>
			<td><input class="form-control" type="text" name="prodId" value="${prod.prodId }" /><label id="prodId-error" class="error"
				for="prodId">${errors.prodId}</label></td>
		</tr>
		<tr>
			<th>상품 명</th>
			<td><input class="form-control" type="text" name="prodName" required
				value="${prod.prodName }" /><label id="prodName-error"
				class="error" for="prodName">${errors.prodName}</label></td>
		</tr>
		<tr>
			<th>상품 분류 코드</th>
			<td>
				<select class="form-control" name="prodLgu" required>
					<option value>분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option value="${lprod.lprod_gu }" >
							${lprod.lprod_nm }
						</option>
					</c:forEach>
				</select>
				<label id="prodLgu-error" class="error"
					for="prodLgu">${errors.prodLgu}</label></td>
		</tr>
		<tr>
			<th>거래처 코드</th>
			<td>
				<select class="form-control" name="prodBuyer" required>
					<option value>거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">
							${buyer.buyerName }
						</option>
					</c:forEach>
				</select>
						<label id="prodBuyer-error"
							class="error" for="prodBuyer">${errors.prodBuyer}</label></td>
		</tr>
		<tr>
			<th>매입가</th>
			<td><input class="form-control" type="text" name="prodCost" required
				value="${prod.prodCost }" /><label id="prodCost-error"
				class="error" for="prodCost">${errors.prodCost}</label></td>
		</tr>
		<tr>
			<th>소비자가</th>
			<td><input class="form-control" type="text" name="prodPrice" required
				value="${prod.prodPrice }" /><label id="prodPrice-error"
				class="error" for="prodPrice">${errors.prodPrice}</label></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input class="form-control" type="text" name="prodSale" required
				value="${prod.prodSale }" /><label id="prodSale-error"
				class="error" for="prodSale">${errors.prodSale}</label></td>
		</tr>
		<tr>
			<th>상품 개략 설명</th>
			<td><input class="form-control" type="text" name="prodOutline" required
				value="${prod.prodOutline }" /><label id="prodOutline-error"
				class="error" for="prodOutline">${errors.prodOutline}</label></td>
		</tr>
		<tr>
			<th>상품 상세 설명</th>
			<td><input class="form-control" type="text" name="prodDetail"
				value="${prod.prodDetail }" />	<label id="prodDetail-error"
				class="error" for="prodDetail">${errors.prodDetail}</label></td>
		</tr>
		<tr>
			<th>이미지(소)</th>
			<td><input class="form-control" type="text" name="prodImg" required
				value="${prod.prodImg }" /><label id="prodImg-error" class="error"
				for="prodImg">${errors.prodImg}</label></td>
		</tr>
		<tr>
			<th>재고수량</th>
			<td><input class="form-control" type="text" name="prodTotalstock" required
				value="${prod.prodTotalstock }" /><label id="prodTotalstock-error"
				class="error" for="prodTotalstock">${errors.prodTotalstock}</label></td>
		</tr>
		<tr>
			<th>신규일자(등록일)</th>
			<td><input class="form-control" type="date" name="prodInsdate"
				value="${prod.prodInsdate }" /><label id="prodInsdate-error"
				class="error" for="prodInsdate">${errors.prodInsdate}</label></td>
		</tr>
		<tr>
			<th>안전 재고수량</th>
			<td><input class="form-control" type="text" name="prodProperstock" required
				value="${prod.prodProperstock }" /><label
				id="prodProperstock-error" class="error" for="prodProperstock">${errors.prodProperstock}</label></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input class="form-control" type="text" name="prodSize" value="${prod.prodSize }" /><label
				id="prodSize-error" class="error" for="prodSize">${errors.prodSize}</label></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input class="form-control" type="text" name="prodColor"
				value="${prod.prodColor }" /><label id="prodColor-error"
				class="error" for="prodColor">${errors.prodColor}</label></td>
		</tr>
		<tr>
			<th>배달 특기 사항</th>
			<td><input class="form-control" type="text" name="prodDelivery"
				value="${prod.prodDelivery }" /><label id="prodDelivery-error"
				class="error" for="prodDelivery">${errors.prodDelivery}</label></td>
		</tr>
		<tr>
			<th>단위(수량)</th>
			<td><input class="form-control" type="text" name="prodUnit" value="${prod.prodUnit }" /><label
				id="prodUnit-error" class="error" for="prodUnit">${errors.prodUnit}</label></td>
		</tr>
		<tr>
			<th>총 입고 수량</th>
			<td><input class="form-control" type="text" name="prodQtyin"
				value="${prod.prodQtyin }" /><label id="prodQtyin-error"
				class="error" for="prodQtyin">${errors.prodQtyin}</label></td>
		</tr>
		<tr>
			<th>총 판매 수량</th>
			<td><input class="form-control" type="text" name="prodQtysale"
				value="${prod.prodQtysale }" /><label id="prodQtysale-error"
				class="error" for="prodQtysale">${errors.prodQtysale}</label></td>
		</tr>
		<tr>
			<th>개당 마일리지 점수</th>
			<td><input class="form-control" type="text" name="prodMileage"
				value="${prod.prodMileage }" /><label id="prodMileage-error"
				class="error" for="prodMileage">${errors.prodMileage}</label></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-success"/>
				<input type="reset" value="취소" class="btn btn-danger" />
				<input type="button" value="목록으로" class="btn btn-secondary controlBtn" 
					data-gopage="${pageContext.request.contextPath }/prod/prodList.do"
				/>
			</td>
		</tr>
	</table>
	</form>
	<script type="text/javascript">
		$(function(){
			$("#prodForm").validate();
			let prodBuyer = $("select[name='prodBuyer']");
			$("select[name='prodLgu']").on("change", function(){
				let lgu = $(this).val();
				if(lgu){
					prodBuyer.find("option").hide();
					prodBuyer.find("option."+lgu).show();
					prodBuyer.find("option:first").show();
				}else{
					prodBuyer.find("option").show();
				}
			});
			$(".controlBtn").on("click", function(){
				let gopage = $(this).data("gopage");
				if(gopage){
					location.href = gopage;
				}
			});	
		});
	</script>
<jsp:include page="/includee/footer.jsp" />	
</body>
</html>







