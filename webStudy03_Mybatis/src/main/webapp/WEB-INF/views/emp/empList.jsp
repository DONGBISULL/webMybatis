<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<link rel ="stylesheet" href="<%=request.getContextPath() %>/resources/js/fancytree/skin-win8/ui.fancytree.min.css">
<script src="<%=request.getContextPath() %>/resources/js/fancytree/jquery.fancytree-all-deps.min.js"></script>
<style>
	#formDiv{
	
	display: none;
	}
	
	#tree{
	
	display : inline-block;
	width: 45%;
	
	}

	#info{
	width: 40%;
	float: right;
	}
	 
fieldset

		{
			border: 1px solid #ddd !important;
			margin-right: 10%; 
			 
			padding: 10px;      
			position: relative;
			border-radius:4px;
			background-color:#f5f5f5;
			padding-left:10px! important;
		}

		 

	 
</style>
</head>
<body>
<div id="tree"></div>

<div id="info">
	<form id="infoForm"  method="post" >
	<fieldset>
	  <legend>상세 정보</legend>
	<div class="form-group">
	
	<label>사번</label> : <input type="number" name="empno"  class="form-control" > 
	<label>이름</label> : <input name="ename"  class="form-control" >  
	<label>직급</label> : <input type="text" name="job"  class="form-control" > 
	<label>직속 상사 사번</label> : <input type="number" name="mgr"  class="form-control" > 
	<label>입사일</label> : <input name="hiredate" type="date"  class="form-control" > 
	<label>급여</label> : <input type="number" name="sal"  class="form-control" >  
	<label>상여금</label> : <input type="number" name="comm"  class="form-control" >  
	<label>부서명</label>    
	<select name="deptno"  class="form-control">
	
	</select> 
	</div>
	<button type="button"  class="btn" id="insertInfo" name="insertInfo">추가</button>
	<button type="button" class="btn" id="empty" name="empty">비우기</button>
	<button type="button" id="modifyInfo" class="btn btn-primary" >수정</button>
	<button type="button"  id="deleteInfo" class="btn btn-danger" >삭제</button>
	</fieldset>
	
	</form>

</div>


<!-- <input type="button" value="등록" id="insertEmp"/>
<div id="formDiv">
	<form id="insertForm">
	<label>사번</label> : <input type="number" name="empno"> <br>
	<label>이름</label> : <input name="ename"> <br>
	<label>직급</label> : <input type="text" name="job"> <br>
	<label>입사일</label> : <input name="hiredate" type="date"> <br>
	<label>급여</label> : <input type="number" name="sal"> <br>
	<label>상여금</label> : <input type="number" name="comm"> <br>
	<label>부서명</label>   <br>
	<select name="deptname">
	
	
	</select> 
	<input type="submit" class="submit" value="제출"/>
	</form>
</div>
<div id="empInfo">
	<table> 
		<tbody>
			
		</tbody>
	</table>
</div> -->

