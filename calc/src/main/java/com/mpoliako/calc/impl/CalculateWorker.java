package com.mpoliako.calc.impl;

import java.util.Scanner;

import com.mpoliako.calc.intf.Eval;

public class CalculateWorker {
	
	private Eval calc = new Calculator();
	
	public void start() {		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Calculator. If you want to exit enter 'exit' command");
		while(true) {
			System.out.println("Enter expression you want ro evaluate: ");
			String expression = in.next();
			if ("exit".equals(expression)) {
				System.out.println("GoodBye");
				break;
			}
			calc.exec(expression);
		}
		in.close();
	}

}
