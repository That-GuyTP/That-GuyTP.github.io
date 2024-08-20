package edu.usc.csce145.chapter07;

import java.util.Scanner;

public class SelectionSort {

	public static void main(String[] args) {
	
	//Scanner
	Scanner kb;
	kb = new Scanner(System.in);
		
	//User Prompt
	System.out.println("How many numbers would you like to sort?");
	int size;
	size = kb.nextInt();
	
	//Creating Array
	double[] myArray;
	myArray = new double[size];
	//double[] myArray = new double[size];
	//Esentially you're creating an array based off the size inputed by the user.
	
	//Storing Array Values
	for(int i = 0; i < myArray.length; i++) {
		//.length used to replace the need for the above variable "size".
		System.out.println("Enter the value of the array at index " + i + ":");
		myArray[i] = kb.nextDouble();
	}//EOC Storing Array Values
	
	//Selection Sort
	for(int i = 0; i < myArray.length; i++) {
		int minIndex = i;
		//minIndex means the variable will look for the smallest number in the order.
		double smallest = myArray[i];
		for (int j = 0; j <myArray.length; j++) {
			if(myArray[j] < smallest) {
				smallest = myArray[j];
				minIndex = j;
			}//EOC inner-inner if
		}//inner for
		//Using this for-if loop, the program will identify the value of the current double in the array, to the next value. If the next value is 
		//smaller it will swap places.
	
		//minIndex Organizer
		//Creating a temporary variable for storing the variable that is going to be swapped.
		if(smallest < myArray[i]) {
			double temp = myArray[i];
			myArray[i] = myArray[minIndex];
			myArray[minIndex] = temp;
		
		/*
		 * Example:
		 *    temp   myArray[i]   myArray[minIndex]
		 * 1.)       7			  3
		 * 2.) 7	 3            
		 * 3.)					  7
		 * 4.)		 3            7
		 */
		}//EOC minIndex Organizer
			
	}//EOC Selection Sort
	
	//Printing Array Type 1
	for(double i : myArray) {
		System.out.println(i);
	}//EOC for loop
	
	//Printing Array Type 2
	//for(int i = 0; i < myArray.length; i++) {
	//	System.out.print(myArray[i] + "\t");
	//}//EOC for loop
	
	
	kb.close();
	
	}//EOC main method

}//EOC SelectionSort
