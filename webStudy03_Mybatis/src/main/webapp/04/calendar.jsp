<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//코드는 수정 상황이 발생하기 마련 ==> 최소한의 비용으로 수정할 수 있어야 함 
	
	request.setCharacterEncoding("UTF-8");
	
	String yearStr = request.getParameter("year");
	String monthStr = request.getParameter("month");
	String langTag =  request.getParameter("language");
	String timezone = request.getParameter("timeZone");
	
	 Locale locale = request.getLocale();
	

	if(langTag!=null && !langTag.isEmpty()){
		locale = Locale.forLanguageTag(langTag);
	} 
	
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
	
	Calendar nowcal = getInstance();

	//해당 타임 존에 맞는 시간 나타나도록 세계 시간 
	//Calendar.getInstance(zone, aLocale)//타임 존 사용 
	 
	if(timezone!=null && !timezone.isEmpty()){
		nowcal.setTimeZone(TimeZone.getTimeZone(timezone));
	}else{
		timezone = TimeZone.getDefault().getID();
	}
	
	int nowyear = nowcal.get(YEAR);
	int nowmonth = nowcal.get(MONTH);
	int nowdate = nowcal.get(DATE);
	
	
	Calendar cal = getInstance(); //static import때문에 Calendar  ㅇ안써도 됨
	
	
	if(yearStr!= null && yearStr.matches("\\d{4}")){
		cal.set(YEAR, Integer.parseInt(yearStr));
	}
	
	if(monthStr!=null && monthStr.matches("\\d{1,2}")){
		cal.set(MONTH , Integer.parseInt(monthStr));
	}
	
	int year = cal.get(YEAR);
	int month = cal.get(MONTH);
	
	cal.set(DAY_OF_MONTH, 1);
	int offset = cal.get(DAY_OF_WEEK) -1 ; //일요일이 0 
	int lastDate = cal.getActualMaximum(DAY_OF_MONTH);
	
	cal.add(MONTH,-1);
	int beforeYear= cal.get(YEAR);
	int beforeMonth= cal.get(MONTH);
	
	cal.add(MONTH,2);
	int nextYear =cal.get(YEAR);
	int nextMonth=cal.get(MONTH);
	cal.add(MONTH, -1);
	
%>

<title>04/calendar.jsp</title>
<style>
/* td , tr{

padding : 2%;
margin: 3%;

} */
div{
display: inline-block;
text-align: center;
}

table{

text-align: center;
margin-left: 15%;
margin-right: 15%;
}

.sunday {
	color :red;
}

.saturday{

color :blue;
}

.current{
	border-collapse: separate;
	background-color : green;
	border: 2px solid white;
	color: white;
}

a{

color: black;
text-decoration: none;
/* font-weight: 800; */
}

a:hover{
color: black;
text-decoration: none;
/* font-weight: 800; */
}

select[name='language']{
	width: 200px;
} 
</style>
 
<div>

<h4>현재 서버 시각 : <%=String.format(locale, "%tc",nowcal ) %></h4> 

<h4>
<a href="#" class="moveA" data-year="<%=beforeYear %>" data-month="<%=beforeMonth %>" >이전달 </a>
<%=String.format(locale,"%1$tY , %1$tB" , nowcal) %><!-- //여기서 locale 선택하면 선택값 유지 가능 -->
<a href="#" class="moveA" data-year="<%=nextYear%>" data-month="<%=nextMonth %>" >다음달</a>
<%-- format에서 %t는 시간 데이터를 의미한다 --%>
</h4>
<form id="calendarForm">
	<input type="hidden" name="service" value="CALENDAR"/>
	<input type="number" name="year" placeholder="<%=year%>" value="<%=year%>">
	<select name="month">
		<option value>월 선택</option>
<%
	String optionPtrn = "<option %s value='%s'>%s</option>";
		String[] months = dfs.getMonths();
		for(int tmp = 0;tmp<12 ; tmp++){
			String selected = (tmp)==month?"selected" :"";
			out.println(
					String.format(optionPtrn, selected, tmp, months[tmp])
					);
			
		}
%>
	</select>
	
	<select name="language">
		<%
		Locale[] locales = Locale.getAvailableLocales();
		for(Locale tmpLoc : locales){
			String tag = tmpLoc.toLanguageTag();
			String name = tmpLoc.getDisplayName();
			String selected = tmpLoc.equals(locale) ?"selected":"";
			if(name.isEmpty()) continue;
			out.println( 
					String.format(optionPtrn , selected ,tag,name)
					);
			
		}
		%>
	
	</select>
	
	<select name="timeZone">
		
		<%
		String[] timezones = TimeZone.getAvailableIDs();
	
		for(int i = 0 ; i<timezones.length;i++){
				
				String selected = timezones[i].equals(timezone) ?"selected" :"";
					out.println(
							String.format(optionPtrn,selected ,timezones[i],timezones[i])
							);
				}
		%>
	
	</select>
	
	
</form>

<%-- <%= Calendar.SUNDAY%>
<%= Calendar.MONDAY%> --%>
<table class="table table-bordered">
	<thead>
		<tr>
			<%  String[] weekDays = dfs.getWeekdays();
				String thPtrn = "<th>%s</th>";
				for(int idx=SUNDAY ; idx<=SATURDAY;idx++){
					out.println(
								String.format(thPtrn , weekDays[idx])
							);
					
				}
			
			%>
		</tr>
	</thead>
	<tbody>
		<%	String pattern = "<td class='%s'>%s</td>";
			int number = 1;
			 
			for(int row=1 ; row<=6 ; row++){
					out.println("<tr>");
				for(int col = SUNDAY; col<=SATURDAY ;col++){
					int dateNumber = number++ -offset;
					String printNumber =
							dateNumber >=1 && dateNumber <=lastDate
							? dateNumber+"" :"&nbsp;";
							
					String yoil = (col==SUNDAY) ?"sunday" :"";
							yoil += (col==SATURDAY) ? "saturday" :"";
							yoil += dateNumber==nowdate && nowmonth==month && nowyear==year ?"current" :"";
					out.println(String.format(pattern,yoil ,printNumber));
				}
					out.println("</tr>");
			}
		
		%>
		
	
	</tbody>
</table>


</div>



<script type="text/javascript"> // 스트립트 언어의 특성을 반영해서 body 끝나기 바로 전에 스크립트 입력
	let calForm = $("#calendarForm").on("change" , ":input" ,function(){ //:input ==> 모든 입력 태그를 포함 
		console.log(this.form)
		console.log(calForm[0])
		this.form.submit(); //html 엘리먼트의 submit
		//calForm.submit(); //제이쿼리 엘리먼트를 사용했을 때  
	}).on("submit",function(){
		//console.log("===================")
		return true;	
	});

	$(".moveA").on("click" , function(event){ //발생한 이벤트 객체화 시킬 것 
		event.preventDefault();
		let year=$(this).data("year");
		let month=$(this).data("month");
		calForm.find("input[name='year']").val(year);
		calForm.find(":input[name='month']").val(month);
		/* calForm.get(0).month  */		
		calForm.submit();
		return false;
		
	});


</script>
 