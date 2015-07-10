package com.mpoliako.calc.impl;

import java.util.Deque;
import java.util.LinkedList;

public class MathExprTokenizer {	
	
	private String expr;
	private Deque<String> stack = new LinkedList<>();
	
	public MathExprTokenizer(String expr) {
		this.expr = expr.replaceAll("\\s+", "");
		validate(this.expr);
	}
	
	public String nextToken() {
		if (stack.size() > 0) {
			return stack.pollLast();
		}
			
		return null;
	}
	
	private void validate(String expr) {
		char charArray[] = expr.toCharArray();
		int openBrackets = 0, closeBrackets = 0;
		
		for (int i = 0; i < charArray.length; i++) {
			if (i==0 && charArray[i] == '-') {
				stack.push("0");
				stack.push("-");
				continue;
			}
			if (MathUtils.isNumber(charArray[i])) {
				if (i!=0) {
					String before = stack.getFirst();
					if (!MathUtils.isOperation(before) && !MathUtils.isOpenBracket(before)) {
						throw new RuntimeException("Invalid input expression. There was no open bracket or math operation before number");
					}
				}
				String numberString = "";
				while (i < charArray.length && (MathUtils.isNumber(charArray[i]) || MathUtils.isDot(charArray[i]))) {
					numberString+=charArray[i];
					i++;
				}
				if (!MathUtils.isNumber(numberString))
					throw new RuntimeException("Invalid number. " + numberString);
				stack.push(numberString);
				i--;
				continue;
			}
			if (MathUtils.isOperation(charArray[i])) {
				String before = stack.getFirst();
				if (!MathUtils.isNumber(before) && !MathUtils.isCloseBracket(before)) {
					throw new RuntimeException("Invalid input expression. There was no closing bracket or number before math sign");
				}
				stack.push("" + charArray[i]);;
				continue;
			}
			if (MathUtils.isOpenBracket(charArray[i])) {
				openBrackets++;
				if (i==0) {
					stack.push("" + charArray[i]);
					continue;
				}
				String before = stack.getFirst();
				if (!MathUtils.isOperation(before) && !MathUtils.isOpenBracket(before)) {
					throw new RuntimeException("Invalid input expression. There was no open bracket or math operation before open bracket");
				}
				stack.push("" + charArray[i]);
				continue;
			}
			if (MathUtils.isCloseBracket(charArray[i])) {
				closeBrackets++;
				if (i==0) {
					stack.push("" + charArray[i]);
					continue;
				}
				String before = stack.getFirst();
				if (!MathUtils.isNumber(before) && !MathUtils.isCloseBracket(before)) {
					throw new RuntimeException("Invalid input expression. There was no close bracket or number before close bracket");
				}
				stack.push("" + charArray[i]);
				continue;
			}
			throw new RuntimeException("Unexpected char in Expression '" + charArray[i] + "'");
		}
		
		if (openBrackets!=closeBrackets) {
			throw new RuntimeException("Number of opened brackets is " + openBrackets + " while number of close brackets is " + closeBrackets);
		}
		
	}

	
	

}
