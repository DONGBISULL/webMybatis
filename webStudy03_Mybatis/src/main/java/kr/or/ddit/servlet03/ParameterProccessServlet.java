package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//절대 경로 ==> 서버(톰캣)이 사용할 거라 contextPath 사용 안함 
@WebServlet("/03/parameterProcess")
public class ParameterProccessServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.printf("현재 요청 메소드 : %s\n", req.getMethod());
		//request로 넘어오는 자료들 Map으로 관리되고 있음 
		Map<String , String[]>parameterMap = req.getParameterMap();
		for(Entry<String, String[]> entry :parameterMap.entrySet()) { //순차접근이 가능한 것들만 향상된 for문 사용 
			String paramName = entry.getKey();
			String[] paramValue = entry.getValue();
			System.out.printf("%s : %s\n" , paramName,Arrays.toString(paramValue));
		}
	
	}
	
 
	
	
}
