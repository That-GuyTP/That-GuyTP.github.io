package edu.usc.csce145.chapter04;

import java.util.Scanner;

public class HW02ExampleStringtoInteger {

	public static void main(String[] args) {
		
		//Scanner
		Scanner kb = new Scanner(System.in);
		
		//String
		String response = "";
		
		//Integer
		int shieldChoice = 0;
		
		//User Prompt
		System.out.println("You have chosen the Shield. Would you like the heavy shield(Type \"Heavy\" or the light shield(Type\"Light\")?");
		response = kb.next();
		
		//If block 1
		if(response.equalsIgnoreCase("Heavy")) {
			shieldChoice = 1;
		}else if(response.equalsIgnoreCase("Light")) {
			shieldChoice = 2;
		}//EOC if-block 1

		
		//If block 2
		if(shieldChoice == 1) {
			System.out.println("You have chosen the heavy shield!");
		}else if(shieldChoice ==2) {
			System.out.println("You have chosen the light shield!");
		}//EOC if-block 2
		
		System.exit(0);
		kb.close();
		
	}//EOC main

}//EOC class
