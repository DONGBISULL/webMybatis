package kr.or.ddit.commons;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {
	private boolean authenticated(String id , String password) {
		return id.equals(password);
	}
 	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1 파라미터 확보
		req.setCharacterEncoding("utf-8");
		String memId = req.getParameter("mem_id");
		String memPass = req.getParameter("mem_pass");
		
		//2 검증 --> 둘 다 있어야 함 
		//필수 파라미터 누락 여부 확인(400)
		if(memId== null || memPass==null) {
			resp.sendError(400);
			return;
		}
		
		if(authenticated(memId, memPass)==true) {
			HttpSession session =req.getSession();
			session.setAttribute("authId", memId);
			resp.sendRedirect( req.getContextPath() +"/login/index.jsp");
		}else {
			String path = "/login/loginForm.jsp";
			req.setAttribute("mem_id", memId);
			RequestDispatcher rd =req.getRequestDispatcher(path);
			rd.forward(req, resp);//
		}
		

		
		//3 인증 
		// 1)  성공 : welcome page 이동 
				//redirection
		// 2) 실패 : 다시 로그인 form 페이지로 이동
				//무조건 패스워드가 틀렸다고 가정
				//id값은 value 로 나타남 (forward)
	}
}
