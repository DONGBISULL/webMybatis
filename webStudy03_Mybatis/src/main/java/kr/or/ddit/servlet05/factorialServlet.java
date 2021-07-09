package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;

@WebServlet("/05/factorial")
public class factorialServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf-8");
		 resp.setCharacterEncoding("utf-8");
		 int left = 0; 
		 String param =  req.getParameter("left");
		 if(param!= null &&!param.isEmpty()) {
		 left = Integer.parseInt(param);
		 }
		 int expression = 0 ;
		
		 if(left==1) {
			 expression = 1;
		 }else {
			 while(left>0) {
				 expression += left *(left-1);
				 left-=1;
			 }
			 
		 }
		 String accept = req.getHeader("Accept");
		 MimeType mime = MimeType.findMimeType(accept);
		 resp.setContentType(mime.getMimeText());
		 Map<String , Object>dataMap = new HashMap<>();
		 String data = "";
		 
		 if(MimeType.PLAIN.equals(mime)) {
			 data += param+"!=" +expression;
				//json javascript object notation
				try(PrintWriter out = resp.getWriter();
						){
					
					out.println(data);
				}
		 
		 }else if(MimeType.XML.equals(mime)) {
			 StringBuffer xml = new StringBuffer(1024);
			 xml.append("<result>");
			 xml.append("<left>");
			 xml.append(param);
			 xml.append("</left>");
			 xml.append("<expression>");
			 xml.append(param+"!=" +expression);
			 xml.append("</expression>");
			 xml.append("</result>");
			 
			 try(PrintWriter out = resp.getWriter();
						){
					
					out.println(xml);
				}
			 
			 
		 }else if(MimeType.JSON.equals(mime)) {
			 dataMap.put("left" ,param);
			 dataMap.put("operater" ,"!");
			 dataMap.put("expression" ,param+"!=" +expression);
			 ObjectMapper mapper = new ObjectMapper();
			 
			
			 try(PrintWriter out = resp.getWriter()){
				 mapper.writeValue(out, dataMap);
			 
			 }//out
			 
			 
		 }else {
			 resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			 return;
		 }
		 
			 
		 
		
	}
	
	
}
