package com.mpoliako.calc.impl;

public class MathUtils {
	
	private static String numberRegex = "\\d+([.]\\d+)?";
	
	public static boolean isDot(char c) {
		return c=='.';
	}

	public static boolean isOpenBracket(char c) {
		return c == MathOperation.OPEN_BRACKET.getSignum();
	}
	
	public static boolean isCloseBracket(char c) {
		return c == MathOperation.CLOSE_BRACKET.getSignum();
	}

	public static boolean isOpenBracket(String before) {
		return (before.length() == 1 && isOpenBracket(before.charAt(0)));
	}

	public static boolean isOperation(String before) {
		if (before.length() != 1 )
			return false;
		return isOperation(before.charAt(0));
	}

	public static boolean isNumber(char c) {
		return (c>='0' && c<='9');
	}

	public static boolean isNumber(String before) {
		return before.matches(numberRegex);
	}

	public static boolean isCloseBracket(String before) {
		return (before.length() == 1 && isCloseBracket(before.charAt(0)));
	}

	public static boolean isOperation(char c) {
		if(MathOperation.DIVIDE.getSignum()==c || MathOperation.MINUS.getSignum()==c || MathOperation.MULTIPLY.getSignum()==c || MathOperation.PLUS.getSignum()==c) {
			return true;
		}
		return false;
	}

}
