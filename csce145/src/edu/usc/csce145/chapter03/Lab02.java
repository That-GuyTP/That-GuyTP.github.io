package edu.usc.csce145.chapter03;

//Thomas Peterson

import java.util.Scanner;

public class Lab02 {

	public static final double GRAVITY = 9.8;
	//Here is my gravity constant I created. It will be needed later for calculations.

	public static void main(String[] args) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
	//Input
		System.out.println("Welcome to the distance and velocity calculator. Please enter the following values one at a time:");
		//Overall I'm using doubles to ensure that the output shown is in decimal format.
		
		//Initial Velocity	
		System.out.println("Initial velocity at point A (meters per second):");
		double u;
		u = kb.nextDouble();
		
		//Time
		System.out.println("Time taken to reach point B (seconds):");
		double time;
		time = kb.nextDouble();
		
		//Calculations
		double v;
		v = u + (GRAVITY * time);
		//This is the calculation of the final velocity. I am using parenthesis to separate the at calculation.
		//I also added a "+" to ensure that the calculation is added to u.
		
		double S;
		S = (u * time) + (0.5 * GRAVITY * (time * time));
		//I am calculating the distance traveled. I added parenthesis to separate the two calculations. I ensured that in 1/2at^2
		//the time is in its own separate parenthesis to make sure they are multiplied correctly.
		
	//Output
		System.out.println("The final velocity of the ball = " + v + " m/s");
		System.out.println("The distance traveld by the ball = " + S + " meters");

		
		kb.close();
		
	}//EOC main

}//EOC class
