package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
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
import kr.or.ddit.utils.ValidatorUtils;
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
		
		Map<String ,List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		
		boolean valid = new ValidatorUtils<>().validate(member, errors);
		
		String message = null;
		String viewName = null;
		if(valid ) {
			ServiceResult result = service.createMemberVO(member);
				switch (result) {
				case OK:
					viewName = "redirect:/index.do";
					break;
				case PKDUPLICATED:
					//?????? ???????????? ?????????
					viewName ="member/memberForm";
					message ="?????? ???????????? ??????????????????";
					break;
				default :
					viewName ="member/memberForm";
					message = "???????????????";
				
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
	
//	private boolean validate(MemberVO member , Map<String ,String>errors) {
//		boolean valid = true;
//		
//		if (StringUtils.isBlank(member.getMemId())) {
//			valid = false;
//			errors.put("memId", "?????? id ??????");
//		}
//		if (StringUtils.isBlank(member.getMemPass())) {
//			valid = false;
//			errors.put("memPass", "?????? ?????? ??????");
//		}
//		if (StringUtils.isBlank(member.getMemName())) {
//			valid = false;
//			errors.put("memName", "????????? ??????");
//		}
//		if (StringUtils.isBlank(member.getMemZip())) {
//			valid = false;
//			errors.put("memZip", "???????????? ??????");
//		}
//		if (StringUtils.isBlank(member.getMemAdd1())) {
//			valid = false;
//			errors.put("memAdd1", "?????? 1  ??????");
//		}
//		if (StringUtils.isBlank(member.getMemAdd2())) {
//			valid = false;
//			errors.put("memAdd2", "?????? 2 ??????");
//		}
//		if (StringUtils.isBlank(member.getMemMail())) {
//			valid = false;
//			errors.put("memMail", "?????? ??????");
//		}
//		return valid;
//		
//	}
}
