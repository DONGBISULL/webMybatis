<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/parameterProcess.jsp</title>
</head>
<body>
<h4>요청 파라미터 처리</h4>
<form action="<%=request.getContextPath() %>/03/parameterProcess" method="get">
<pre>
일반 텍스트 : <input type="text" name="param1"/>
숫자 : <input type="number" name="param2"/>
라디오  버튼 :<input type="radio" value="A" name="param3"/>A
 		 <input type="radio" value="B" name="param3"/>B
체크 박스(다중 선택 지원) : 
<input type="checkbox" value="C1" name="param4"/>C1
<input type="checkbox" value="C2"  name="param4"/>C2
<input type="checkbox" value="C3"  name="param4"/>C3
	
셀렉트(단일)
<select name="param5">
	<option value>prompt text</option>
	<option value="O1">Option1</option>
	<option value="O2">Option2</option>
</select>
<br>
<select name="param6" multiple size ="3">
	<option>OPTIONA</option>
	<option>OPTIONB</option>
	<option>OPTIONC</option>
</select>
	
<textarea rows="5" cols="100" name="param7"></textarea>
<button type="button">걍 버튼 </button>
<input type="submit" value="전송">
<button type="reset">취소</button>	
</pre>
	
</form>


</body>
</html>