package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.vo.BuyerVO;
@WebServlet("/buyer/buyerView.do")
public class BuyerViewServlet extends HttpServlet{
	BuyerService service = new  BuyerServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//언어 설정 응답도 설정하자 .
		req.setCharacterEncoding("utf-8");
		//파라미터로 id 받기 
		String buyerId = req.getParameter("what");
		//검증을 생각못했다... null인지 아닌지 검증
		if(StringUtils.isBlank(buyerId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		//뷰에 담기
		BuyerVO buyer = service.retrieveBuyerDetail(buyerId);
		//데이터 request에 세팅 
		req.setAttribute("buyer", buyer);
		//이동
		String viewName = "buyer/buyerView";
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect( req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix +viewName + suffix).forward(req, resp);
		}
	}

}
