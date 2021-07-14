package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EnumSet;
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

import com.sun.el.stream.Stream;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.enumtype.StatusType;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerInsert.do")
public class BuyerInsertController extends HttpServlet {
	OthersDAO otherDAO = new OthersDAOImpl();
	BuyerService service = new BuyerServiceImpl();
	
	
	private void setAttribute(HttpServletRequest req) {
	 	List<StatusType> statusList = new ArrayList<>();
		for (StatusType StatusTypes :  StatusType.class.getEnumConstants()) {
			//System.out.println(StatusTypes.getStatusName());
			statusList.add(StatusTypes);
		} 
	//	System.out.println(statusList);
		req.setAttribute("statusList", statusList);
		List<Map<String, Object>> lprodList = otherDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("command", "INSERT");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		setAttribute(req);
		String viewName = "buyer/buyerForm";
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
		// 언어 설정
		req.setCharacterEncoding("utf-8");
		// 파라미터 맵 받기
		BuyerVO buyer = new BuyerVO();
		Map<String, String[]> paramMap = req.getParameterMap();
		
		req.setAttribute("buyer", buyer);
		buyer.setBuyerStatus(StatusType.HOLDING.getStatusName());
		try {
			BeanUtils.populate(buyer, paramMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			 throw new ServletException(e);
		}
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		// 파라미터 검증
		boolean valid = new ValidatorUtils<>().validate(buyer, errors);
		// 메세지 보내기
		String message = null;
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createBuyer(buyer);
			switch(result) {
			case OK : 
				viewName = "redirect:/buyer/buyerView.do?what=" +buyer.getBuyerId();
				break;
				
			case FAIL : 
				viewName = "buyer/buyerForm";
				message ="서버 오류";
				break ;
			}
		}else {
			viewName = "buyer/buyerForm";
		}
		
		setAttribute(req);
		if (viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		} else {
			req.setAttribute("message", message);
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}		
		// 페이지 이동

	}

	 

}
