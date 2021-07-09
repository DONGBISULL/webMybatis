<%@page import="kr.or.ddit.enumtype.OperatorTypeT"%>
<%@page import="kr.or.ddit.enumtype.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="<%=request.getContextPath() %>/calculateT.do" name="calForm" >
	<input type="radio" name="mime" value="plain" data-type="text" data-success="parsePlain" checked> PLAIN
	<input type="radio" name="mime" value="json" data-type="json" data-success="parseJson"> JSON
	<input class="form-control"  type="number" name="left" />
	<!-- 연산자 UI : operator -->
<select name="operator">
<%
 	OperatorTypeT[] operators =  OperatorTypeT.values();
		for(OperatorTypeT tmp : operators){
			
%>
		<option value="<%=tmp.name()%>"><%=tmp.getSign() %></option>
<%
		}
%>
</select>
	<input class="form-control" type="number" name="right" min="1" />
	<input type="submit" value="=">
	<span id="resultArea"></span>
</form>

<!-- 
하드 코딩 안하고 동적으로 구현 -->
 
 <script>
 	
	let resultArea = $("#resultArea")
 	let functions = {
 			parsePlain : function(resp){
				resultArea.text(resp); 				
 			},
 			parseJson : function(resp){
 				resultArea.text(resp.expression);
 			}
 			
 	}
 
	 $("[name='calForm']") .on("submit", function(event){
		 event.preventDefault();
			let url = this.action ;
			let data = {}
			let formData = new FormData(this);
			
			console.log(formData);
			console.log(formData.keys()); //Iterator 형태 향상된 for문 사용 가능
			
			for(let key of formData.keys()){
				//console.log(key);
				//console.log(formData.getAll(key));
				let values = formData.getAll(key);
				data[key] =  values && values.length>1 ? values : values[0];
			}
			
			//console.log(data);
  			let method = this.method;
			let dataType = $(this).find("[name='mime']:checked").data("type")
			let success = $(this).find("[name='mime']:checked").data("success")//함수 이름 가짐 
			
		 	
			let options = {}
			options.url = url;
			options.method= "post"
			options.dataType =dataType; //응답 데이터 타입
			/* options.data = data; */
			
			//데이터 보내는 부분 실수 조심
		   	options.data =JSON.stringify(data)//json  // 마샬링을 해줌  parse --> json을 자바스크립트로 
			options.contentType = "application/json;chaset=utf-8";//바디가 존재
			options.success = functions[success]
			
			$.ajax(options); 
		  
		 return false ;
	 })
	
	 
	
 
 
 
 </script>