package Lab00;

/*
 * Written by THOMAS PETERSON
 */

//Packages
import java.util.Scanner;
import java.util.Random;

//Do not alter-----------------------------------------------
public class BubbleSorter {
//-----------------------------------------------------------
	
	//CONSTANTS
	public static final int ARRAY_SIZE = 10;
	
//Do not alter-----------------------------------------------
	public static void main(String[] args) {
//-----------------------------------------------------------
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//ARRAY COLLECTION
		System.out.println("Enter " + ARRAY_SIZE + " numbers and I'll sort them");
		int[] array = new int[ARRAY_SIZE];
		for(int i = 0; i < array.length; i++) {
			System.out.println("Enter value " + i);
			array[i] = kb.nextInt();
		}//EOC FOR
		
		//BUBBLE SORT
		boolean hasSwapped = true;
		while(hasSwapped) {
			hasSwapped = false;
			for(int i = 0; i < array.length -1; i++) {
				if(array[i] > array[i + 1]) {
					//SWAP
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					hasSwapped = true;	
				}//EOC IF
			}//EOC FOR
		}//EOC WHILE
		
		//ARRAY PRINT
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}//EOC FOR
	
		
		kb.close();
		
	}//Do not alter //EOC MAIN METHOD

}//Do not alter //EOC BubbleSorter
