package com.mpoliako.calc.impl;

import java.util.Stack;

import com.mpoliako.calc.intf.Pile;

public class CalcStack implements Pile{
	
	@SuppressWarnings("rawtypes")
	private Stack stackImpl = new Stack();

	@SuppressWarnings("unchecked")
	public void push(Object val) {
		stackImpl.push(val);
	}

	public Object pop() {
		return stackImpl.pop();
	}

	public Object peek() {
		return stackImpl.peek();
	}

	

}
