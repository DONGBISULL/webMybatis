package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.filter.wrapper.SampleHttpServletRequestWrapper;
/**
 * 원본 요청을 변경해서 
 * 파라미터에 있는 what 
 *	
 */
public class RequestChangeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override 	//원본 요청 request --->바꿔서 전달할 수 있도록 함
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		SampleHttpServletRequestWrapper wrapper = new SampleHttpServletRequestWrapper(req);
		chain.doFilter(wrapper, response);	//필터에서 받은 요청은 원본 요청이지만 필터를 빠져나갈 경우 요청 변경됨
	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
