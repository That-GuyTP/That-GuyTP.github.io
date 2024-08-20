package edu.mtc.egr281.chapter02;

//Thomas Peterson
//Project 02
//Current Date: 09-10-22
//Due Date: 09-08-22
//This is a program that helps calculate your BMI (Imperial version)
//by using scanners and the System.out and inputStream to ask and receive answers

import java.util.Scanner;

public class Project02 {

	public static final int IMPERIAL_AVERAGE = 703;
	
	public static void main(String[] args) {
		
		//Scanner
		Scanner inputStream;
		
		//Instantiate Scanner
		inputStream = new Scanner(System.in);
		
		//Algorithm design
		//Figure out how to calculate BMI
		//Add static number for imperial calculation
		//Get Height
		//Get Weight
		//Multiply weight * Imperial average
		//Calculate (Weight *Imperial average)/Height_Squared
		//Output BMI
		
		//Store
		int Height, Weight;
		int Height_Squared, BMI, sum;
		
		//Input
		System.out.println("Please enter your weight(lbs)");
		Weight = inputStream.nextInt();
		System.out.println("Please enter your height(in)");
		Height = inputStream.nextInt();
		
		//Process
		Height_Squared = Height * Height;
		sum = Weight * IMPERIAL_AVERAGE;
		BMI = sum / Height_Squared;
		
		//Output
		System.out.println("Your BMI is " + BMI);
		
		//Close Scanner
		inputStream.close();
		
	}//EOC main
	
}//EOC Project02
