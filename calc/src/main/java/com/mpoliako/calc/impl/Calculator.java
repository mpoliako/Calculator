package com.mpoliako.calc.impl;

import java.util.Stack;

import com.mpoliako.calc.intf.Eval;
import com.mpoliako.calc.intf.Pile;

public class Calculator implements Eval {

	private Pile pile = new CalcStack();

	public String exec(String src) {
		MathExprTokenizer tokenizer = new MathExprTokenizer(src);
		return exec(tokenizer);
	}

	private String exec(MathExprTokenizer tokenizer) {
		String next;
		Stack<String> opStack = new Stack<String>();
		while ((next = tokenizer.nextToken()) != null && !MathUtils.isCloseBracket(next)) {
			if (MathUtils.isNumber(next)) {
				opStack.push(next);
				continue;
			}
			if(MathUtils.isOperation(next)) {
				if (opStack.size() == 1) {
					opStack.push(next);
					continue;
				} else {
					int priority = getPriority(next);
					if (priority == 1) {
						String second = opStack.pop();
						String operand = opStack.pop();
						String first = opStack.pop();
						String res = execOperation(second, operand, first);
						opStack.push(res);
						opStack.push(next);
					} else {
						String second = tokenizer.nextToken();
						if (MathUtils.isOpenBracket(second)) {
							second = exec(tokenizer);
						}
						String res = execOperation(second, next, opStack.pop());
						opStack.push(res);						
					}
				}
				continue;					
			}
			if (MathUtils.isOpenBracket(next)) {
				String res = exec(tokenizer);
				opStack.push(res);
			}			

		}
		
		if (opStack.size() == 1) {
			return opStack.pop();
		} else {
			return execOperation(opStack.pop(), opStack.pop(), opStack.pop());
		}
	}

	private String execOperation(String second, String operand, String first) {
		double fst = Double.parseDouble(first);
		double scnd = Double.parseDouble(second);
		switch (operand) {
			case "+":
				return new Double(fst + scnd).toString();
			case "-":
				return new Double(fst - scnd).toString();
			case "*":
				return new Double(fst * scnd).toString();
			case "/":
				return new Double(fst / scnd).toString();
			default:
				throw new RuntimeException("Invalid operation " + operand);
		}

	}

	private int getPriority(String next) {
		switch(next) {
			case "+":
			case "-": return 1;
			case "*":
			case "/": return 2;
			default: throw new RuntimeException("Invalid operation " + next);
		}
	}

}