<script type="text/javascript">
	 var insertBtn = document.getElementById("insertEmp");
	 var formDiv = document.getElementById("formDiv");
	 let deptname = $('select[name="deptname"]')
	 
	 let submitBtn =document.getElementsByClassName('submit')[0]
	 var emptybtn =document.getElementById('empty') 
	 let deleteInfo = document.getElementById('deleteInfo');
	 let modifyInfo = document.getElementById('modifyInfo');
	 let tbody = $("table>tbody");
	
	 let infoForm = $("#infoForm")
	 let infoEmpNo =$("input[name='empno']") 
	 let infoMgr =$("input[name='mgr']") 
	 let infoEname =  $("input[name='ename']")
	 let infoJob = $("input[name='job']")
	 let infoHiredate = $("input[name='hiredate']")
	 let infoSal = $("input[name='sal']")
	 let infoComm =$("input[name='comm']")
	 let infoDept =$("SELECT[name='deptno']")
 	 
	 let insertInfo = $('#insertInfo')
	 
	 
 
	
	  
 $(function(){
	 
		 $.ajax({
		       	  url :  "<%=request.getContextPath()%>/employee/empInsert.do",
		       	  method :"get" , 
		       	  dataType : "json" ,
		       	  success: function(resp){
		       		 	option=""
		 				 $.each(resp , function(i, v){
		 					option += "<option value='" + v.deptno +"'>" + v.dname +"</option>"
		 				 });
		 				infoDept.html(option)
		       	  }, error : function(errorResp) {
		
						}
			 	})
	 
 })


	$("#tree").fancytree({
		 
		source :{
		
			url : location.pathname, 
			cache : true , // 한번가져온 데이터 자체적으로 cache 하곘다
		} ,
		lazyLoad: function(event, data){
		      var node = data.node;
		     	console.log(node.key)
		      data.result = {
		    	url: location.pathname,
		        data: {mgr : node.key },
		        cache: false
		      };
		  }  ,
	      click: function(event, data) {
	    	  var adaptee = data.node.data.adaptee 
			  let key = data.node.key
			  console.log(key)
 
	            $.ajax({
	        	  url : location.pathname, 
	        	  method :"post" , 
	        	  data : {"key" :key }, 
	        	  dataType : "json" ,
	        	  success: function(resp){
					adaptee =resp.adaptee
					infoEmpNo.val(adaptee.empno)
					infoEname.val(adaptee.ename)
					infoJob.val(adaptee.job)
					infoHiredate.val(adaptee.hiredate)
					infoSal.val(adaptee.sal)
					infoComm.val(adaptee.comm)
					infoMgr.val(adaptee.mgr)
					//infoDept.val(adaptee.deptno)
					//infoDept.prop(adaptee.deptno ,'selected')
					infoDept.find('option[value="' + adaptee.deptno  +'"]').attr("selected",true);

					console.log(adaptee.deptno)
	        		// tbody.empty();
	        		 // td  = makeTdFrom(resp.adaptee);
	        		  //btnArea = $("<tr>").html("<button id='modify' data-key='" +key +"' >수정</button> <button id='delete' data-key='" +key+"'>삭제</button> " )
	        		//  tbody.append(td ,btnArea);
	        	  }, error : function(errorResp) {

					}
	        	  
	          });  
	        }
		  
	});
	
	  
	 $(emptybtn).on("click" , function(){
		  $(":input").val("");
	  }) 
	  
	  $(deleteInfo).on("click" , function(){
		  
		 data =  infoEmpNo.val();
		 $.ajax({
			 url :"<%=request.getContextPath()%>/employee/empChange.do" , 
			 method :  "get",
			 data : {empno : data},
			 dataType :"json",
			 success:function(res){
				 if(res=="OK"){
					 alert("삭제 성공")
					 location.reload(); 
				 }else{
					 alert("삭제 실패")
				 }
				 
			 }
		 })
		  
	  });    
	 
	  $(modifyInfo).on("click" , function(){
		  data ={}
		  let formData = new FormData(infoForm[0]);
		  
		  for(let key of formData.keys()){
		 		let values = formData.getAll(key);
		 		data[key] =  values && values.length>1 ? values : values[0];
		 	};
	  $.ajax({
			 url :"<%=request.getContextPath()%>/employee/empChange.do" , 
			 method :  infoForm[0].method,
			 data:JSON.stringify(data),
			 dataType :"json",
			 success:function(res){
				 if(res=="OK"){
					 alert("수정 성공")
					 location.reload(); 
				 }else{
					 alert("수정 실패")
				 }
				 
			 }
		 })
		  
	  });    
	 
	 
	  
  insertInfo.on("click" , function(){
	  data ={}
		  let formData = new FormData(infoForm[0]);
	  
	  for(let key of formData.keys()){
	 		let values = formData.getAll(key);
	 		data[key] =  values && values.length>1 ? values : values[0];
	 		 console.log(data[key])
	 		
	 		//console.log(values)
	 	};
		      $.ajax({
				
				url :"<%=request.getContextPath()%>/employee/empInsert.do" ,
				method :  infoForm[0].method,
				data : data,
				dataType : "text",
				success : function(resp){
					
				  	 if(resp=="OK"){
				  		 alert("추가에 성공했습니다")
						 location.reload();
					}else{
						alert("추가에 실패했습니다");
					} 
					 
					 
				} 
			 
			});       
	  })
	   
	  
 	
  
</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</body>
</html>