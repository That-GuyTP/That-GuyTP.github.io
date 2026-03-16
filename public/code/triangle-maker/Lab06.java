package edu.usc.csce145.chapter07;

//Thomas Peterson
//Please note that I will be attempting to switch up my organization format to something similar to what I used to use back when I took this course at midlands tech. This will feature the same indentation structure but,
//labels for the sections of the program and EOC's will be in capital letters, label code will be grouped together without spaces inbetween, and comments will be lowercase. I am doing this because I believe my previous structure
//was better organized than my current.

import java.util.Scanner;

public class Lab06 {

	public static void main(String args[]) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//VARIABLES
		int height;
		
		//HEIGHT VALUES
		System.out.println("How tall would you like the triangle to be?");
		height = kb.nextInt();
		
		
		//VALID NUMBER PROMPT
		//While not required, this line of code was just added for the sake of it. I will not be adding this to my flow chart since it isn't required, but just know that it is here.
		//It's purpose is to ensure the user has picked a number that will output a triangle.
		while(height < 2) {
			System.out.println("Sorry, I cannot create a triangle with that number. Please choose a number bigger than 1:");
			height = kb.nextInt();
		}//EOC VALID NUMBER PROMPT
		
	//TRIANGLE
	//I decided to split this into two sides of the triangle to make things simpler. Instead of trying to compile both sides into one long strand of code.
		
		//TRIANGLE UP
		//This is the top half of the triangle output. It will continue to print *'s for each line until it has reached the requested height.
		for(int i = 1; i <= height; i++) {
			for(int j = 0; j < i; j++) {
				System.out.print("*");
			}//EOC J LOOP
			System.out.println();
			//I have included this println() line here and in the "Triangle Down" section to allow for the code to begin printing on a new line for the next j loop case.
		}//EOC I LOOP
		
		
		//TRIANGLE DOWN
		//This is the bottom half of the triangle output. It will take the requested height, subtract 1, and then begin to print that amount of *'s for that line. 
		//It will continue to print *'s for each line in a decreasing value, until i has reached 0 where it will stop.
		for(int i = (height - 1); i >= 0; i--) {
			for(int j = 0; j < i; j++) {
				System.out.print("*");
			}//EOC J LOOP
			System.out.println();
		}//EOC I LOOP
		
		
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC Lab06
