package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CharacterEncodingFilter implements Filter {
	 private String encoding ;
	//filterConfig :web.xml에서 <init-param> 태그를 통해 작성해둔 설정값 정보들과 ServletContext에 대한 참조

	 @Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 	encoding = filterConfig.getInitParameter("encoding");
		 	if(encoding == null || encoding.isEmpty()) {
		 		encoding = "UTF-8";
		 	}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
 

}
