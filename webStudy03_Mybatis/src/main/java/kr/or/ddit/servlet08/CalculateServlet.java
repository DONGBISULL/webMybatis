package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.OperatorTypeT;
import kr.or.ddit.vo.OperateVO;
@WebServlet("/calculateT.do")

public class CalculateServlet extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String contentType = req.getHeader("Content-Type");
		String accept = req.getHeader("Accept");
		OperateVO operateVO = null;
		
		if(contentType!=null && contentType.contains("json")) {
			operateVO = generateVOFromJson(req);
		}else {
			operateVO = generateVOFromParameter(req);
		}
		
		Object content = null;
		
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			content = mapper.writeValueAsString(operateVO);
		}else {
			resp.setContentType("text/plain;charset=utf-8");
			content  = operateVO.getExpression();
		}
		
		try(
				PrintWriter out = resp.getWriter();
				){
			out.print(content);
		}
		
		
	}

	private OperateVO generateVOFromParameter(HttpServletRequest req) {
		String leftParam = req.getParameter("left");
		String rightParam = req.getParameter("right");
		String operator = req.getParameter("operator");
		double left =Double.parseDouble(leftParam);
		double right = Double.parseDouble(rightParam);
		OperatorTypeT operatorTypeT = OperatorTypeT.valueOf(operator);
		OperateVO operateVO = new OperateVO(left, right, operatorTypeT);
		
		return operateVO;
	}

	private OperateVO generateVOFromJson(HttpServletRequest req) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		try(
				InputStream is = req.getInputStream();
				){
			return mapper.readValue(is, OperateVO.class);
		}
		
		   
	}
	
	
}
