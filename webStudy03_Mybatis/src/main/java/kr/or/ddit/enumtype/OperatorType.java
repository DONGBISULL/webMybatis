package kr.or.ddit.enumtype;

import kr.or.ddit.commons.NumericOperator;

public enum OperatorType  {
	PLUS("+",new NumericOperator(){
		@Override
		public double operate(int left, int right) {
			return left +right;
		}
	}),
	MINUS("-", new NumericOperator() {
		@Override
		public double operate(int left, int right) {
			 
			 return  left -right;
		}
	}),
	MUL("*" , new NumericOperator() {
		@Override
		public double operate(int left, int right) {
			return left *right;
		}
	}),
	DIV("/" , new NumericOperator() {
		
	@Override
		public double operate(int left, int right) {
			return  left /right;
		}	
		
	}),
	QUOTIENT("%" , new NumericOperator() {
		@Override
		public double operate(int left, int right) {
			return left %right;
		}
	});
	
	private String opName;
	private NumericOperator realOperator;
	
	private OperatorType(String opName, NumericOperator realOperator) {
		this.opName = opName;
		this.realOperator = realOperator;
	}


	private OperatorType(String opName) {
		this.opName = opName;
	}
	 
	public String getOpName() {
		return opName;
	} 
	public void setOpName(String opName) {
		this.opName = opName;
	}
	
	public NumericOperator getRealOperator() {
		return realOperator;
	}
	public void setRealOperator(NumericOperator realOperator) {
		this.realOperator = realOperator;
	}
	
	public  double operate(int left, int right ) {
		 
			return realOperator.operate(left, right);
	}
	
	public static OperatorType findOpType(String accept) {
		accept = accept.toUpperCase();
		OperatorType finded = OperatorType.PLUS;
		
		if(accept!=null) {
			for(OperatorType tmp : values()) {
				if(accept.indexOf(tmp.name())!=-1) {
					finded = tmp;
					break;
				}
			}
		}
		return finded;
	}
	
	

	public static String getOpName(String param) {
		 
		return findOpType(param).getOpName();
	}

 
	
}
