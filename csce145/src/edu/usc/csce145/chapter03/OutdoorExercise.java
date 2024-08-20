package edu.usc.csce145.chapter03;

import java.util.Scanner;

public class OutdoorExercise {

	public static void main(String[] args) {
		
	//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
	//Input
		System.out.println("Enter the outside temperature (in Fahrenheit):");
		int temperature = kb.nextInt();
		
	//Eval & Output
		/* Temperature Ranges (F):
		 * -144-134 is the acceptable range
		 * 40-60 cold
		 * 85-96 hot
		 * 65-70 ideal
		 */
		
		//Else-If
		if(temperature < -144 || temperature > 134 ) {
			System.out.println("Invalid temperature for planet Earth");
			kb.close();
		}else if(temperature >= 40 && temperature < 61) {
			System.out.println("Temperature is cold");
		}else if(temperature >= 65 && temperature < 70){
			System.out.println("Temperature is just right");
		}else if(temperature >= 70 && temperature <96) {
			System.out.println("Temperature is getting pretty hot!");
		}else {
			System.out.println("That temperature is one nobody likes!");
		}//EOC else-if
		

		kb.close();
		
	}//EOC main

}//EOC class
