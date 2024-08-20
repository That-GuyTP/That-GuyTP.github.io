package edu.mtc.egr281.chapter03;

import javax.swing.JOptionPane;

public class SimpleMultibranchIf {

	public static void main(String[] args) {
		
		String userInput = JOptionPane.showInputDialog("Enter a numeric grade:");
		double grade = Double.parseDouble(userInput);
		
		if(grade >= 90) {
			JOptionPane.showMessageDialog(null, "A");
		} else if(grade >= 80) {
			JOptionPane.showMessageDialog(null, "B");
		} else if(grade >= 70) {
			JOptionPane.showMessageDialog(null, "C");
		} else {
			JOptionPane.showMessageDialog(null, "F");
		}//EOC if(grade)
		
		JOptionPane.showMessageDialog(null, "End of program");
		
		System.exit(0);
		
	}//EOC main
	
}//EOC SimpleMultiBranchIf
