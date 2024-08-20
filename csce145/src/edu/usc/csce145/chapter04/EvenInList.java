package edu.usc.csce145.chapter04;

import java.util.Scanner;

public class EvenInList {

	public static void main(String[] args) {
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Lower Input
		System.out.println("Enter the lower bound of a range of numbers:");
		int lower; 
		lower = kb.nextInt();
		
		//Upper Input
		System.out.println("Enter the upperound of a range of numbers:");
		int upper;
		upper = kb.nextInt();
		
		//For Loop
		for(int i = lower; i <= upper; i++) {
			
			//If Statement
			if(i%2 == 0) {
				System.out.println(i);
			}//EOC if
			
		}//EOC for
		
		
		kb.close();
		
	}//EOC main

}//EOC class
