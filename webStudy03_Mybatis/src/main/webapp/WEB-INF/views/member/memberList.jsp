<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<style>
table{
border : 1px solid lightgray;
border-collapse: collapse;

}

td,tr, th{
border : 1px solid lightgray;
padding :10px;

}
</style>
<title>Insert title here</title>
</head>
<body>
 
<table  class="table table-hover" >

<tbody>
	<tr>
	<th>아이디</th><th>이름</th><th>지역</th><th>휴대폰</th><th>이메일</th><th>마일리지</th>
	</tr>
	 
<%
	PagingVO<MemberVO> pagingVO = (PagingVO)request.getAttribute("pagingVO");
	List<MemberVO> memList =pagingVO.getDataList();
	if(	memList.size()==0){
		
%>
	<tr> <td colspan="6">존재하는 회원이 없습니다</td></tr>
<%}
	for(int i = 0 ; i<memList.size();i++){
		MemberVO vo = memList.get(i);
%>
<tr id="<%=vo.getMemId()%>">
	<td ><%=vo.getMemId() %></td> 
	<td><%=vo.getMemName() %></td> 
	<td><%=vo.getMemAdd1() %></td> 
	<td><%=vo.getMemHp() %></td> 
	<td><%=vo.getMemMail() %></td> 
	<td><%=vo.getMemMileage() %></td> 
	</tr>

<%
	}
%>

</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<%=pagingVO.getPagingHTML() %>
				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
						<option value="hp">휴대폰</option>
					</select>
					<input name="searchWord"/>
					<button type="button" id="searchBtn">검색</button>
				</div>
			</td>
		</tr>
	</tfoot>

</table>

<form id="searchForm">
	<input type="text" name="searchType"/>
	<input type="text" name="searchWord"/>
	<input type="text" name="page"/>

</form>


	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog  modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


	<script type="text/javascript">
	$(function(){
		$("[name='searchType']").val("${pagingVO.simpleSearch.searchType}");
		$("[name='searchWord']").val("${pagingVO.simpleSearch.searchWord}");//null 값을 whitespace로 바꿈 
		$(".pageLink").on("click" , function(event){
			event.preventDefault();
			let page= $(this).data("page");
			searchForm.find('[name="page"]').val(page);
			searchForm.submit();
			
			return false;
		}).css("cursor","pointer");
		//페이징 처리 
		let searchForm = $("#searchForm")
		
		let searchUI = $("#searchUI").on("click" ,"#searchBtn" , function(){
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx , input){
			let name = this.name ;
			let value = $(this).val()
			searchForm.find("[name='"+name+"']").val(value);
		})//
		searchForm.submit();
	});
		
		
		
			
		let exampleModal = $("#exampleModal").modal({
			show:false
		}).on('show.bs.modal' , function(event){
			//console.log(event.relatedTarget);
			let trTag = event.relatedTarget;
			if(!trTag) return false;
			let mem_id = trTag.id;
			
			 $.ajax({
				url : '<%=request.getContextPath()%>/member/memberView.do' ,
				data : {who :mem_id},
				dataType : "html",//받아올 때 
				success : function(resp) {
				exampleModal.find('.modal-body').html(resp)
				},
				error : function(errorResp) {

				}

			});  
			
		}).on('hidden.bs.modal' , function(){
			exampleModal.find('.modal-body').empty();
		}) //모달
		$("tbody").on("click" , "tr[id]", function(){
			let mem_id = this.id;
			<%--  location.href ='<%=request.getContextPath()%>/member/memberView.do?who='+mem_id
		 --%>
			exampleModal.modal('show' ,this);
			 
		}).css("cursor" , "pointer");

	 	 
		
	})
	
	 
	
	
 
</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</body>
</html>