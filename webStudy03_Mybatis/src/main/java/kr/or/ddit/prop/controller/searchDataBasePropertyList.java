package kr.or.ddit.prop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prop.service.DataBasePropertyService;
import kr.or.ddit.prop.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;
 
@WebServlet("/11/searchServlet.do")
public class searchDataBasePropertyList extends HttpServlet {
	private DataBasePropertyService service = new DataBasePropertyServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		String input = req.getParameter("input");

		if(input==null || input.isEmpty()) {
			resp.sendError(400);
			return;
		}
		
		List<DataBasePropertyVO> propList = service.searchDataBasePropertyList(input);
		
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			
			try(
					PrintWriter out = resp.getWriter();
					){
					out.println(mapper.writeValueAsString(propList));
			}
		
		}
	
	}
}
