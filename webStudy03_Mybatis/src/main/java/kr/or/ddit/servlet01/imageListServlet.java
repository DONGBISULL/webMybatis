package kr.or.ddit.servlet01;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class imageListServlet extends HttpServlet {
	String contentsPath;
	ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException { // init가 제일 먼저 실행됨
		super.init(config);
		application = getServletContext();
		contentsPath = getServletContext().getInitParameter("contentsPath");
		System.out.printf("%s 서블릿 초기화됨\n", getClass().getName());
		
	
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse  resp) throws ServletException, IOException {
		application =getServletContext();
		resp.setContentType("text/html;charset=UTF-8");
		File ContentFoldor = new File(contentsPath);
		
		String[] imageList = ContentFoldor.list(new FilenameFilter() {
			
			@Override
			public boolean accept(java.io.File dir, String name) {
				 String mimeText = application.getMimeType(name);
				return mimeText!=null && mimeText.startsWith("image/");
			}
		}); 
		
		
		String pattern = "<option>%s</option>";
		StringBuffer options = new StringBuffer();
		for(String name :imageList) {
			options.append(String.format(pattern,name));
		}
		///////////
		//this 생략 이미지리스트 서블릿 숨어있음 
							//getClass() 현재 참조하고 있는 클래스를 알 수 있다 
		InputStream is = getClass().getResourceAsStream("imageList.tmpl");//바이트 스트림
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		String tmp = null ;
		StringBuffer tmplSource = new StringBuffer();
		while((tmp = reader.readLine())!=null) { 
			tmplSource.append(tmp+"\n");
		}
		
		String html = tmplSource.toString().replace("#{data}", options );
		resp.getWriter().println(html);
		
		
	}

}
