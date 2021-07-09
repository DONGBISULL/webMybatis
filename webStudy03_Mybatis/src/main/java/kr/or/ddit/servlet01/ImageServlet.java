package kr.or.ddit.servlet01;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

public class ImageServlet extends HttpServlet {
    ServletContext application;  
    File contentsFolder;
    
	@Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	application = getServletContext();
    	String contentsPath = application.getInitParameter("contentsPath");
    	contentsFolder = new File(contentsPath);
       
       }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String imageName = request.getParameter("image");
		if(imageName==null || imageName.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		
		String mime = "image/jpeg";
		response.setContentType(mime);
		//String FileContext = "D:/contents/" + FileName;
		File srcFile = new File(contentsFolder,imageName);
		
		FileInputStream fis = new FileInputStream(srcFile) ;
		
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1 ;
		while((pointer=fis.read(buffer))!=-1) {
			os.write(buffer, 0, pointer);
		}
		
		fis.close();
		os.close();
	}

}
