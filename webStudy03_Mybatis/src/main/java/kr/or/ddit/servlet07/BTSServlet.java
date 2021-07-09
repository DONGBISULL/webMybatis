package kr.or.ddit.servlet07;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumtype.BtsType;

@WebServlet("/bts") //하나의 서블릿으로 다 처리하라....불가능해요... enum도 사용해보래요 ...
public class BTSServlet extends HttpServlet {
	private ServletContext appliaction ;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		appliaction = getServletContext();
		Map<String ,String[]> btsMap = new HashMap<>();
		btsMap.put("B001",new String[] { "RM" , "rm.jsp"});
		btsMap.put("B002",new String[] { "지민" , "jimin.jsp"});
		btsMap.put("B003",new String[] { "뷔" , "bui.jsp"});
		btsMap.put("B004",new String[] { "진" , "jin.jsp"});
		btsMap.put("B005",new String[] { "정국" , "jungkuk.jsp"});
		btsMap.put("B006",new String[] { "슈가" , "suga.jsp"});
		btsMap.put("B007",new String[] { "제이홉" , "jhop.jsp"});
		appliaction.setAttribute("btsMap", btsMap);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member = req.getParameter("btsMember");
		int status = HttpServletResponse.SC_OK;
		String errorMsg= null;
		String goPage = null;
		
		if(member==null || member.isEmpty()) {
	//		resp.sendError(400,"필수파라미터 누락");
			status = 400;
			errorMsg = "필수파라미터 누락";
			return;
		}
		
		Map<String ,String[]>btsMap = (Map)appliaction.getAttribute("btsMap" );
		if(!btsMap.containsKey(member)) {
//			resp.sendError(404);
			status = 404;
			errorMsg = String.format("방탄에는  %s 멤버가 없음" ,member);
		}else {
			Date searchTime = new Date();
			req.setAttribute("searchTime", searchTime);
			String[] info= btsMap.get(member);
			goPage = info[1];
		
		}
			
		if(status==HttpServletResponse.SC_OK) {
			goPage = "/WEB-INF/views/bts/" +goPage;
			RequestDispatcher rd = req.getRequestDispatcher(goPage);
			rd.forward(req, resp); 
		}else {
			resp.sendError(status, errorMsg);
		}
		
		//String btsname = BtsType.BtsName(member);
		///webStudy01/WebContent/WEB-INF/views/08/btsForm.jsp
		
		//nestforuri --> 뭔가를 하려는 건 메소드를 통해 구현
		 
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	///	/WEB-INF/views/08/btsForm.jsp
		
		String dest = "/WEB-INF/views/08/btsForm.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(dest);
		rd.forward(req, resp);
	
	}
}
