package edu.mtc.egr281.chapter02;

import java.util.Scanner;

public class EggCounter {

	public static final int DOZEN = 12;
	
	public static void main(String[] args) {
		
		//Declaration
		Scanner kb;
		
		//Instantiation
		kb = new Scanner(System.in);
		
		//Prepare
		int numberOfEggs;
		int numberOfDozens;
		int remainingEggs;
		
		//input
		System.out.print("Enter number of eggs collected: ");
		numberOfEggs = kb.nextInt();
		
		//Process
		numberOfDozens = numberOfEggs / DOZEN;
		remainingEggs = numberOfEggs % DOZEN;
		
		//Output
		System.out.print(numberOfEggs + " eggs is ");
		System.out.print(numberOfDozens + " dozen eggs with ");
		System.out.println(remainingEggs + "left over.");
		
		//Close Input
		kb.close();
		
		
	}//EOC Main
	
}//EOC Class
