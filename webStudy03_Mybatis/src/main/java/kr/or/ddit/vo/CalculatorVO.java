package kr.or.ddit.vo;

import java.io.Serializable;

public class CalculatorVO implements Serializable{
	
	private String right ;
	private String left;
	private String expression ;
	
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
 
	
}
