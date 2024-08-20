package edu.mtc.egr281.chapter07;

import java.util.Scanner;

public class SampleArray2 {

	public static final int THIRTEEN = 13;
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		int[] myIntArray = new int[THIRTEEN];
		
		for(int i = 0; i < THIRTEEN; ++i) {
			myIntArray[i] = i;
		}//EOC for loop
		
		System.out.print("Enter a multiplier: ");
		int multiplier = kb.nextInt();
		
		for(int i = 0; i < THIRTEEN; ++i) {
			myIntArray[i] *= multiplier;
		}//EOC for statement
		
		for(int i : myIntArray) {
			System.out.print(i + " ");
		}//EOC for statement
		
		kb.close();
		
	}//EOC main
	
}//EOC SampleArray2
