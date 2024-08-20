package edu.mtc.egr281.chapter03;

import java.text.DecimalFormat;

public class DecimalFormatDemo {

	public static void main(String[] args) {
		
		DecimalFormat oneDigitPastPoint = new DecimalFormat("#0.0");
		
		double number = 123.4567;
		
		System.out.println(number);
		System.out.println(oneDigitPastPoint.format(number));
		
	}//EOC main
	
}//EOC DFD
