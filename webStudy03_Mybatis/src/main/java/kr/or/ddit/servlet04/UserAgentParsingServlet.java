package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.enumtype.MimeType;
import kr.or.ddit.enumtype.OsType;

@WebServlet("/04/getBroserName")
public class UserAgentParsingServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accept = req.getHeader("Accept");
		String userAgent = req.getHeader("user-agent").toUpperCase();
		
		String browser = BrowserType.parseUserAgent(userAgent); 
		String  os = OsType.findeOsName(userAgent);
		
		Map<String , Object> target =new HashMap<>();
		target.put("browser" ,browser);
		target.put("os" , os);
		/*
		StringBuffer json = new StringBuffer();
		String PROPPTRN = "\"%s\" : \"%s\" , ";
		*/
		//Mashalling ==> native의 언어로 표현된 데이터를 공통 표현 방식(xml , json)으로 바꾸는 과정
		//Unmashalling ==> 공통의 표현 방식으로 표현된 데이터를 native한 방식으로 바꾸는 과정
		/*
		json.append("{");
		for(java.util.Map.Entry<String ,Object>entry :target.entrySet()) {
			json.append(String.format(PROPPTRN, entry.getKey() ,
						Objects.toString(entry.getValue(), "")));
		}
		json.append("}");
		*/
		
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(target);
		
		
		/*int lastIdx = json.lastIndexOf(",");
		if(lastIdx >=0) {
			json.deleteCharAt(lastIdx);
		}*/
		MimeType mime= MimeType.findMimeType(accept);
		
		Object data =null;
		
		if(MimeType.JSON.equals(mime)) {
			data = json;
		}else {
			data = browser;
			data += "," + os;
		}
		
		resp.setContentType(mime.getMimeText());
		//json javascript object notation
		try(PrintWriter out = resp.getWriter();
				){
			
			out.println(data);
		}
		
	}
	
}
