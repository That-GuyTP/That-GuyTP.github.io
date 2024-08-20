package edu.usc.csce145.chapter05.copy;

//Thomas Peterson

public class TimeException extends Exception{

	//DEFAULT CONSTRUCTOR
	public TimeException() {
		super("EXCEPTION: Invalid time entered");
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public TimeException(String msg) {
		super(msg);
	}//EOC PARAMETER CONSTRUCTOR
	
}//EOC TimeException
