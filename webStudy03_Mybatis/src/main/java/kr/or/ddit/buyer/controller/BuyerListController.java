package kr.or.ddit.buyer.controller;

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

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@WebServlet("/buyer/buyerList.do")
public class BuyerListController extends HttpServlet {
	OthersDAO otherDAO = new OthersDAOImpl();
	BuyerService service = new BuyerServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//언어 설정
		req.setCharacterEncoding("utf-8");
		//데이터 세팅
		setAttribute(req);
		
		//페이지 받기	
		String pageParam = 	req.getParameter("page");
		//페이지가 없으면 기본 페이지 
		int currentPage = 1 ; 
		
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		//폼에서 받은 데이터로 검색어가 있을 경우 detailSearch 세팅 
		Map<String , String[]> map =req.getParameterMap();
		
		BuyerVO detailSearch = new BuyerVO();
		
		try {
			BeanUtils.populate(detailSearch, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		PagingVO<BuyerVO> paging = new PagingVO<>();
		paging.setCurrentPage(currentPage);
		
		paging.setDetailSearch(detailSearch);

		service.retrieveBuyerList(paging);
		//데이터 세팅
		req.setAttribute("paging",paging);
		//리스트를 보내기 위해 json 쓰기 --> 비동기 방식으로 폼을 넘길 경우
		String accept = req.getHeader("accept");
		//containsIgnoreCase 문자열을 포함하고 있으면 true 
		//대소문자 상관없이 문자열 포함관계 비교.
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();
					){
				mapper.writeValue(out, paging);
			}
		}else { // 비동기 아닐 경우 원하는 방식으로 보내기 
			
			String viewName = "buyer/buyerList";
			//redirect면 바로 redirect로 보내 
			if(viewName.startsWith("rediect:")) {
				viewName= viewName.substring("redirect:".length());
				resp.sendRedirect(req.getContextPath() +  viewName);
				
			}else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
			}
			
			
		}
		
		
		
	}


	private void setAttribute(HttpServletRequest req) {
		List<Map<String ,Object>>lprodList = otherDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
	}
}
