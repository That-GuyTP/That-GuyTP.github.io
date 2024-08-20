package edu.usc.csce145.chapter05.Exceptions;

public class Exceptions_UnknownOperatorException extends Exception{

	//CALLING DEFAULT CONSTRUCTOR
	public Exceptions_UnknownOperatorException() {
		super("Unknown Operator entered!");
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETERIZED CONSTRUCTOR
	public Exceptions_UnknownOperatorException(String msg) {
		super(msg);
	}//PARAMETERIZED CONSTRUCTOR
	
	
	
	
	
}//EOC Exceptions_UnknownOperatorException
