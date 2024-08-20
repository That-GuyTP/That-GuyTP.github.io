package edu.usc.csce145.chapter03;

import java.util.Scanner;

public class Lab03 {

	//Constants
	public static final int RETAIL_PRICE = 99;
	public static final double FIRST_DISCOUNT = .20;
	public static final double SECOND_DISCOUNT = .30;
	public static final double THIRD_DISCOUNT = .40;
	public static final double FOURTH_DISCOUNT = .50;
	//Here I am just merely declaring the price of one software package as well as each level of discounts. The discounts must be decibles in order to calculate
	//the correct amount easily and correctly.
	
	
	public static void main(String[] args) {
		
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
	//Package Calcuations and If-Blocks
		//Prompt and Package Amount Collection
		System.out.println("Welcome to the University of South Carolina IT Department software package ordering program."
							+ " Please enter the amount of software you would like to order:");
		double purchaseAmount;
		purchaseAmount = kb.nextDouble();
		double purchaseTotal = purchaseAmount * RETAIL_PRICE;
		
		//Invalid Input
		if(purchaseAmount < 1 || purchaseAmount % 1 != 0) {
			//What I am doing here is checking to see if the entered value can pass a correct input test. The test is trying to see if the inputed value is either negative (<= 0) 
			//or if it has any decibel points (% 1 != 0). What the second case is doing, is dividing the number by 1 and seeing if there is any remainder. If the remaninder
			//of the equation is not = 0, then that means the inputed value was a decibel.
			System.out.println("Sorry, you have entered a wrong number, please run the program again. Thank you and have a good day.");
			kb.close();
		
		//1-9 Packages
		}else if(purchaseAmount >= 1 || purchaseAmount < 10) {
			System.out.println("Thank you for purchasing a few software pakcages. Your total cost is $" + (int)purchaseTotal + ".");
			
		//10-19 Packages
		}else if(purchaseAmount >= 10 || purchaseAmount < 20) {
			double discount = purchaseTotal * FIRST_DISCOUNT;
			discount = (int)discount; purchaseTotal = (int)purchaseTotal;
			//Here I am converting the variables into integers after having them as doubles for the decibel check. I didn't want to turn them both back into integers as soon as
			//the test was done since I still needed to calculate the discount.
			System.out.println("Thank you for purchasing software packages. Your total cost is $" + purchaseTotal
								+ ". After a discount, the price is $" + (purchaseTotal - discount) + ".");
			//The whole process in one of these else if blocks is calculating the amount of discount applied, turning that number back into a integer,
			//and then subtracting that number from the purchaseTotal to show the final amount of money owed.
			
		//20-49 Packages
		}else if(purchaseAmount >= 20 || purchaseAmount < 50) {
			double discount = purchaseTotal * SECOND_DISCOUNT;
			discount = (int)discount; purchaseTotal = (int)purchaseTotal;
			System.out.println("Thank you for purchasing 20 or more software packages! Your total cost is $" + purchaseTotal
								+ ". After a discount, the price is $" + (purchaseTotal - discount) + ".");
		
		//50-99 Packages
		}else if(purchaseAmount >= 50 || purchaseAmount < 100) {
			double discount = purchaseTotal * THIRD_DISCOUNT;
			discount = (int)discount; purchaseTotal = (int)purchaseTotal;
			System.out.println("Thank you for purchasing 50 or more software packages! Your total cost is $" + purchaseTotal
								+ ". After a discount, the price is $" + (purchaseTotal - discount) + ".");
		//100+ Packages
		}else {
			double discount = purchaseTotal * FOURTH_DISCOUNT;
			discount = (int)discount; purchaseTotal = (int)purchaseTotal;
			System.out.println("Thank you for purchasing over 100 packages! Your total cost is $" + purchaseTotal
								+ ". After the maximium discount, the price is $" + (purchaseTotal - discount) + ".");
		}//EOC if-else
		
		
		kb.close();
		//I can leave my kb.close() outside of the if else blocks since it's outside of the entire chain itself. As a result, no matter what input or if block is ran,
		//the program will always close once it is done.
		
	}//EOC main

}//EOC class
