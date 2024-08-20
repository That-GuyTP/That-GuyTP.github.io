package Intro;

//Thomas Peterson

//NOTES
/* Finding the .java file. Right click class in package explorer, show in, system explorer.
 * Blue bar is the break point bar. Clicking the bar will add a blue point, allowing you to enter bug mode. This allows you to debug code a LOT easier.
 */

import java.util.Scanner;

public class SelectionSort {

	//CONSTANTS
	public static final int DEF_SIZE = 10;
	
	public static void main(String args[]) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//USER INPUT
		System.out.println("Enter " + DEF_SIZE + " numbers and I'll sort'em");
		int[] a = new int[DEF_SIZE];
		for(int i = a.length-1; i >= 0; i--) { //Stores data from the value 9 to 0 instead of 0 to 9.
			a[i] = kb.nextInt();
		}//EOC FOR
		System.out.println("Test");
		
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC SelectionSort
