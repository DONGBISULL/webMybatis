package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan.tmpl")
public class GugudanServlet extends ReadTmplServlet {
	@Override
	//1 . /01/gugudan.tmpl 읽기 
	//2. 데이터 만들기 
	//3. 마임을 설정해서 보내주기
	protected void makeData(HttpServletRequest req) {
		String dan =""; 
		
		String minStr = req.getParameter("minDan");
		String maxStr = req.getParameter("maxDan");
		
		int minDan = 2 ;
		if(minStr!=null&&minStr.matches("\\d{1,2}")) {
			Integer.parseInt(minStr);
		}
		//StringBuffer=> 데이터가 휘발성이에욤 데이터 과부하를 막기 위해 String이 아니라 
		//힙은 가비지 컬렉션에서 지워짐---> 자바라서 !! html에선 안지워짐 
		StringBuffer gugudan = new StringBuffer();
		int maxDan = 9 ;
		if(maxStr!=null&& maxStr.matches("[0-9]+")) {
			maxDan = Integer.parseInt(maxStr);
		}
		
		for(int j = minDan ; j<=maxDan ; j++) {
				gugudan.append("<tr>");
				gugudan.append("<td>");
				gugudan.append(j+"단");
				gugudan.append("</td>");
				
			for(int i =1 ; i<10;i++) {
				gugudan.append("<td>");
				gugudan.append(" " + j +" * " + i +" = " +i*j +" ") ;
				gugudan.append("</td>");
			}
			gugudan.append("</tr>");
		}
		 
		req.setAttribute("gugudan", gugudan);
	}

	@Override
	protected String getMime() {
		return "text/html;charset=utf-8";
	}


}
