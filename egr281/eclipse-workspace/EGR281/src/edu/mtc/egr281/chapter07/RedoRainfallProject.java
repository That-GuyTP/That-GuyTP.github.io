package edu.mtc.egr281.chapter07;

import java.text.DecimalFormat;
import java.util.Scanner;

public class RedoRainfallProject {

	public static final int AVERAGE = 12;
	public static final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	public static void main(String[] args) {
		
		//Inst Decimal Format
		DecimalFormat df = new DecimalFormat("#.0");
		
		//SCANNER
		Scanner kb = new Scanner(System.in);
		
		//ARRAY
		double[] monthlyRainfall = new double[AVERAGE];
		double total = 0, average;
		
		//Populate the array
		for(int i = 0; i < monthlyRainfall.length; ++i) {
			System.out.print("Enter rainfall amount for " + MONTH[i] + ": ");
			monthlyRainfall[i] = kb.nextDouble();
			total += monthlyRainfall[i];
		}//EOC For loop
		
		average = total/ AVERAGE;
		
		//OUTPUT
		System.out.println("Total rainfall is " + df.format(total));
		System.out.println("Average rainfall is " + df.format(average));
		
		kb.close();
	}//EOC main
	
}//EOC RRP
