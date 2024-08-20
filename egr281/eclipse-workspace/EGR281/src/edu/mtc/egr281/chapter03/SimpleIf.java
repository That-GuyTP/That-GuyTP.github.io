package edu.mtc.egr281.chapter03;

import javax.swing.JOptionPane;

public class SimpleIf {

	public static void main(String[] args) {
		
		String input = JOptionPane.showInputDialog("Enter a number:");
		
		//Calling about the wrapper class To help convert from a string to an int
		//int number = Integer.parseInt(input);
		
		// convert from a String to a double
		double number = Double.parseDouble(input);
		
		if(number > 0) {
			JOptionPane.showMessageDialog(null, "Number is positive");
		}//EOC if(number)
		
		JOptionPane.showMessageDialog(null, "End of program");
		System.exit(0);
		
	}//EOC main
	
}//EOC SimpleIf
