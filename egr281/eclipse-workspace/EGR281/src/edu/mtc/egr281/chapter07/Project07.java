package edu.mtc.egr281.chapter07;

//Thomas Peterson
//Project07
//Due: 10-13-2022
//Current: 10-13-2022
//Using scanners and loops, I will output a diamond-like image for the user based off the number they imputed.

import java.util.Scanner;

public class Project07 {

	public static void main(String[] args) {
		
		//ALGORITHM
		//Figure out what he wants
		//SET UP AND PROMPT FOR SCANNER
		//SET UP SOME FORM OF A LOOP THAT WILL OUTPUT THE IMAGE CONSISTANTLY
		//OUTPUT IMAGE
		
		//SCANNERS
		Scanner kb = new Scanner(System.in);
		//int diamondSize;
		
		//INPUT
		System.out.print("Please enter the size of your diamond (number): ");
		int diamondSize= kb.nextInt();
		
		//PROCESS & OUTPUT
			//LOOP UP
			for(int i = 1; i <= diamondSize; ++i) {
				for(int j = 0; j < i; ++j) {
					System.out.print("*");
					System.out.print("S");
				}//EOC INNER
				System.out.println();
			}//EOC OUTER
		
			//LOOP DOWN
			for(int i = diamondSize; i >= 0; --i) {
				for(int j = 0; j < i; ++j) {
					System.out.print("*");
					System.out.print("S");
				}//EOC INNER
				System.out.println();	
			}//EOC OUTER
			
		//END
			System.out.println("End of Program");
		kb.close();
	}//EOC main
	
}//EOC Project 07






//TRY 1
		//if("2".equals(diamondSize)) {
			//System.out.println(reverse)
		//}
			
		//switch(diamondSize) {
	
			//System.out.println("The answer to this project");
		//case 5:
			//System.out.println("   *");
			//System.out.println("  *S*");
			//System.out.println(" *S*S*");
			//System.out.println("*S*S*S*");
			//System.out.println("*S*S*S*S*");
			//System.out.println("*S*S*S*");
			//System.out.println(" *S*S*");
			//System.out.println("  *S*");
			//System.out.println("   *");
			//break;
		//case 4:
			//System.out.println("   *");
			//System.out.println("  *S*");
			//System.out.println(" *S*S*");
			//System.out.println("*S*S*S*");
			//System.out.println(" *S*S*");
			//System.out.println("  *S*");
			//System.out.println("   *");
			//break;
		//case 3:
			//System.out.println("  *");
			//System.out.println(" *S*");
			//System.out.println("*S*S*");
			//System.out.println(" *S*");
			//System.out.println("  *");
			//break;
		//case 2:
			//System.out.println(" *");
			//System.out.println("*S*");
			//System.out.println(" *");
			//break;
		//default:
			//System.out.print("Sorry, not a valid number/diamond size. Please input a number above 2 and below 6");
		//}//EOC Switch
		


		//TRY 2
			//if (diamondSize > 1) {
				//char diamondChar = '*';
				//while(diamondSize > 1) {
					//System.out.println("S" + diamondChar);
					//diamondChar + "*S";
					//diamondSize--;
				//}
			//}else {
			//System.out.println("Sorry invalid input. Please input a number above 2");
			//}