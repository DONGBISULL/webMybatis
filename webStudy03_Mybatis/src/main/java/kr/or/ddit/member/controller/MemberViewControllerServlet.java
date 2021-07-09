package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.UserNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet {
	MemberService service = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");     
		//파라미터 받기 
			String who = req.getParameter("who");
			MemberVO vo = null;
			String dept = "/WEB-INF/views/member/memberView.jsp";
			//파라미터 검증 통과 못하면 400 에러  --> catch 부분에 해당하면 404에러..?
		try {
			if(StringUtils.isBlank(who)) {
				 resp.sendError(400);
			}else {
				vo = service.retrieveMember(who);
				req.setAttribute("member", vo);
				req.getRequestDispatcher(dept).forward(req, resp);
				
				//StringBuffer htmls = new StringBuffer();
	/*htmls.append("<table >")	;		                                                                                            
	htmls.append(	" <tr><td>회원 id   </td><td>           "  + vo.getMem_id()            +     "</td></tr>"  );
	htmls.append(	" <tr><td>비밀 번호</td><td>    "   +        vo.getMem_pass()          +     "</td></tr>"  );
	htmls.append(	" <tr><td>회원명</td><td>       "   +        vo.getMem_name()          +     "</td></tr>"  );
	htmls.append(	" <tr><td>주민등록번호1</td><td>"   +        vo.getMem_regno1()        +     "</td></tr>"  );
	htmls.append(	" <tr><td>주민등록번호2</td><td>"   +        vo.getMem_regno2()        +     "</td></tr>"  );
	htmls.append(	" <tr><td>생일</td><td>         "   +        vo.getMem_bir()           +     "</td></tr>"  );
	htmls.append(	" <tr><td>우편번호</td><td>     "   +        vo.getMem_zip()           +     "</td></tr>"  );
	htmls.append(	" <tr><td>주소 1 </td><td>      "   +        vo.getMem_add1()          +     "</td></tr>"  );
	htmls.append(	" <tr><td>주소 2</td><td>       "   +        vo.getMem_add2()          +     "</td></tr>"  );
	htmls.append(	" <tr><td>집 전화 번호 </td><td>"   +        vo.getMem_hometel()       +     "</td></tr>"  );
	htmls.append(	" <tr><td>회사 전화번호</td><td>"   +        vo.getMem_comtel()        +     "</td></tr>"  );
	htmls.append(	" <tr><td>이동 전화번호</td><td>"   +        vo.getMem_hp()            +     "</td></tr>"  );
	htmls.append(	" <tr><td>메일</td><td>         "   +        vo.getMem_mail()          +     "</td></tr>"  );
	htmls.append(	" <tr><td>직업</td><td>         "   +        vo.getMem_job()           +     "</td></tr>"  );
	htmls.append(	" <tr><td>취미</td><td>         "   +        vo.getMem_like()          +     "</td></tr>"  );
	htmls.append(	" <tr><td>기념일 명</td><td>    "   +        vo.getMem_memorial()      +     "</td></tr>"  );
	htmls.append(	"<tr><td>기념일 날짜</td><td>"  +         vo.getMem_memorialday()   +     "</td></tr>"  );
	htmls.append(	"<tr><td>마일리지</td><td>   "  +         vo.getMem_mileage()       +     "</td></tr>"  );
	htmls.append(	"<tr><td>삭제 여부</td><td>  "  +         vo.getMem_delete()        +     "</td></tr>"  );
	htmls.append("</table >")	;				                                                                  
				try(
					PrintWriter out = resp.getWriter();	
						){
					
					out.println(htmls);
				}*/
				
			} 
		} catch (UserNotFoundException e) {
			resp.sendError(404 ,e.getMessage());
		
		}
			
			
			
			//통과 -->  서비스 의존 관계 
		
			//서비스를 통해 받은 컨텐츠  
			
			//
	
	}
}
