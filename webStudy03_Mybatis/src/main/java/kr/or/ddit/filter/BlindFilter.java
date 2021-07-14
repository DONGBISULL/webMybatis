package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlindFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	private Map<String,String> blindMap;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		logger.info("{} 초기화" ,getClass().getSimpleName() );
		blindMap = new HashMap<>();
		blindMap.put("127.0.0.1" , "이건 나 필터링");
		blindMap.put("0:0:0:0:0:0:0:1" , "이건 나 필터링");
		blindMap.put("192.168.44.39" , "이건 짝궁 필터링 ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String messageView = "/13/blindMessage.jsp";
//누구인지 ip를 통해 추적 가능
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		String clientIP = req.getRemoteAddr();// 서버 사이드 : local 서버 사이드 remote 클라이언트 사이드
		
		boolean pass =uri.equals(messageView)|| !blindMap.containsKey(clientIP);
		
		if(!pass) {
			//통과 시키면 안됨
			String reason = blindMap.get(clientIP);
			//인증과 관련된 모든 요청은 redirect로 이동
			req.getSession().setAttribute("reason", reason);
			resp.sendRedirect(req.getContextPath() + messageView);
		
		}else {
			//통과 
			chain.doFilter(request, response);
		} 
	}
	
	

	@Override
	public void destroy() {
		logger.info("{} 소멸" ,getClass().getSimpleName() );
		
	}
}
