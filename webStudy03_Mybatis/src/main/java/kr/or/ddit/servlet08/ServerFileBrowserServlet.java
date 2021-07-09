package kr.or.ddit.servlet08;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FancyTreeNodeAdapter;

@WebServlet("/fileBrowser.do")
public class ServerFileBrowserServlet extends HttpServlet{
	ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String accept = req.getHeader("Accept");
		if(accept.indexOf("json") >0) {
			processJsonRequest(req,resp);
		}else {
			String dest = "/WEB-INF/views/serverBrowser.jsp";
			req.getRequestDispatcher(dest).forward(req, resp);
		}
		
		
	
	
	}

	private void processJsonRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String base = req.getParameter("base");//데이타 property 명으로 결정 
		if(base==null || base.isEmpty()) {
			base= "/";
		}
		
		Set<String> children = application.getResourcePaths(base);
		if(children==null) {
			resp.sendError(404);
			return;
		}
		List<File> fileList = new ArrayList<>();
		
		for(String child : children) {
			System.out.println(child);
			String realPath = application.getRealPath(child); ///05/ d드라이브 부터 시작되는 실제 경로
			File file = new File(realPath);
			FancyTreeNodeAdapter wrapper = new FancyTreeNodeAdapter(file, child);
			fileList.add(wrapper);
		}
		
	
		
		//req.setAttribute("fileList", fileList);
		
		Collections.sort(fileList);
		
		resp.setContentType("application/json;charset=UTF-8");
		//마샬링 필요 
		ObjectMapper mapper = new ObjectMapper();
		try(
				PrintWriter out = resp.getWriter();
				){
			
			mapper.writeValue(out,  fileList);
		}
		
		
	}
}
