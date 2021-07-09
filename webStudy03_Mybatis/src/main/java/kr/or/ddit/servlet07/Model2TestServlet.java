package kr.or.ddit.servlet07;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/innerAccess.do")
public class Model2TestServlet extends HttpServlet {
	 //String contents 전역 변수 안써요 
	@Override
	
	//서블릿에서 요청을 받음 
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contents = "김치찌개";
		req.setAttribute("contents", contents);
		HttpSession session =  req.getSession();
		session.setAttribute("contents", contents);
		//req 안에는 Map이 하나 들어있다고 보면 됨
		//req가 살아있는 동안에는 map에 들어있는 데이터 사용 가능
		
		//요청 분석
		//web-inf 보안을 위해 접근 불가함 
//		String dest = "/WEB-INF/views/inner.jsp";//서버 사이드 안에서 이동 ContextPath 필요없음
		//server에서 redirect로 서버 안에서만 이동은 가능 
		/*RequestDispatcher rd =  req.getRequestDispatcher(dest);
		rd.forward(req, resp);*/
		
		//WEB-INF는 inner.jsp
//		String dest = req.getContextPath() + "/WEB-INF/views/inner.jsp";//서버 사이드 안에서 이동 ContextPath 필요없음
		String dest = req.getContextPath() + "/07/desination.jsp";//서버 사이드 안에서 이동 ContextPath 필요없음
		resp.sendRedirect(dest);//header로 나갈 때 Location 을 가지고 나감//클라이언트가 사용하는 주소
	
	}
}
