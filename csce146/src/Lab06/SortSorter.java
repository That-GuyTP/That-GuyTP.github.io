package Lab06;

//THOMAS PETERSON

import java.util.Scanner;

public class SortSorter {

	public static final int MAX_SIZE = 100;
	
	public static void main(String[] args) {
		
		//VARIABLES & SCANNER
		Scanner kb = new Scanner(System.in);
		String word = "";
		String ans = "";
		Boolean quit = false;
		Boolean quitInput = false;
		SortSorterBE SS = new SortSorterBE(MAX_SIZE);

		//GREETING & LOOP START
		System.out.println("Welcome to the SortSorter. "
						 + "\nIn this program you can insert a list of words and they will be sorted by smallest to largest amount of \"sorts\" in the word.");
		while(quit != true) {
			//INPUT WHILE LOOP
			quitInput = false;//RESETING QUITINPUT
			System.out.println("Please enter a list of words you would like to be SortSorted (Enter \"quit\" when you are done entering words):");
			while(quitInput != true) {
				word = kb.nextLine();
				if(word.equalsIgnoreCase("quit")) {
					quitInput = true;
					break;
				}else {
					SS.addString(word);
				}//EOC IF-ELSE
			}//EOC WHILE
			
			//VALID INPUTS CHECK
			if(SS.getSize() > 0) {
				System.out.println("");
				SS.mergeSort(0, SS.getSize() - 1);
				System.out.println("Sorted by Sort List:");
				SS.print();
				System.out.println("");
			}else {
				System.out.println("Sorry, you didn't input any strings.");
				System.out.println("");
			}//EOC IF-ELSE
		
			//CONTINUE CHECK
			System.out.println("Would you like to sort another list? (Yes/No)");
			ans = kb.nextLine();
			if(ans.equalsIgnoreCase("No")) {
				quit = true;
				break;
			}else {
				continue;
			}//EOC IF-ELSE
		}//EOC WHILE
		
		System.out.println("Have a good day!");
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC SortSorter
