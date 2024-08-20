package edu.mtc.egr281.chapter04;

import java.util.Scanner;

public class SimpleDoWhile {

	public static void main(String[] args) {
		
		//Dec and Inst
		Scanner inputStream = new Scanner(System.in);
		
		//Variable - single test grade
		int testGrade = 0;
		
		//Variable - total grades
		int total = 0;
		
		//Variable - grades entered
		int numberOfGrades = -1;
		
		do {
			//ADD test grade to total
			total += testGrade;
			
			//Increment the number of grades
			++numberOfGrades;
			
			//Prompt user for input of test grade
			System.out.print("enter a test grade [-1 to quit]: ");
			
			//Obtain test grade
			testGrade = inputStream.nextInt();
			
			
		} while(testGrade != -1);
		
		System.out.println("Number of grades entered was " + numberOfGrades);
		System.out.println("The total sum was " + total);
		System.out.println("The average grade was " + (total/numberOfGrades));
		
		//Close
		inputStream.close();
		
	}//EOC main
	
}//EOC SDW
