package kr.or.ddit.commons;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

 
@WebServlet("/login/loginCheckT.do")
public class LoginCheckServletT extends HttpServlet {
		private AuthenticateService service = new AuthenticateServiceImpl();
	 
	private boolean validate(MemberVO param ,Map<String,String> errors) { 
		boolean valid = true;
		//값을 넘기는 경우 
	 	
		//객체 참조형 주소를 넘김==> callbyreference 
		
		if(StringUtils.isBlank(param.getMemId())) {
			valid = false;
			errors.put("mem_id" ,"아이디는 필수 입력");
		}
		
		if(StringUtils.isBlank(param.getMemPass())) {
				valid = false;
				errors.put("mem_pass" , "비밀번호를 필수 입력");
		}/*else {
			Pattern regexPtrn = Pattern.compile("^((?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8})$");
			Matcher matcher =  regexPtrn.matcher(param.getMem_pass());
			if(!matcher.find()) {
				valid = false;
				errors.put("mem_pass", "비밀번호는 영대소문자 숫자 특수 문자를 포함한 4~8 글자 이내");
				
			}else {
				System.out.println(matcher.group(1));//전체 문자열이 아니라 그룹일 때
			}
		}*/
		return valid ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1 파라미터 확보
		req.setCharacterEncoding("utf-8");
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		 
		String goPage = null;
		boolean redirect = false;
		//2 검증 --> 둘 다 있어야 함 
		HttpSession session =req.getSession();
		Map<String , String> errors  = new HashMap<>();
		session.setAttribute("errors", errors);
		MemberVO param = new MemberVO(mem_id , mem_pass);
		
		boolean valid = validate(param ,errors);
		 if(!valid) {
			 //필수 파라미터 누락 여부 확인(400)
			/* resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			 return ;*/
			 goPage= "/login/loginFormT.jsp";
			 redirect=true;//발생한 에러 상황에 대한 정보를 가진 errors를 가지고 가야 함
		 }else {
			//3 인증 
			 try {
				//지금 받은 건 java  
				 MessageDigest md = MessageDigest.getInstance("SHA-512");
				 //java를 바이트 형식으로 바꿈 
				 byte[] input = mem_pass.getBytes();
				//
				 byte[] encrypted = md.digest(input); 
				 mem_pass = Base64.getEncoder().encodeToString(encrypted);
				 param.setMemPass(mem_pass);  
				 
				 Object resultValue = service.authenticate(param);
				 if(resultValue instanceof MemberVO) {
					 // 1)  성공 : welcome page 이동  (redirection)
					  
					 	goPage ="/";
					 	redirect = true;
					 	session.setAttribute("authMember" , resultValue); 
					 	//로그인에 성공하면 웰컴 페이지로 이동해서 성공한 사람의 이름을 보여줄 것
					 	 
						//session.setAttribute("authId", mem_id);
					 	
					 	
					}else {
						// 2) 실패 : 다시 로그인 form 페이지로 이동 //id값은 value 로 나타남 (forward)
						goPage="/login/loginFormT.jsp";
						redirect = true;
						session.setAttribute("failId",mem_id);
						session.setAttribute("message", "비밀번호 오류");
					}
			 }catch (UserNotFoundException | NoSuchAlgorithmException e) {
				 goPage="/login/loginFormT.jsp";
				 redirect = true;
				session.setAttribute("message", e.getMessage());
			 }
			 
		  
		 	
		 if(redirect) {
			 resp.sendRedirect(req.getContextPath() + goPage);
		 }else {
			 RequestDispatcher rd =req.getRequestDispatcher(goPage);
			 rd.forward(req, resp); 
			 
		 }
			 
		 }
			 
		
		
		

		
		
	}
}
