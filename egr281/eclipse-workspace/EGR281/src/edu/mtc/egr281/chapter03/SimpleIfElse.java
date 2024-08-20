package edu.mtc.egr281.chapter03;

import java.util.Scanner;

public class SimpleIfElse {

	public static void main(String[] args) {
		
		Scanner inputStream;
		
		inputStream = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		int number = inputStream.nextInt();
		
		if( (number % 2) == 0) {
			System.out.println("Number is even.");
		} else {
			System.out.println("Number is odd.");
		}//EOC if(number)
		
		System.out.println("End of program");
		
		inputStream.close();
		
	}//EOC main
	
}//EOC SimpleIfElse
