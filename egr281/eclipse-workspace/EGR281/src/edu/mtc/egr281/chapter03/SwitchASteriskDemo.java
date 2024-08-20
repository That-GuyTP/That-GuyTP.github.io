package edu.mtc.egr281.chapter03;

import java.util.Scanner;

public class SwitchASteriskDemo {

	public static void main(String[] args) {
		
		//STORE
		Scanner keys = new Scanner(System.in);
		int number;
		
		//INPUT
		System.out.print("Enter a number from 1 to 6: ");
		number = keys.nextInt();
		
		//PROCESS
		switch(number) {
		case 6:
			System.out.print("*");
		case 5:
			System.out.print("*");
		case 4:
			System.out.print("*");
		case 3:
			System.out.print("*");
		case 2:
			System.out.print("*");	
		case 1:
			System.out.print("*");
			break;
		default:
			System.out.println("Number not in range");
		}//EOC switch
		
		
		//OUTPUT
		System.out.println("End of Program");
		
		keys.close();
	}//EOC main
	
}//EOC the fucking title
