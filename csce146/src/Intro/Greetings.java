package Intro;

//Thomas Peterson

import java.util.Scanner;

public class Greetings {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);

		//USER INPUT
		System.out.println("Enter two values and I'll add them!");
		int val1 = kb.nextInt();
		int val2 = kb.nextInt();
		
		//CALCULATION & OUTPUT
		int sum = val1 + val2;
		System.out.println("The sum is: " + sum);
		
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC Greetings
