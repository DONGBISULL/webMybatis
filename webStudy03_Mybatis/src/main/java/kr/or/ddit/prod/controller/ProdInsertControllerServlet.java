package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertControllerServlet extends HttpServlet {
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();

	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		req.setAttribute("buyerList", buyerList);
		
		req.setAttribute("command", "INSERT");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		addAttribute(req);
		String viewName = "prod/prodForm";

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
		req.setCharacterEncoding("UTF-8");
		addAttribute(req);
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(prod, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}

		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = new ValidatorUtils<>().validate(prod, errors, InsertGroup.class);
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
				viewName = "prod/prodForm";
				message = "?????? ??????, ?????? ?????? ??????.";
				break;
			}
		} else {
			viewName = "prod/prodForm";
		}

		req.setAttribute("message", message);

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
