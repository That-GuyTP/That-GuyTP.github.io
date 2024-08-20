package edu.usc.csce145.chapter07;

import java.util.Scanner;

public class MinMaxArray {
	
	//CONSTANT
	public static final int SIZE = 10;
	
	public static void main(String[] args) {
		
	//SCANNER	
	Scanner kb;
	kb = new Scanner(System.in);
		
	//ARRAY CREATION
	int[] myArray = new int[SIZE];
	for(int i = 0; i < myArray.length; i++) {
		System.out.println("Enter the value of the array at index " + i + ":");
		myArray[i] = kb.nextInt();
	}//EOC ARRAY CREATION
	
	//VARIABLES
	int smallest = myArray[0];
	int largest = myArray[0];
	int sum = 0;
	double average;
	
	//SMALLEST, LARGEST, & SUM LOOP
	for(int i = 0; i < myArray.length; i++) {
		
		//SMALLEST
		if(myArray[i] < smallest) {
			smallest = myArray[i];
		}//EOC SMALLEST IF
		
		//LARGEST
		if(myArray[i] > largest) {
			largest = myArray[i];
		}//EOC LARGEST IF
		
		sum += myArray[i];
	}//EOC SMALLEST, LARGEST, & SUM
	
	//AVERAGE CALCULATION
	average = sum/SIZE;
	
	//PRINT STATEMENT
	System.out.println("Your smallest value is: " + smallest);
	System.out.println("Your largest value is: " + largest);
	System.out.println("The average of all values is: " + average);
	
	
	kb.close();

	}//EOC MAIN METHOD

}//EOC MinMaxArray
