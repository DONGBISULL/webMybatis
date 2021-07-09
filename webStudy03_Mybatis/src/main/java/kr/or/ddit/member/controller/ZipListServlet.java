package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.ZiptbVO;

@WebServlet("/member/zipList.do")
public class ZipListServlet extends HttpServlet {
	MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		
		String accept= 	req.getHeader("accept");
		
		List<ZiptbVO> zipList  = service.selectZipList();
		//req.setAttribute("zipList", zipList);
	    
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			
			try(
					PrintWriter out = resp.getWriter();
					){
				out.println(mapper.writeValueAsString(zipList));
			}
			
		}
	}
	 
}
