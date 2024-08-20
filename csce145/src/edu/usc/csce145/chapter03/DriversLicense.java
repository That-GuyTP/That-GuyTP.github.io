package edu.usc.csce145.chapter03;

import java.util.Scanner;

public class DriversLicense {

	//Constant
	public static final int MIN_AGE = 16;
	
	public static void main(String[] args) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
	
	//Input
		System.out.println("Enter your age(in years):");
		int age;
		age = kb.nextInt();
		
		//Input Validation
			if(age < 0) {
				System.out.println("Sorry you have entered a invalid age. Exiting the program");
			}//EOC if
			
		//Driver's License Eligibility
			if(age >= MIN_AGE) {
				System.out.println("You are eligible to get a driver's license!");
			}else {
				System.out.println("Sorry you are not old enough to get a driver's license yet");
				int waitTime = MIN_AGE - age;
				System.out.println("You must wait " + waitTime + " years until you can get a driver's license");
			}//EOC if else
				

		kb.close();

	}//EOC class

}//EOC main
