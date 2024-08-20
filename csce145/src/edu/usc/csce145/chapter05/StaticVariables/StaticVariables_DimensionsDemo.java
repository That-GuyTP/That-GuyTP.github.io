package edu.usc.csce145.chapter05.StaticVariables;

import java.util.Scanner;

public class StaticVariables_DimensionsDemo {

	
	public static void main(String[] args) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//CONVERTER
		System.out.println("Enter a value in feet:");
		double feet = kb.nextInt();
		System.out.println(feet + " feet = " + StaticVariables_DimensionConverter.FeetToInches(feet) + " inches"); 
		//Since the method in the other class is public and static, we do not need to create an object to use the method in another class.
		
		System.out.println("Enter a value in inches:");
		double inches = kb.nextInt();
		System.out.println(inches + " inches = " + StaticVariables_DimensionConverter.InchesToFeet(inches) + "feet");
		
		
		kb.close();
		
	}//EOC MAIN METHOD

}//EOC StaticVariables_DimensionsDemo
