package edu.mtc.egr281.chapter02;

import javax.swing.JOptionPane;

public class JOptionPaneDemo {

	public static void main(String[] args) {
		
		//Input the number of apples
		String appleString = JOptionPane.showInputDialog("Enter number of apples:");
		
		//Covert class String to primitive int
		int appleCount = Integer.parseInt(appleString);
		
		//Input Oranges
		String orangeString = JOptionPane.showInputDialog("Enter number of oranges:");
		
		//Convert class String to primitive int
		int orangeCount = Integer.parseInt(orangeString);
		
		//Process
		int totalFruit = appleCount + orangeCount;
		
		JOptionPane.showMessageDialog(null, "the total count is" + totalFruit);
		
		System.exit(0);
		
	}//EOC main
	
}//EOC Class
