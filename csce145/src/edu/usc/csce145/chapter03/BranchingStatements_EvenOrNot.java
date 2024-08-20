package edu.usc.csce145.chapter03;

import java.util.Scanner;

public class BranchingStatements_EvenOrNot {

	public static void main(String[] args) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
	//Input
		//User Prompt Conversion
		System.out.println("Enter a number:");
		int number = (int)kb.nextDouble();
		
		//If-else even or odd check
		if(number%2 == 0) {
		//When the number when divided by 2 has a remainder of 0.
			System.out.println("Number entered is even!");
		}else {
			System.out.println("Number entered is odd!");
		}//EOC if-else
		
		
		kb.close();

	}//EOC main

}//EOC class
