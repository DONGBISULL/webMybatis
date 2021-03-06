package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		List<BuyerVO> buyerList = othersDAO.selectBuyerList();
		req.setAttribute("buyerList", buyerList);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		addAttribute(req);
		String pageParam = req.getParameter("page");
//		String prodLgu = req.getParameter("prodLgu");
//		String prodBuyer = req.getParameter("prodBuyer");
//		String prodName = req.getParameter("prodName");
		Map<String, String[]> parameterMap = req.getParameterMap();
		ProdVO detailSearch = new ProdVO();
		try {
			BeanUtils.populate(detailSearch, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO  = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveProdList(pagingVO);
		
		String accept = req.getHeader("accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();	
			){
				mapper.writeValue(out, pagingVO);
			}
		}else {
//		?????? ?????? : ?????????, ??????????????????, ??????????????? (?????? ??????)
//		?????? ?????????????????? ?????? ?????? : ?????? ????????? ?????? ??? ?????? ????????? ???????????? ?????????.
			String viewName = "prod/prodList";
			if(viewName.startsWith("redirect:")) {
				viewName = viewName.substring("redirect:".length());
				resp.sendRedirect(req.getContextPath() + viewName);
			}else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);			
			}
		}
		
	}
}











