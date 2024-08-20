package edu.mtc.egr281.chapter03;

import java.util.Scanner;

public class SwitchCharDemo {

	public static void main(String[] args) {
		
		//Store
		Scanner keyboard = new Scanner(System.in);
		char letterGrade = 'z';
		String inputString = "";
		String outputString = "";
		
		//Input
		System.out.print("Enter a letter grade: ");
		inputString = (keyboard.next()).toUpperCase();
		letterGrade = inputString.charAt(0); //This is how you get a CHAR. Must be a string and CHAR must be a 0
		
		//Process
		switch(letterGrade) {
		case 'A':
			outputString = "Outstanding!!";
			break;
		case 'B':
			outputString = "Good job";
			break;
		case 'C':
			outputString = "Ok.";
			break;
		default:
			outputString = "You suck.";
			
		}//EOC switch
		
		//Output
		System.out.println(outputString);
		
		keyboard.close();
	}//EOC main
	
}//EOC SwitchCharDemo
