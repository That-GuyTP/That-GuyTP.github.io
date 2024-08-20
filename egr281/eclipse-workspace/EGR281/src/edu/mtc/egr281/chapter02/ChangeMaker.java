package edu.mtc.egr281.chapter02;

import javax.swing.JOptionPane;

public class ChangeMaker {

	//Constants
	public static final int QUARTER = 25;
	public static final int DIME = 10;
	public static final int NICKEL = 5;
	
	public static void main(String[] args) {
		
		//Prepare
		int amount, originalAmount, quarters, dimes, nickels, pennies;
		String amountString;
		
		//Input
		amountString = JOptionPane.showInputDialog(
		"Enter a qhole number from 1 to 99. /n " +
		"I will output a combination of coins, /n " +
		"that equals the amount of change.");	
		
			//String to int
			amount = Integer.parseInt(amountString);
		
		//Process
		originalAmount = amount;
		
		quarters = amount/ QUARTER;
		amount = amount % QUARTER;
		
		dimes = amount / DIME;
		amount = amount % DIME;
		
		nickels = amount / NICKEL;
		nickels = amount % NICKEL;
		
		pennies = amount;
		
		//Output
		JOptionPane.showMessageDialog(null,  originalAmount + quarters + dimes + nickels + pennies);
		
		System.exit(0);
		
	}//EOC main
	
}//EOC class
