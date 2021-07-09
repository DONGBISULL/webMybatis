package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ReadTmplServlet extends HttpServlet {
	ServletContext application;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext(); //싱글톤 패턴이라 한 번만 실행
		
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StringBuffer template = readTemplate(req);
		makeData(req);
		String mime = getMime();
		resp.setContentType(mime); //고정시키면 다른 데이터 요청시킬때 문제됨
		makeResponseContents(template ,req ,resp);
	}
	
	
	protected abstract String getMime() ; //후크 메서드


	//1. tmpl 읽기 
	private StringBuffer readTemplate(HttpServletRequest req) throws IOException {
		StringBuffer template = null;
		String tmplPath = req.getServletPath(); //서블릿에 매핑 되어있는 url 주소 얻을 수 있음
		InputStream is =  application.getResourceAsStream(tmplPath);
		if(is!=null) {
			 
			try(
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					//여기선 finally가 자동으로 닫히도록 생성 됨 
			   ) {
					template = new StringBuffer();
					String tmp = null;
					while((tmp=reader.readLine())!=null) {
						template.append(String.format("%s\n",tmp));
				}
			} 
		}
		return template;
	}
	
	
	//2.데이터 만들기 *** ==> 여가서 내용이 달라질 것 => 추상
	
	protected abstract void makeData(HttpServletRequest req);//상속 받고 수정해야하기 때문에 private 이면 안됨 
	//callbyReference
	
	
	
	//3. 실제 데이터로 치환
	//4. 컨텐츠로 응답 전송
	private void makeResponseContents(
			StringBuffer template ,
			HttpServletRequest req ,
			HttpServletResponse resp
	) throws IOException {
		if(template==null) return;
		if(resp.isCommitted()) return ;
		
		String tmplSrc = template.toString();
//		#{식별자}
//		#{test1} , #{text2}
		Pattern regex =	Pattern.compile("#\\{([\\w_]+)\\}"); //중괄호 메타 문자 --> 이걸 그냥 문자로 읽도록 ㅅ확인 시켜줘야 
		Matcher matcher = regex.matcher(tmplSrc);
//a-zA-Z0-9 ==> \w 0글자 이상 * + 1글자 이상  자바라서 \\ 두개 넣어줘야 함 
//		req.getAttribute("test1")
//		req.getAttribute("test2")
		
		StringBuffer html = new StringBuffer();
		
		while(matcher.find()) {
			String name = matcher.group(1);
			Object data = req.getAttribute(name);
			matcher.appendReplacement(html, Objects.toString(data ,"")); //찾은 다음 데이터를 변경 하겠다 
		//Objects.toString(data ,"") ==> null문자 안찍히도록 변경도 가능 
		//Objects.toString(data) => object가 널에 위험해서 널에 위험하지 않도록 1.7부터 todrla 
		}
		matcher.appendTail(html);
		
		try(PrintWriter out = resp.getWriter();){ // try with resource 
			
			out.print(html);
		}
		
		
		
	}
	
	
	
}
