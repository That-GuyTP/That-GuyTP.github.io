package edu.mtc.egr281.chapter03;

import javax.swing.JOptionPane;

public class Q5 {

	public static void main(String[] args) {
		
		String userInput = JOptionPane.showInputDialog("Enter a numeric grade:");
		double grade = Double.parseDouble(userInput);
		
		if(grade >= 60) {
			JOptionPane.showMessageDialog(null, "Passed");
		} else {
			JOptionPane.showMessageDialog(null, "Failed");
		}//EOC if(grade)
		
		JOptionPane.showMessageDialog(null, "End of program");
		
		System.exit(0);
		
	}//EOC main
	
}//EOC Q5