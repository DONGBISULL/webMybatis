<%@page import="java.io.IOException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.net.URL"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/applicaionDesc.jsp</title>
</head>
<body>
<h4>ServletContext application</h4>
<pre>
	sevlet container(WAS)와 해당 컨테이너 내에서 운영되는 
	어플리케이션(context) 에 대한 정보를 가진 객체 
	1. 컨텍스트 초기화 파라미터 확보 
		- 파라미터를 받는 대상 -> application /servlet/....
		<%=application.getInitParameter("contentsPath") %>
	2. 로그 기록 
		<%application.log("명시적으로 기록할 로그 데이터"); %>
	3. 서버나 컨텍스트에대한 정보 확인 
		<%=application.getServerInfo() %> ,
		<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	
	4. 웹 리소스 확보(*****)
		/webStudy01/WebContent/resources/images/jj.jpg 입력 스트림 
		/08/jj.jpg //출력스트림
		
		
 <%-- <%
		String path = "/resources/images/jj.jpg";
		/* String realPath = application.getRealPath(path);
		//out.println(realPath);
		File readFile = new File(realPath) */;
		
		String myPath ="/08";
		String dwPath =  application.getRealPath(myPath);
		dwPath=dwPath+"/jj.jpg";
		out.println(dwPath);
 		 try(
		FileInputStream fis = new FileInputStream(readFile);
		FileOutputStream fos = new FileOutputStream(dwPath);
				){
			byte[] buffer = new byte[1024];
			int pointer = -1;
		
			while((pointer=fis.read(buffer))!=-1){
				 fos.write(buffer, 0, pointer);  
				//System.out.write(buffer, 0, pointer);
			}
		}
  --%>
		 
		
		
		 <%
		
		String imageURL = "/resources/images/jj.jpg";
		 /* String realPath = application.getRealPath(path);
			File readFile = new File(realPath)
			FileInputStream fis = new FileInputStream(readFile);
		*/;
		
		String destURLStr = "/08/jj.jpg";
		
		URL destURL =  application.getResource(destURLStr);//application
		Path target = null;
		if(destURL==null){
			//파일이 존재하지 않는다면
			String destRP = application.getRealPath(destURLStr);
			target=	Paths.get(destRP);		
		}else{
			//파일이 존재한다면 
		target = Paths.get(destURL.toURI());
		}
		
		try{
			InputStream is = application.getResourceAsStream(imageURL);
			Files.copy(is,target,StandardCopyOption.REPLACE_EXISTING); //nio --> 양방향 채널 fileChannel
			if(1==1) throw new SQLException("강제 발생 예외");
		}catch(SQLException e){ //모든 에러와 exception 을 다 잡을 수 있다
			System.err.println(e.getMessage());
			throw new IOException(e);
		}
		%> 
		 
		
		<img src="<%=application.getContextPath()%>/08/jj.jpg"/>
	File 스트림 --> 절대 경로 필요함 ==> 서버를 통해 절대 경로를 받아야 함 
	application을 통해 수행 


</pre>

</body>
</html>