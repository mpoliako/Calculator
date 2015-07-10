package com.mpoliako.calc.impl;

public enum MathOperation {
	
	PLUS('+'), MINUS('-'), DIVIDE('*'), MULTIPLY('/'), OPEN_BRACKET('('), CLOSE_BRACKET(')');	
	
	private final char signum;
	
	MathOperation(char signum) {
		this.signum = signum;
	}
	
	public char getSignum() {
		return signum;
	}

}
