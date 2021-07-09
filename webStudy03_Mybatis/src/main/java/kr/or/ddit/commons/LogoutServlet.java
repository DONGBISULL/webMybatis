package kr.or.ddit.commons;
 
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login/logout.do")
public class LogoutServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession(false);
		 if(session==null || session.isNew()) {
			 //timeout이 지난 후 로그아웃 클릭 
			 resp.sendError(HttpServletResponse.SC_BAD_REQUEST ,"로그아웃이 최초의 요청????");
			 return;
		 }
		 
	 	 session.invalidate();//세션을 삭제 시킴
		 String message = URLEncoder.encode("로그아웃 성공" , "utf-8");
//		 session.setAttribute("로그인 ", value);
		 
		 resp.sendRedirect(req.getContextPath() + "/?message=" + message);
		 //forward
		 
	}
}
