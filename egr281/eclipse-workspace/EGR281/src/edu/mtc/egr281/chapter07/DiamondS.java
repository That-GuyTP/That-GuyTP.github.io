package edu.mtc.egr281.chapter07;

import java.util.Scanner;

public class DiamondS {

	public static void main(String[] args) {
		
		//DEC AND INST
		Scanner kb = new Scanner(System.in);
		
				
		//PROMPT for n
		System.out.print("Enter the value for n: ");		
				
		//n should be n - 1
		int n = (kb.nextInt()) - 1;
		
		//Outer loop -n to +n
		for(int i = -n; i <= +n; ++i) {
			
		
			//Leading spaces are equal to the absolute value of the outer loop index
			for(int spaces = 0; spaces < Math.abs(i); ++spaces) {
				System.out.print("");
			}//EOC INNER
			
			//Asterisk-s blocks are equal to n - the absolute value of the outer loop index
			for(int blocks = 0; blocks < (n - Math.abs(i)); ++blocks) {
				System.out.print("*S");
				
			}//EOC INNER
			
			//Ending asterisk combined with the new line
			System.out.println("*");
			
		}//EOC OUTER
				
		kb.close();
	}//EOC main
	
}//EOC DiamondS
