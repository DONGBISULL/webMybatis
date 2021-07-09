package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.OperateVO;

/**
 * 로그인 된 사용자가 자기 정보를 수정함
 *
 */
@WebServlet("/member/memberUpdate.do")
public class MemberUpdateController extends HttpServlet {
	private MemberService service = MemberServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMemId());

		req.setAttribute("member", member);

		String dest = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Parsing : parseInt , pattern 
		//문자열 특정 타입 
		//to_char(포매팅) ==> select로 가져올 때는 String
		//to_date && to_number(parsing) ==> 수정할 때는 to_date로 파싱을 해서 넘김
		req.setCharacterEncoding("utf-8");
		
		MemberVO member = new MemberVO();
		Map<String,String[]> parameterMap = req.getParameterMap();
		
		req.setAttribute("member", member);
	 
		//member.setMemId(req.getParameter("memId"));
		
		Map<String,String> errors = new HashMap<>();
		
		try {
			BeanUtils.populate(member, parameterMap); 
		} catch (IllegalAccessException | InvocationTargetException e) {//자바 빈 규약에 의해 만들어지지 않을 경우 예외 생김
			throw new ServletException(e);
		}
		
		req.setAttribute("errors", errors);
		ServiceResult result =	service.modifyMember(member);
		
		boolean valid = validate(member , errors);
		String viewName = null;
		String message = null;
		if(valid) {
		switch (result) {
		case OK:
			//mypage로 이동 ==> redirect 
			viewName ="redirect:/mypage.do";
			break;

		case INVALIDPASSWORD:
			//정상적 비번을 넣을 수 있는 곳으로 보냄 
			//memberForm.jsp로 이동  , -->forward
			message ="비밀번호 오류";
			viewName ="member/memberForm";
			break;
		default :
			//memberForm.jsp로 이동  , -->forward
			
			message ="서버 오류 이따가 다시 실행해주세요";
			viewName ="member/memberForm";
			
			break;
		}
	}else {
		//memberForm.jsp 이동
		//기존 입력 데이터 
		//errors 
		viewName ="member/memberForm";
	}
		req.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() +viewName );
		}else {
			String prefix="/WEB-INF/views/";
			String surfix =".jsp";
			req.getRequestDispatcher(prefix+ viewName + surfix).forward(req, resp);
		}
		
		
		
	}

/**
 * Member 테이블의 제약 조건에 따른 검증
 * @param member
 * @param errors
 * @return
 */

	private boolean validate(MemberVO member  ,Map<String,String> errors) {
		
		boolean valid = true;
		if(StringUtils.isBlank(member.getMemId())){ 
		valid=false;
		errors.put("memId", "회원 id 	누락");}
		if(StringUtils.isBlank(member.getMemPass())){
			valid=false;errors.put("memPass", "비밀 번호누락");
			}
		if(StringUtils.isBlank(member.getMemName())){ valid=false;errors.put("memName", "회원명누락");}
		if(StringUtils.isBlank(member.getMemZip())){ valid=false;errors.put("memZip", "우편번호누락");}
		if(StringUtils.isBlank(member.getMemAdd1())){ valid=false;errors.put("memAdd1", "주소 1 누락");}
		if(StringUtils.isBlank(member.getMemAdd2())){ valid=false;errors.put("memAdd2", "주소 2누락");}
		if(StringUtils.isBlank(member.getMemMail())){ valid=false;errors.put("memMail", "메일누락");}
		if(StringUtils.isNotBlank(member.getMemMemorialday())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					sdf.parse(member.getMemMemorialday());
				} catch (ParseException e) {
					valid = false;
					errors.put("MemMemorialday","날짜 형식 확인");
				}
		}
		 return valid;
	}
}