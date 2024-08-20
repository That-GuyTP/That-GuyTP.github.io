package edu.usc.csce145.chapter05.Exceptions;

public class Exceptions_CalculatorDemo {

	public static void main(String[] args) {
		
		//DECLARING OBJECT
		Exceptions_Calculator c = new Exceptions_Calculator();
		
		//BEGINNING calculate (from Exceptions_Calculator) METHOD OPERATION
		c.calculate();
		
		//WRAPPER CLASS EXAMPLE
		System.out.println(c.calc("123.20"));
		
		
		System.exit(0);
		
	}//EOC MAIN METHOD

}//EOC Exceptions_CalculatorDemo
