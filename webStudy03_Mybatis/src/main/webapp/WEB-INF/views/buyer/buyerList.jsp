<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#pagingArea{

	text-align:  center;
}

</style>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}")
	</script>
</c:if>
<div id="searchUI" >
<h3><a href="${pageContext.request.contextPath }/buyer/buyerList.do">거래처 리스트</a></h3>
	<h4>Search UI</h4>
	<select name="prodLgu">
		<option>분류</option>
		<c:forEach items="${lprodList}" var="lprod">
		<option value="${lprod.lprod_gu}">${lprod.lprod_nm}</option>
		</c:forEach>
	</select>
	<label>거래처 이름</label>
	 <input type="text" name="buyerName" value="${paging.detailSearch.buyerName }"/>
	<label>거래처 주소</label>
	 <input  name="buyerAdd1" value="${paging.detailSearch.buyerAdd1}"/>
	 <input type="button" value="검색" id="searchBtn" class = "btn btn-light"/>
	  <input type="button" value="거래처 등록" class="btn btn-outline-secondary controlBtn " data-gopage="${pageContext.request.contextPath }/buyer/buyerInsert.do"/>
</div>

 <table class="table">
	<thead class="thead-dark">
		<tr>
		<th>거래처 ID</th> 
		<th>거래처 이름</th> 
		<th>거래처 분류</th> 
		<th>거래처 은행</th> 
		<th>거래처 계좌번호</th> 
		<th>거래처 우편 번호 </th> 
		<th>거래처 주소</th> 
		<th>거래처 전화번호</th> 
		<th>거래처 메일</th> 
	 	</tr>
	</thead>
	
	<tbody>
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan="9">
				<div id="pagingArea">
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input name="page" >
	<input name="buyerLgu" value="${paging.detailSearch.buyerLgu}">
	<input name="buyerName" value="${paging.detailSearch.buyerName}"/>
	<input name="buyerAdd1" value="${paging.detailSearch.buyerAdd1}"/>
	
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/paging.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$(".controlBtn").on("click" , function(){
			
			let gopage = $(this).data("gopage");
			if(gopage){
				location.href= gopage;
			}
		})
		
		
		let pagingArea = $("#pagingArea");
		
		let tbody = $("table>tbody").on("click" , "tr" , function(){
			let buyer = $(this).data("buyer")
			if(!buyer) return false;
			let buyerId = buyer.buyerId;
			location.href= "${pageContext.request.contextPath }/buyer/buyerView.do?what=" +buyerId;
		})
		
		
		let searchForm = $("#searchForm").paging({
			pagingArea : "#pagingArea",
			pageLink : ".pageLink",
			searchUI : "#searchUI",
			btnSelector : "#searchBtn",
			pageKey : "page",
			pageParam : "page"
		}).ajaxForm({
			dataType :"json",
			success :function(paging){
			 	tbody.empty();
			 	pagingArea.empty();
			 	let buyerList  = paging.dataList;
			 	let trTags = [];
			 	console.log(paging)
				 if(buyerList){
					 $(buyerList).each(function(idx , buyer){
						 trTags.push(makeTrTag(buyer));
					 });
					 pagingArea.html(paging.pagingHTML)
				 }else {
					 trTags.push(
						$("<tr>").html(
						$("<td>").attr("colspan" ,"6" ).html("조건에 맞는 결과가 없음")
						)
					 )
				 };
				 tbody.append(trTags);
			}  
		
			
		}).submit() // ajax로 받기 위해서는 강제로 한 번 제출하는 과정이 필요함                                                                                                                                     
	 
		
		
	function makeTrTag(buyer){
			return $("<tr>").append(
				$("<td>").html(buyer.buyerId) ,	
				$("<td>").html(buyer.buyerName)	,
				$("<td>").html(buyer.lprodNm).attr("class" , buyer.buyerLgu),
		 
				$("<td>").html(buyer.buyerBank)	,
				$("<td>").html(buyer.buyerBankno),	
				$("<td>").html(buyer.buyerZip)	,
				
				$("<td>").html(buyer.buyerAdd1)	,
				$("<td>").html(buyer.buyerComtel)	,
				$("<td>").html(buyer.buyerMail)	
			).data("buyer" , buyer);
		}
		
	});

</script>


<jsp:include page="/includee/footer.jsp" />
</body>
</html>