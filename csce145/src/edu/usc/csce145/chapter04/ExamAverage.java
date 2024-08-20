package edu.usc.csce145.chapter04;

import java.util.Scanner;

public class ExamAverage {

	public static void main(String args[]) {
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Variables
		String response;
		response = "";
		
		//Do Loop
		do {
			int gradeCounter;
			gradeCounter = 0;
			
			double sum, average;
			sum = 0;
			average = 0;
			
			//Grades User Input
			System.out.println("enter the grades whose average you want to calculate. Enter a negative value once done.");
			double grade = kb.nextDouble();
			
		//While
		while(grade >= 0 && grade <= 100){
			sum += grade;//Also could be written as "sum = sum + grade;"	
			gradeCounter++;
			grade = kb.nextDouble();
		}//EOC while
		
			//If Statement
			if(gradeCounter > 0) {
				average = sum/gradeCounter;
				System.out.println("The average of the grades is: " + average);
			}//EOC if
			
			//Reprompt
			System.out.println("Do you want to calculate the average for another set of grades? Enter \"Yes\" or \"No\":");
			response = kb.next();
			
		}while(response.equalsIgnoreCase("Yes")); 
			
		
		kb.close();
		
	}//EOC main
	
}//EOC class
