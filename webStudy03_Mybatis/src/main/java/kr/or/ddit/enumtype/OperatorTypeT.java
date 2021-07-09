package kr.or.ddit.enumtype;

public enum OperatorTypeT {

	PLUS('+', new RealOperator() {
		public double operate(double left , double right) {
			return left + right;
		}
	}),
	MINUS('-' , (left, right)->{return left-right;}),
	MULTIPLY('*' , (left, right)-> {return left* right;}),
	DIVIDE('/' , (left,right)->{return left/right;});
	
	@FunctionalInterface
	public static interface RealOperator{ //펑션널 인터페이스 functional interface
		public double operate(double left , double right);
		//public double operate1(double left , double right);
		//펑셔널 인터페이스일 땐 함수가 하나여야 함 
	}
	
	private RealOperator realOperator;
	
	private char sign;

	private OperatorTypeT(char sign , RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	public double operate(double left , double right) {
		return realOperator.operate(left, right);
	}

	public RealOperator getRealOperator() {
		return realOperator;
	}

	public void setRealOperator(RealOperator realOperator) {
		this.realOperator = realOperator;
	}

	public char getSign() {
		return sign;
	}

	public void setSign(char sign) {
		this.sign = sign;
	}
	private static final String EXPPTRN = "%f %c %f = %f";
	public String getExpression(double left , double right) {
		return String.format(EXPPTRN, left , sign , right, operate(left, right));
	}

}
