package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.NumericOperator;
import kr.or.ddit.enumtype.OperatorType;
import kr.or.ddit.vo.CalculatorVO;

@WebServlet("/calculate.do")
public class CalculatorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String op = req.getParameter("operator");
			String mime = req.getParameter("mime");
			String rightStr = req.getParameter("right");
			String leftStr = req.getParameter("left");
			String expression = null;
			
			if(mime==null || mime.isEmpty()) {
				mime = req.getHeader("accept");
			}
			
			int status = 200;
			int right  = 0;
			int left = 0;
			double result =0;
			
			if(op==null||op.isEmpty()||rightStr==null||!rightStr.matches("^[0-9]+$") || rightStr.equals("0")
					||leftStr==null||!leftStr.matches("^[0-9]+$") ) {
				status = 400;
			}else {
				right = Integer.parseInt(rightStr);
				left = Integer.parseInt(leftStr);
				OperatorType opType= OperatorType.findOpType(op);
				result = opType.operate(left, right);
				expression = left  + " " +OperatorType.getOpName(op) + " " + right +" = " + result;
				
				CalculatorVO target = new CalculatorVO();
				target.setLeft(leftStr);
				target.setRight(rightStr);
				target.setExpression(expression);
				
				try {
					getContents(target , mime,req );			
					
				} catch (Exception e) {
					status = 500;
				}
				
			
				if(status==200) {
					Object contentType = req.getAttribute("contentType");
					resp.setContentType(Objects.toString(contentType, ""));
					try(
							PrintWriter out = resp.getWriter();
							){
						Object contents = req.getAttribute("contents");
						out.println(contents);
					}
				}else {
					resp.sendError(status);
				}
				
				
				
			}
				
				
	}

	private void getContents(CalculatorVO target, String mime, HttpServletRequest paramMap) throws JsonProcessingException {
	 
		String contentType = null;
		Object contents = null;
		
		if(mime.contains("json")) {
			contentType = "application/json;charset=UTF-8";
			ObjectMapper mapper = new ObjectMapper();
			contents = mapper.writeValueAsString(target);
		}else {
			contentType = "text/plain;charset=UTF-8";
			contents=target.getExpression();
		}
		
		paramMap.setAttribute("contentType" , contentType);
		paramMap.setAttribute("contents" , contents);
		
		
	}
	
	
	
	
	 
	
}
