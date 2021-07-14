package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.enumtype.StatusType;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.vo.BuyerVO;
@WebServlet("/buyer/buyerdelete.do")
public class BuyerDeleteController extends HttpServlet{
	BuyerService service = new  BuyerServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			String buyerId = req.getParameter("what");
			String message = null; 
			String viewName = null;
			
			
			if(StringUtils.isBlank(buyerId)) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
		
			BuyerVO buyer = new BuyerVO();
			buyer = service.retrieveBuyerDetail(buyerId);
			if(buyer.getBuyerStatus().equals(StatusType.DELETE.getStatusName())) {
				viewName = "redirect:/buyer/buyerList.do";
				message = "이미 삭제된 거래처는 다시 삭제할 수 없습니다";
				
			}else {
				
				buyer.setBuyerStatus(StatusType.DELETE.getStatusName());
				buyer.setBuyerId(buyerId);
				
				Map<String ,List<String>> errors = new HashMap<>();
				req.setAttribute("errors", errors);
				boolean valid = new ValidatorUtils<>().validate(buyer, errors, DeleteGroup.class);
				
				if(valid) {
					ServiceResult result= service.deleteBuyer(buyer);
					switch(result) {
						case OK :
							viewName = "redirect:/buyer/buyerList.do";
							break;
						default: 
							message ="서버오류";
							viewName = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyerId();
						}	
					}else {
						viewName = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyerId();
					}
			}
		
				//req.setAttribute("message", message);
				req.getSession().setAttribute("message", message);
				//이동
	
				if (viewName.startsWith("redirect:")) {
					viewName = viewName.substring("redirect:".length());
					resp.sendRedirect(req.getContextPath() + viewName);
				} else {
					String prefix = "/WEB-INF/views/";
					String suffix = ".jsp";
					req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
				}
	}
}
