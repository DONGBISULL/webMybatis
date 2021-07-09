package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 로그인 되어있는 사용자에 대한 탈퇴 처리
 *
 */
@WebServlet("/member/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	private MemberService service = MemberServiceImpl.getInstance();
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memPass = req.getParameter("memPass");
		String message = null;
		String viewName = null;
		if(StringUtils.isBlank(memPass)) {
			resp.sendError(400 ,"필수 파라미터 누락");
			return ;
		}
		HttpSession session = req.getSession();
		MemberVO authMember  =(MemberVO) session.getAttribute("authMember") ;
		
		MemberVO member = MemberVO.builder()
							.memId(authMember.getMemId())
							.memPass(memPass)
							.build();
		
		ServiceResult result   = service.removeMember(member);
		
		
			switch (result) {
			case OK:
				//welcome page : redirect
				//로그아웃 상태로 welcome로 되어있도록 
				System.out.println("컨트롤러");
				session.invalidate();
				viewName = "redirect:/index.do";
				break;
			case INVALIDPASSWORD :
				//mypage redirect
				viewName = "redirect:/mypage.do";
				message = "비밀번호 오류";
				break;
			default:
				//mypage , redirect
				viewName ="redirect:/mypage.do";
				message ="서버 오류 "; //plash
				break;
			}
			if(message!=null) {
				req.getSession().setAttribute("message", message);
			}
			
			
			if(viewName.startsWith("redirect:")) {
				viewName = viewName.substring("redirect:".length());
				resp.sendRedirect(req.getContextPath() +viewName );
			}else {
				String prefix="/WEB-INF/views/";
				String surfix =".jsp";
				req.getRequestDispatcher(prefix+ viewName + surfix).forward(req, resp);
			}
			
			
		}
	
	
}
