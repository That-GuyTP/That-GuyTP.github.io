package edu.usc.csce145.chapter05.Exceptions;

public class Exceptions_DivisionByZeroException extends Exception{
//*Added "extends Exception". This is adding a child class(Exceptions_DivisionByZeroException) to the parent class(Exception).
	
	//CALLING DEFAULT CONSTRUCTOR
	//Using the word "super" will allow the user to call the base class Exception's constructor.
	public Exceptions_DivisionByZeroException() {
		super("Cannot divide by zero!");
	}//EOC Exceptions_DivisionByZeroException METHOD
	
	
	//PARAMETERIZED CONSTRUCTOR
	public Exceptions_DivisionByZeroException(String msg) {
		super(msg);
	}//EOC PARAMETERIZED CONSTRUCTOR
	
	
}//EOC Exceptions_DivisionByZeroException
