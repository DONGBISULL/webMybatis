package kr.or.ddit.servlet06;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class sessionExtend
 */
@WebServlet("/sessionExtend")
public class sessionExtend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
		protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Session session = session.set
		HttpSession session =	req.getSession(); //요청만 발생하면 연장 
		
		System.out.println(session.getLastAccessedTime()); 
		System.out.println(session.getMaxInactiveInterval());
		System.out.println(session.getLastAccessedTime() + session.getMaxInactiveInterval());
		//session.setMaxInactiveInterval(2);
		}
	

}
