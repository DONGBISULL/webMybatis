<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>캘린더</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">

#calc{
background-color: #65656A;
color: white;
padding : 2%;
width: 80%;
display: inline-block;
}

.today{
border-collapse: separate;
background-color: #28AFEA;
border: 2px solid white;
}

button {
	background-color:  #1F1F1F;
	color: white;

}


table{

text-align: center;
}
a{

color: white;
text-decoration: none;
font-weight: 800;
}

a:hover{
color: white;
text-decoration: none;
font-weight: 800;
}

table{

width: 90%;

}
td , tr{
padding : 2%;
margin: 3%;

}
span{
padding : 5px;

}
#nowDate{

border-bottom: 2px solid white ;
}
.sortLeft{
text-align: left;

}
</style>
<%
		Calendar cal = Calendar.getInstance();
		
		int today = cal.get(Calendar.DATE); // 오늘에 해당하는 일 
		int nowYear = cal.get(Calendar.YEAR); // 오늘에 해당하는 년
		int nowMonth = cal.get(Calendar.MONTH)+1; //0-11을 --> 1로 맞춰줌 

		int year = nowYear;
		int month = nowMonth;
		
		String paraYear = request.getParameter("year");
		String paraMonth = request.getParameter("month");

		year = request.getParameter("year")==null? nowYear :Integer.parseInt(paraYear);
		month = request.getParameter("month")==null? nowMonth :Integer.parseInt(paraMonth);
		
		//입력값 새로 세팅
		cal.set(year, month-1, 1);//6월을 구하고 싶으면  5를 넣어야 함 ==> 내가 위에서 지금 달 표기할 때 쓰려고 1을 더해놨음
		
		year=cal.get(Calendar.YEAR); //세팅한 값에서 다시 year 받아옴 
		month = cal.get(Calendar.MONTH)+1; //
	   
		int lastDay = cal.getMaximum(Calendar.DATE);
		int yoil = cal.get(Calendar.DAY_OF_WEEK); //세팅한 달의 시작 요일을 구함 // 내가 위에서 세팅을 그 날의 1일로 해둬서 그 날의 요일이 나올 것
			
%>

</head>
<body>


 <div id="calc">
<table>
		<tr ><td id="nowDate" colspan="7"><a href="calendar.jsp"><%=nowYear %>년<%=nowMonth  %>월<%=today %>일</a></td><tr>
	<tr>
		<td class="sortLeft" colspan="5"><button type="button" data-toggle="modal" data-target="#myModal"><%=year %>년 <%=month %>월</button></td>
		<td colspan="2">
		<span><a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">∧</a></span>
		<span><a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">∨</a></span>
		</td></tr> 
 	<tr><td>일</td><td>월</td><td>화</td><td>수</td><td>목</td><td>금</td><td>토</td></tr>
<% 
			int check = 0;
%>
			<tr><%--	1일부터 세팅했던 달의 시작 요일까지 이동 --%>
<%
			for(int i=1 ; i<yoil;i++ ){ 
%>		
			<td></td> 	
<%
			check ++; }
	
	for(int i=1; i<=lastDay;i++){
		if(today==i && month==nowMonth && year==nowYear){
			
		
%>
			<td class="today"><%=i %></td>
<%}else{%>
			<td ><%=i %></td>
		
<%	}	
			check ++;
	if(check==7 && i!=lastDay){
%>
			</tr>
<%		check=0;
	
	 	}
	}
	%>	
 	<tr>	
 	
</table>
 
</div>
<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">날짜 설정</h4>
        </div>
        <div class="modal-body">
          <form action="calendar.jsp">
           <select name="year" id="selYear">
			<%for(int i= nowYear+50 ; i>nowYear-100;i--){
				if(i==year){
			%>
				<option value="<%=i%>" selected="selected"><%=i %>년 </option>	
			<%	}else{
				%>  
			<option value="<%=i%>"><%=i %>년 </option>
			<%} 
			
			}%>         
           </select>
           <select name="month" id="selMonth">
           <%for(int i =1 ; i<13; i++){
        	 	if(i==month){  
        	 %>
           	<option value="<%=i%>" selected="selected"><%=i %>월 </option>
           	<%}else {
           	%>
           	<option value="<%=i%>"> <%=i %>월 </option>
           		
           <%	}
           }	%>
           </select>
           <input type="submit" value="이동">
        </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>