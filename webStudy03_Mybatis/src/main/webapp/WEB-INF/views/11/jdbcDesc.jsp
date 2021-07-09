<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<jsp:include page="/includee/preScript.jsp"></jsp:include>
 
<h4>JDBC(Java Deta Base Connectivity)</h4>

<h5>단일 책임의 원칙</h5>
<pre> 
	: 데이터 베이스 프로그래밍 단계
	1. 벤더가 제공하는 드라이버를 찾고 빌드 패스에 추가 
	2. 드라이버 클래스 로딩 
	3. Connection 생성
	4. 쿼리 객체 생성 
		- PreparedStatement ==> 쿼리가 미리 컴파일이 되어있다(변경할 수 없음)
		- Statement
		- CallableStatement 
	5. 쿼리 실행 :DML
		- ResultSert excuteQuery() : SELECT(조회)
		- int executeUpdate() : INSERT ,UPDATE, DELETE
			--> 몇개의 데이터에 영향이 있었는지
		--> select key가 없으면 insert 메서드 안쓰는것 
	6. 질의 결과 사용
	7. 자원의 해제 
	<%
//데이터 베이스로부터 raw 데이터 조회후 
//조회 날짜 붙여서 추가해달라
	//DataBasePropertyDAO dao = new DataBasePropertyDaoImpl();
	/* 	DataBasePropertyService service = new DataBasePropertyServiceImpl();
	List<DataBasePropertyVO> propList = service.retrieveDataBaseProperties();
	 */
	//System.out.println(propList);
//
//비동기로 검색 
%>
	

		

</pre>

<table>
	<thead>
		<tr>
		<th>PROPERTY_NAME</th>
		<th>PROPERTY_VALUE</th>
		<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
 	<%-- <% 	 List<DataBasePropertyVO> propList =(List<DataBasePropertyVO>)request.getAttribute("propList");
 	 
		  for(int i = 0 ; i< propList.size();i++){
			DataBasePropertyVO vo = propList.get(i); 
	%>
	 <tr>
		<td><%=vo.getProperty_name() %></td>
		<td><%=vo.getProperty_value()==null?"":vo.getProperty_value() %></td>
		<td><%=vo.getDescription()==null?"" :vo.getDescription() %></td>
	</tr>
	<%
		}
	
	%>  --%>
 
	</tbody>
	<tfoot>
	<tr>
	<td colspan="3">
		<div id="searchUI">
		<input type="text" name="search"/>
		<input type="button" value="검색" id="searchBtn" />
		</div>
	</tr>
	</tfoot>
</table>

<form id="searchForm" >
	<input type="text" name="search"/>
<!-- 	<input type="text" name="search2"/>
	<input type="text" name="search3"/> -->
</form>

<script type="text/javascript">
	
	let search = $("[name='search']");
	let tbody = $("table>tbody");
	let searchUI = $("#searchUI").on("click" ,"#searchBtn" , function(){
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx , input){
			let name = this.name ;
			let value = $(this).val()
			searchForm.find("[name='"+name+"']").val(value);
		})//
		searchForm.submit();
	});
	
let searchForm = $("#searchForm").on("submit" , function(event){
		event.preventDefault;
		 
		let url = this.action;
		let method  = this.method;
		let formData = new FormData(this);
		 
		let data = $(this).serialize();
		
		$.ajax({
				url : url,
				method :method ,
			 	dataType : "json",
			 	data : data ,
				success : function(resp) {
					tbody.empty();
					let trs = [];
					$.each(resp, function(i,v){
						trs.push($("<tr>").append(makeTdFromData(v)));
					 
					})
					tbody.append(trs);
				
				},
				error : function(errorResp) {

				}

			}); 
		
		return false;
		
		
	}).submit();
 
	
	
	
	<%-- 
	$("input[type=submit]").on("click" , function(){
		
		 let input = search.val();
			
		 	 $.ajax({
		 		url : "<%=request.getContextPath()%>/11/searchServlet.do",
		 		data :  {"input" : input},
		 		dataType :"json",
		 		method :"post",
		 		success: function(resp){
		 			tbody.empty();
		 			let trs = [];
		 			
		 			$.each(resp,function(i,v){
		 			trs.push($("<tr>").append(makeTdFromData(v)));
		 			})
		 			tbody.append(trs);
		 		}
		 		
		 	}) 
	})

		 --%>

function makeTdFromData(propVO){
	let tds = [];
	for(let propName in propVO){
		let td = $("<td>").html(propVO[propName]);
		tds.push(td);
	}
	return tds
 
}
	 
	 
</script>
