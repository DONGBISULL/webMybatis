package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;

@WebServlet("/05/messageServiceWithLocale")
public class MessageServiceServletWithLocale extends HttpServlet{
 
	@Override
	public void init(ServletConfig config) throws ServletException {
		String basename = config.getInitParameter("basename");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			 req.setCharacterEncoding("utf-8");
			 Locale locale = null;
			 locale = req.getLocale();
			 String lang = req.getParameter("lang");
			 
			 if(lang!=null &&!lang.isEmpty()) {
				 locale = Locale.forLanguageTag(lang); 
			 }
			 
		 	ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.servlet05.message" ,locale);//확장자 이름 안씀		
			Map<String ,Object >dataMap = new HashMap<>();
			
			for(String key :bundle.keySet()) {
				dataMap.put(key,bundle.getObject(key));
			}
			
			
			//Qualified 네임이랑 맞추려고 .으로 씀  "/"으로 써도 됨 
			String accept = req.getHeader("Accept");
			
			//**마임 설정 Content-Type
			MimeType mime = MimeType.findMimeType(accept);
			
			if(!MimeType.JSON.equals(mime)) {
				resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
				return;
			}

			//1.마샬링 marshalling 
			ObjectMapper mapper = new ObjectMapper();
			//String json = mapper.writeValueAsString(dataMap);
			
			//2. 직렬화 
			 
			 resp.setContentType(mime.getMimeText());
			 try(PrintWriter out = resp.getWriter()){
				 mapper.writeValue(out, dataMap);
			 }
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		
		
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.servlet05.message" ,Locale.ENGLISH);//확장자 이름 안씀		
		Map<String ,Object >dataMap = new HashMap<>();
		
		for(String key :bundle.keySet()) {
			dataMap.put(key,bundle.getObject(key));
		}
		
		
		//Qualified 네임이랑 맞추려고 .으로 씀  "/"으로 써도 됨 
		String accept = req.getHeader("Accept");
		
		//**마임 설정 Content-Type
		MimeType mime = MimeType.findMimeType(accept);
		
		if(!MimeType.JSON.equals(mime)) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}

		//1.마샬링 marshalling 
		ObjectMapper mapper = new ObjectMapper();
		//String json = mapper.writeValueAsString(dataMap);
		
		//2. 직렬화 
		 
		 resp.setContentType(mime.getMimeText());
		 try(PrintWriter out = resp.getWriter()){
			 //out.print(json);
			 mapper.writeValue(out, dataMap);
		 }
		 
	}
}
