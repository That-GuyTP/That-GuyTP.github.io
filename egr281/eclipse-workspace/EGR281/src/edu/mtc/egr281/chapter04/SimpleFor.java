package edu.mtc.egr281.chapter04;

import java.util.Scanner;

public class SimpleFor {

	public static void main(String[] args) {
		
		//Dec and Inst
		Scanner kb = new Scanner(System.in);
		
		//Variables
		int testGrade = 0;
		int total = 0;
		
		System.out.print("Enter the number of grades: ");
		int numberOfGrades = kb.nextInt();
		
		for(int i = 0; i < numberOfGrades; ++i) {
			
			//Prompt for test grade
			System.out.print("Enter a test grade; ");
			
			//Obtain test grade
			testGrade = kb.nextInt();
			
			//Add test to total
			total += testGrade;
			
		}//EOC for
		
		//Output
		System.out.println("Number of grades enetered was " + numberOfGrades);
		System.out.println("The total sum was " + total);
		System.out.println("The average grade was" + (total/numberOfGrades));
		
		//Close
		kb.close();
		
	}//EOC main
	
}//EOC SimpleFor
