package kr.or.ddit.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.or.ddit.enumtype.OperatorTypeT;
@JsonIgnoreProperties("mime")
public class OperateVO {
	private double left ;
	private double right ;
	private OperatorTypeT  operator ;
	private char sign;
	private String expression;
	
	public String getExpression() {
		return operator.getExpression(left, right);
	}
	
	public char getSign() {
		return sign ;
	}
	
	  
	private double result ;
	
	public OperateVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperateVO(double left, double right, OperatorTypeT operator) {
		super();
		this.left = left;
		this.right = right;
		this.operator = operator;
		
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}

	public OperatorTypeT getOperator() {
		return operator;
	}

	public void setOperator(OperatorTypeT operator) {
		this.operator = operator;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public void setSign(char sign) {
		this.sign = sign;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}



}
