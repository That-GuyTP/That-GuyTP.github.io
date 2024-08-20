package edu.mtc.egr281.chapter07;

import java.util.Scanner;

public class SampleArray1 {

	public static void main(String[] args) {
		
		//Declar and Inst
		Scanner kb = new Scanner(System.in);
		
		
		//PROMPT
		System.out.print("How many chars to be stored: ");
		
		
		//Obtain the size of the array form the user
		int SizeOfArray = kb.nextInt();
		
		//Deckar and Inst Array
		char[] myCharArray;
		myCharArray = new char[SizeOfArray];
		
		//Populate
		for(int i = 0; i < myCharArray.length; ++i) {
			System.out.print("Enter a char #" + i + ": ");
			myCharArray[i] = (kb.next()).charAt(0);
		}//EOC for loop
		
		//OUTPUT
		for(char c : myCharArray) {
			System.out.println(c);
			
			
		}//EOC Each loop
		
		kb.close();
		
	}//EOC main
	
}//EOC SampleArray1
