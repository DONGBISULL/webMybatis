package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertController extends HttpServlet {
	MemberService service = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);

		String viewName ="member/memberForm";
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath()+viewName);
		}else {
			String prefix="/WEB-INF/views/";
			String surfix =".jsp";
			req.getRequestDispatcher(prefix+ viewName + surfix).forward(req, resp);
			}
	 
		
	}
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("command", "INSERT");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		addAttribute(req);
		
		MemberVO member = new MemberVO();
		Map<String,String[]> parameterMap = req.getParameterMap();
		req.setAttribute("member", member);
	
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			 throw new ServletException(e);
		}
		
		Map<String , String> errors = new HashMap<>();
		
		
		boolean valid = validate(member, errors);
		
		req.setAttribute("errors", errors);
		String message = null;
		String viewName = null;
		if(valid ) {
			ServiceResult result = service.createMemberVO(member);
				switch (result) {
				case OK:
					viewName = "redirect:/index.do";
					break;
				case PKDUPLICATED:
					//이미 존재하는 아이디
					viewName ="member/memberForm";
					message ="이미 존재하는 아이디입니다";
					break;
				default :
					viewName ="member/memberForm";
					message = "서버오류스";
				
				}
		}else {
			
			viewName ="member/memberForm";
		}
		
		req.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath()+viewName);
		}else {
			String prefix="/WEB-INF/views/";
			String surfix =".jsp";
			req.getRequestDispatcher(prefix+ viewName + surfix).forward(req, resp);
			}
		
	
	}
	
	private boolean validate(MemberVO member , Map<String ,String>errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원 id 누락");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "비밀 번호 누락");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원명 누락");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "주소 1  누락");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "주소 2 누락");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "메일 누락");
		}
		return valid;
		
	}
}
