package edu.mtc.egr281.chapter03;

//Thomas Peterson
//Project04
//9-22-2022
//9-22-2022
//This is a program designed to allow users to input data (rainfall inches) for a year and will output them the total and average

import java.util.Scanner;
import java.text.DecimalFormat;

public class Project04 {

	public static final double YEAR = 12;
	
	public static void main(String[] args) {
		
		//Algorithm Design
		//Figure out what is being asked of me in the project file
		//Decide how I'm going to gather the amount of rainfall for each month
		//Use a scanner and double's to gather the inputs of rainfall in decimals
		//Use that to calculate the average rainfall with a constant
		//Use the new DecimalFormat code to limit the output to a single decimal point
		//Output average rainfall
		//Hopefully get an A
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		double month_1;
		double month_2;
		double month_3;
		double month_4;
		double month_5;
		double month_6;
		double month_7;
		double month_8;
		double month_9;
		double month_10;
		double month_11;
		double month_12;
		double TotalRainfall;
		double AverageRainfall;
		
		//DECIMAL FORMAT
		DecimalFormat Project04Format = new
		DecimalFormat("#00.0");

		//INPUT
		System.out.print("Please enter the amount of rainfall(inches) for January:");
		month_1 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for Feburary:");
		month_2 = kb.nextDouble();		
		System.out.print("Please enter the amount of rainfall(inches) for March:");
		month_3 = kb.nextDouble();		
		System.out.print("Please enter the amount of rainfall(inches) for April:");
		month_4 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for May:");
		month_5 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for June:");
		month_6 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for July:");
		month_7 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for August:");
		month_8 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for September:");
		month_9 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for October:");
		month_10 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for November:");
		month_11 = kb.nextDouble();
		System.out.print("Please enter the amount of rainfall(inches) for December:");
		month_12 = kb.nextDouble();
		
		//PROCESS
		TotalRainfall = month_1 + month_2 + month_3 + month_4 + month_5 + month_6 + month_7 + month_8 + month_9 + month_10 + month_11 + month_12;
		AverageRainfall = TotalRainfall / YEAR;	
	
		//OUTPUT
		System.out.println("The total amount of Rainfall for this year is: " + Project04Format.format(TotalRainfall) + ". The average amount of rainfall for this year is: " + Project04Format.format(AverageRainfall));
		System.out.println("End of Program");
		
		//CLOSE
		kb.close();
		
	}//EOC main
	
}//EOC project04
