package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.UserNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
@WebServlet("/mypage.do")
public class MyPageControllerPage extends HttpServlet{

	MemberService service =  MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
			MemberVO authMember = 	(MemberVO)	session.getAttribute("authMember");
		 
				String dept = "/WEB-INF/views/member/memberView.jsp";
				MemberVO vo = service.retrieveMember(authMember.getMemId());
				req.setAttribute("member", vo);
				
				req.getRequestDispatcher(dept).forward(req, resp);;
			
		 
	
	}
}
