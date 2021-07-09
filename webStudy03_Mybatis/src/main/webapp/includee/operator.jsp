<%@page import="kr.or.ddit.enumtype.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	OperatorType[] ops =OperatorType.values();

	for(int i = 0 ; i<ops.length;i++){
		out.println(ops[i].getOpName());
		/* out.println(ops[i].valueOf(ops[i].getOpName()));
		 */
		out.print(ops[i]);
	}

%>