package edu.usc.csce145.chapter02;

import java.util.Scanner;

public class CylinderVolume {

	//Declaring Constant
	public static final double PI = 3.14;
	//Use all caps for constants since it is the general notation of how to write them.
	
	public static void main (String args[]) {
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		
		//Output 1
		System.out.println("Enter the radius (in inches) of the cylinder:");
		
		//Input 1
		double radius = kb.nextDouble();
		//use.nextDouble to read next user input as a double.
		
		//Output 2
		System.out.println("Enter the height(in inches) of the cylinder:");
		
		//Input 2
		double height = kb.nextDouble();
		
		//Answer Output 
		double volume = PI * Math.pow(radius, 2) * height;
		System.out.println("Vlume of the cylinder = " + volume +" cubic inches");
		//declaring the equation/caculation, calculating the value, and then outputing it to the user.
		//The command Math.pow is used to multiply a value or integer and multiply it by whatever the next value is (either
		//number or another integer.
		
		
		//Scanner Close
		kb.close();
		
	}//EOC main
	
}//EOC class
