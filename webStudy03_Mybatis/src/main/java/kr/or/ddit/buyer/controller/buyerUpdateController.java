package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.enumtype.StatusType;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerUpdate.do")
public class buyerUpdateController extends HttpServlet {
	
	OthersDAO otherDAO = new OthersDAOImpl();
	BuyerService service = new BuyerServiceImpl();
	
	private void setAttribute(HttpServletRequest req) {
		List<StatusType> statusList = new ArrayList<>();
		for(StatusType statusTypes : StatusType.class.getEnumConstants()) {
			 if(statusTypes == StatusType.DELETE) {
				continue;
			} 
			statusList.add(statusTypes);
		}
		req.setAttribute("statusList", statusList);
		List<Map<String, Object>> lprodList = otherDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("command", "UPDATE");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//인코딩 
		req.setCharacterEncoding("utf-8");
		//파라미터 받기 what 
		setAttribute(req);
		String buyerId = req.getParameter("what");
		String viewName = "buyer/buyerForm";
	
		//파라미터 검증 
		if(StringUtils.isBlank(buyerId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		BuyerVO buyer = service.retrieveBuyerDetail(buyerId);
		req.setAttribute("buyer", buyer);
		
		String message = null;
		if(buyer.getBuyerStatus().equals("삭제")) {
			viewName = "redirect:/buyer/buyerList.do";
			message = "삭제된 거래처는 수정할 수 없습니다";
		}
			req.getSession().setAttribute("message", message);
		//폼으로 보내기
		if (viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//언어 설정
		req.setCharacterEncoding("utf-8");
		//파라미터 설정
		setAttribute(req);
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		//폼 데이터를 map 형태로 받기 
		
		Map<String , String[]> paraMap = 	req.getParameterMap();
		// 폼데이터 빈유틸로 세팅 
		try {
			BeanUtils.populate(buyer, paraMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}

		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		
		//검증 
		boolean valid = new ValidatorUtils<>().validate(buyer, errors, UpdateGroup.class);
		String viewName = null;
		String message = null;
		
		//결과 받기 
		if (valid) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case OK:
				viewName = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyerId();
				break;
			default:
				viewName = "buyer/buyerForm";
				message = "서버 오류";
				break;
			}
		} else {
			viewName = "buyer/buyerForm";
		}
		
		req.setAttribute("message", message);
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
