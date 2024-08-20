package edu.usc.csce145.chapter05.Exceptions;

import java.util.Scanner;

public class Exceptions_Calculator {

	//INSTANCE VARIABLES
	private double result;
	private double precision = 0.000001;
	
	//DEFAULT CONSTRUCTOR
	public Exceptions_Calculator() {
		this.result = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETERIZED CONSTRUCTOR
	public Exceptions_Calculator(double xResult) {
		this.setResult(xResult);
	}//EOC PARAMETERIZED CONSTRUCTOR
	
	
	//ACCESSOR METHOD
	public double getResult() {
		return this.result;
	}//EOC getResult
	
	
	//MUTATOR METHOD
	public void setResult(double xResult) {
		this.result = xResult;
	}//EOC setResult
	
	
	//CALCULATE METHOD
	public void calculate() {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//CALCULATION LOOP
		boolean done = false;
		while(!done) {
			System.out.println("Result = " + this.result);
			
			//RECORDING THE OPERATION DESIRED
			char op = kb.next().charAt(0);
			
			//USING E TO EXIT CALC LOOP
			if(op == 'E' || op == 'e') {
				System.out.println("Exiting the program!");
				done = true;
			}else {
				double num2 = kb.nextDouble();
				
				//Used to allow the compiler to catch any errors. "try" must always be written first.
				try { 
					this.result = evaluate(op, this.result, num2);
				}catch(Exceptions_DivisionByZeroException e) {
					System.out.println(e.getMessage());
				}catch(Exceptions_UnknownOperatorException e) {
					System.out.println(e.getMessage());
				//You must always include this "catch(Exception e)". Must be organized last in catch order.
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}finally {
					
				}//EOC TRY-CATCH-FINALLY

			}//EOC IF-ELSE
		}//EOC while	
		
		kb.close();
		
	}//EOC calculate METHOD
	
	
	//CALCULATOR OPERATION METHOD
	public double evaluate(char op, double num1, double num2) throws Exceptions_DivisionByZeroException, Exceptions_UnknownOperatorException {
		double answer = 0;
		
		//SWITCH OPERATOR DEFEINER
		switch(op) {
		
		//ADD
		case '+':
			answer = num1 + num2;
			break;
			
		//SUBTRACT
		case '-':
			answer = num1 - num2;
			break;
			
		//MULTIPLY
		case '*':
			answer = num1 * num2;
			break;
			
		//DIVISION
		case '/':
			//USING && in the parameter since we want the inside of the range instead of any values outside of the range.
			if(num2 < precision && num2 > (-precision)) {
				throw new Exceptions_DivisionByZeroException();
			}else {
				answer = num1 / num2;
			}//EOC IF-ELSE
			break;
			
		//UNKNOWN OPERATOR
		default:
			throw new Exceptions_UnknownOperatorException();
		}//EOC SWITCH

		return answer;
	}//EOC evaluate METHOD
	
	
	//CONVERTING DATA TYPES METHOD / WRAPPER CLASS
	public double calc(String s) {
		double number = Double.parseDouble(s);
		return number;
	}//EOC calc METHOD
	
}//EOC EXCEPTIONS_CCALCULATOR
