package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Decorationg Filter Pattern 의 이용 
 * 	: 요청과 응답에 대한 변형을 가하되 원본 성질 자체를 바꾸지 않는 부가 레이어
 *
 *	1. Filter의 구현체 
 *		- 필터링 수행  call back(do Filter) : chain.doFilter 를 통해 제어권을 적절히 이동
 *	2. WAS 등록하고 , Filter Chain이 형성 되도록 함
 *		-filter chaing 내에서 필터링 되는 순서는 등록된 순서를 따름
 *	3. 필터링 요청에 대한 매핑 설정(경로 매핑, 확장자 매핑 사용)
 *	
 *	필터는 흐름 변경 가능 
	필터 체인 존재 
	필터는 순서를 가짐
	필터는 요청을 받을 때와 응답할 때 역순 

 *
 *
 *================================
 *	자유게시판 (누구나 글 등록 가능) ==> 경고 5번 이상인 사람 잠깐 사용 못하도록	
 *
 */
/*@WebFilter("/*") //1. 어노테이션 버전에 따라 다름 2. 필터 여러개 생성 시 순서 생성에 대한 설정이 없음
*/
public class FilterDesc implements Filter{
//필터 톰캣에 의해 운영 --> singlton 필터의 객체를 여러개 운영하지 않는다.
	private static final Logger logger = LoggerFactory.getLogger(FilterDesc.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화" , getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//필터 여러개 형성시 체인 구조 생성 ---> 필터 체인의 제어권 filter chain
		
		long start = System.currentTimeMillis();
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("{} 요청 발생" , req.getRequestURI());
		chain.doFilter(request, response); // 메소드 필터링이 역순으로 이어짐
		
		long end = System.currentTimeMillis();
		logger .info("{} 처리 소요 시간 {}ms" , req.getRequestURI() , (end-start));
		
		
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸" , getClass().getSimpleName());
		
	}

}
