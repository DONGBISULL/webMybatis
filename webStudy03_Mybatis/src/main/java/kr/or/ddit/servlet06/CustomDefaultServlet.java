package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.Application;


//@WebServlet("/resources/")//
public class CustomDefaultServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String uri = req.getRequestURI();
			String cPath = req.getContextPath();
			uri = uri.substring(cPath.length());
//			InputStream is = application.getResourceAsStream(uri);
			URL resURL =  application.getResource(uri);
			if(resURL== null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, uri);
				return;
			}
			//nio 채널이라는 개념 사용 ==> 하나를 가지고 읽고 쓰기가 가능해짐 
			//이미 던져진 예외와 다른 예외라서 throws가 불가능
			try {
				Path resPath = Paths.get(resURL.toURI());
				//Path resPath  = Paths.get("asdasfdsf");==> 개발자의 오류
				OutputStream os = resp.getOutputStream();
				Files.copy(resPath, os);

				resp.setHeader("Cache-Control", "no-cache");	
				resp.addHeader("Cache-Control", "no-store"); //하나가 덮어씌워지니까 cache 지원하는 곳에서 문제 생김 	
				resp.setHeader("Pragma", "no-cache");	
				resp.addHeader("Pragma", "no-store"); //하나가 덮어씌워지니까 cache 지원하는 곳에서 문제 생김 	
				resp.setDateHeader("Expires", 0);
				
				os.close(); //outputstream 이 닫히면 메타데이터 관리 불가
							//redirect 한 다음에 헤더 변경 불가능
			} catch (URISyntaxException e) {
				throw new IOException(e);//throw 강제로 오류를 만들겠다
				//위에서 받은 에러 e를 넘겨준다	
			}
					
			
			
	}
	
}
