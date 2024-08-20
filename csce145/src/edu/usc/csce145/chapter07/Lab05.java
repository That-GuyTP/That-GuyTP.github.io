package edu.usc.csce145.chapter07;

//Thomas Peterson

import java.util.Scanner;

public class Lab05 {

	//Constant
	public static final int SIX = 6;
	
	public static void main(String[] args) {
	
		//Scanner
		Scanner kb;
		kb = new Scanner(System.in);
		
		//Variables
		int userMoney = 0;
		int couponAmount = 0;
		int chocolateBar = 0;
		int freeChocolateBar = 0;
		
		//User Prompt
		System.out.println("Please enter the amount of money you have:");
		userMoney = kb.nextInt();
		if(userMoney <= 0) {
			System.out.println("Sorry, that is an invalid ammount.");
			System.exit(0);
		}//EOC if
		
		//Calculations
		couponAmount = userMoney;
		chocolateBar = userMoney;
		do {
			couponAmount = couponAmount - 6;
			++freeChocolateBar;
		}while (couponAmount >= 6);
		//A quick explination. The do-while is taking the amount of coupons earned, subtracting 6 from the total, adding one free chcoclate bar
		//and continuing that until the loop can no longer take 6 from the remaining coupon amount.
		chocolateBar = (chocolateBar + freeChocolateBar);
		
		//Output
		System.out.println("You can buy " + chocolateBar + " chocolate bars and you have " + couponAmount + " coupons left.");
		
		
		kb.close();
		
	}//EOC main
	
}//EOC Lab05
