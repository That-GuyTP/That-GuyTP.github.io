package edu.usc.csce145.chapter02;

import java.util.Scanner;
//Scanner is a PACKAGE that contains a predetermined library of class that are tools/utilities.

public class BranchingStatements_GreaterThanAValue {

	public static final int VALUE = 1000;
	
	
	public static void main(String args[]) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
	//Input
		System.out.println("Enter an integer:");
		int number = kb.nextInt();
		
	//Output
		if(number > VALUE) {
			System.out.println("Number is greater than 1000");
		}else {
			System.out.println("number entered is not greater than 1000");
		}//EOC if else
		//This is the if  else block. It is used to set parameters for outputs or calculatons. It will also allow the output of a program if a certain parameter was not met in the if block.
		
	//Type Casting Example
		double num1 = 7.3;
		int num2 = (int)num1;
		//Ensure that if you do type cast you MUST declare what the new data type is within paranthesis attached to the old variable as shown above.
		System.out.println("num2 =" + num2);
		System.out.println("num1 =" + num1);
		
		kb.close();
		
	}//EOC main
	
}//EOC class
