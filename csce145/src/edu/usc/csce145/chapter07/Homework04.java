package edu.usc.csce145.chapter07;

//Thomas Peterson

//Required Aspects
/*Only use Arrays for collection
 *Must self sort
 *If provided # of circles is invlaid, the program must inform and continue.
 *Provide: Circles' data, sorted gradually or reverse gradually, display only unique circles, and/or quit the program.
 */

import java.util.Scanner;

public class Homework04 {

	//CONSTANTS
	public static final double PIE = 3.14;
	
	public static void main(String args[]) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//VARIABLES
		int numberOfCircles;
		int choice;
		double area;
		double temp;
		
		
		//WWELCOME & SIZE COLLECTION
		System.out.println("Welome to the circle collection and calculator. Please enter the number of circles you wish to store.");
		numberOfCircles = kb.nextInt();
		
		//VALID NUMBER CHECK
		while(numberOfCircles <= 0) {
			System.out.println("Sorry that isn't a valid number. Please try again and enter a valid number.");
			numberOfCircles = kb.nextInt();
		}//EOC VALID NUMBER CHECK
		
		//ARRAY VALUE STORAGE
		double[] circleArray = new double[numberOfCircles];
		double[] areaArray = new double[numberOfCircles];
		for(int i = 0; i < circleArray.length; i++) {
			System.out.println("Please enter the radius of circle " + (i + 1) + ":");
			circleArray[i] = kb.nextDouble();
			
			//AREA CALCULATION AND STORAGE
			area = (circleArray[i] * circleArray[i])*PIE;
			areaArray[i] = area;
		}//EOC ARRAY VALUE STORAGE
		
		//OUTPUT CHOICE
		System.out.println("Please enter what you would like to do next.");
		System.out.println("Type: \"1\" to display all entered data, \"2\" to sort each circle's area from smallest to largest, \"3\" to sort each circle's area from largest to smallest, ");
		System.out.println("\"4\" to find and display all unqiue values in the collection, or \"5\" to quit the program.");
		choice = kb.nextInt();
		
		//VALID CHOICE CHECK
		while(choice <0 || choice > 5) {
			System.out.println("Sorry that isn't a valid option. Please try again.");
			choice = kb.nextInt();
		}//EOC VALID CHOICE CHECK
		
	//CHOICE OUTPUT IF'S
		//CHOICE 1
		if(choice == 1) {
			for(int i = 0; i < circleArray.length; i++) {
				System.out.println("Here is all of the entered data & their areas:");
				System.out.println(circleArray[i] + "   " + areaArray[i]);
			}//EOC for
			System.out.println("Have a good day.");
			System.exit(0);
			
		//CHOICE 2
		}else if(choice == 2) {
			System.out.println("Here is all of the areas sorted from smallest to largest:");
			
			//SELECTION SORT SMALLEST TO LARGEST
			for(int i = 0; i < areaArray.length; i++) {
				for(int j = 0; j <areaArray.length; j++) {
					if(areaArray[i] < areaArray[j]) {
						temp = areaArray[i];
						areaArray[i] = areaArray[j];
						areaArray[j] = temp;
					}//EOC SMALLEST IF
				}//EOC J FOR
			}//EOC I FOR
			
			//CHOICE 2 PRINT
			for(int i = 0; i < areaArray.length; i++) {
				System.out.println(areaArray[i]);
			}//EOC PRINT FOR LOOP
			System.out.println("Have a good day.");
			System.exit(0);
			
		//CHOICE 3
		}else if(choice == 3) {
			System.out.println("Here is all of the areas sorted from largest to smallest:");
			
			//SELECTION SORT SMALLEST TO LARGEST
			for(int i = 0; i < areaArray.length; i++) {
				for(int j = 0; j <areaArray.length; j++) {
					if(areaArray[i] > areaArray[j]) {
						temp = areaArray[i];
						areaArray[i] = areaArray[j];
						areaArray[j] = temp;
					}//EOC SMALLEST IF
				}//EOC J FOR
			}//EOC I FOR
			
			//CHOICE 3 PRINT
			for(int i = 0; i < areaArray.length; i++) {
				System.out.println(areaArray[i]);
			}//EOC PRINT FOR LOOP
			System.out.println("Have a good day.");
			System.exit(0);
			
		//CHOICE 4
		}else if(choice == 4) {
			System.out.println("Here are all the area's that are not duplicates:");
			
			//SORTING FROM SMALL TO LARGE
			//I am using this so I can create a better for loop check for duplicates.
			for(int i = 0; i < areaArray.length; i++) {
				for(int j = 0; j <areaArray.length; j++) {
					if(areaArray[i] < areaArray[j]) {
						temp = areaArray[i];
						areaArray[i] = areaArray[j];
						areaArray[j] = temp;
					}//EOC SMALLEST IF
				}//EOC J FOR
			}//EOC I FOR
			
			//DUPLICATE FINDER
			//After the array is sorted small to big, I will use the variable k to place hold a value in the array and compare it to the
			//next value in the array. Once it is being compared to a value that isn't itself, it will switch values with that value,
			//and remove the duplicates in the array.
			int k = 0;
			areaArray[k] = areaArray[0];
			for(int i = 0; i < areaArray.length; i++) {
				if(areaArray[k] != areaArray[i]) {
					k++;
					areaArray[k] = areaArray[i];
				}//EOC INNER IF
			}//EOC I FOR
			
			//NON DUPLICATE ARRAY PRINT
			for(int i = 0; i <= k; i++) {
				System.out.println(areaArray[i]);
			}//EOC NON DUPLICATE ARRAY PRINT
			
			System.out.println("Have a good day.");
			System.exit(0);
			
		//CHOICE 5
		}else if(choice == 5) {
			System.out.println("Thank you for using this calculator. Have a good day");
			System.exit(0);
		}//EOC CHOICE OUTPUT IF'S
		
		
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC Homework04
