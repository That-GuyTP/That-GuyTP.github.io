package edu.mtc.egr281.chapter02;

//Thomas Peterson
//Project03
//09-15-2022
//09-15-2022
//Using the JOptionPane we will ask a user the number of apples they want and display to them how much money they owe. I will be using int and doubles to declare eggs and calculate the price for the user.

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Project03 {

	public static final int DOZEN = 12;
	public static final double EGG = .35;
	public static final double EGGDOZEN = 4.05;
			
	public static void main(String[] args) {
		
		//Algorithm Design
		//Figure out what is being asked of me in the project file
		//Understand how to use the JOptionPane
		//Get the number of eggs a person wants
		//Calculate how many dozens that is
		//Calculate the remainder of eggs leftover
		//Calculate the total price
		//Output that to the user
		
		//Input Apples
		String eggString = JOptionPane.showInputDialog("Please enter the number of eggs you want to buy:");
		
		//Covert class String to primitive int
		int eggCount = Integer.parseInt(eggString);
		
		//Declare and Int Scanners
		Scanner kb;
				
		kb = new Scanner(System.in);
	
		//Prepare ints
		int numberOfDozens;
		int remainingEggs;
		double totalPrice;
		double remainingEggsCalc;
		double numberOfDozensCalc;
		
		
		//Process Data
		numberOfDozens = eggCount / DOZEN;
		remainingEggs = eggCount % DOZEN;
		remainingEggsCalc = remainingEggs * EGG;
		numberOfDozensCalc = numberOfDozens * EGGDOZEN;
		totalPrice = remainingEggsCalc + numberOfDozensCalc;

		
		//Output Data
		JOptionPane.showMessageDialog(null, "You are buying " + eggCount + " eggs. That is " + numberOfDozens + " dozen(s), which are $4.05 each, and " + remainingEggs + " loose egg(s), which are $.35 cents each, bringing your total too $" + totalPrice );
	
				
		//Close Scanner	
		kb.close();
		
		//Exit
		System.exit(0);
		
	}//EOC main
	
}//EOC Project03
