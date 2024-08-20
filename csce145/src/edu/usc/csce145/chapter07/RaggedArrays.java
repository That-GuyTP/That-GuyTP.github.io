package edu.usc.csce145.chapter07;

public class RaggedArrays {

	public static void main(String[] args) {
	//Main is the entry point for the program. The compiler will look through the code for the main method and then begin there.
		
		//ARRAY DECLARATION
		int[][] myArray = {{1, 3}, {1, 3, 9}};
		//In order to create a predetermined 2D array ([][] instead of []) you must include two seperate sets of {}'s with values. 
		//The size is determined by the first {} set.
		
		//A 2D array is an array of arrays.
		/* EX1
		 * x x x x
		 * x x x x
		 * 
		 * EX 2
		 * x x x
		 * x x x
		 * x x x
		 * 
		 * EX3
		 * x
		 * x
		 * x
		 * x
		 * 
		 * All are 2D arrays.
		 */
		
		//ARRAY PRINT
		for(int i = 0; i < myArray.length; i++) {
			for(int j = 0; j < myArray[i].length; j++) {
			//Add the [i] to allow the first {} to not determine the size of the array.
				System.out.print(myArray[i][j] + " ");
			}//EOC J FOR
			System.out.println();
		}//EOC I FOR
		
		
		System.exit(0);

	}//EOC MAIN METHOD

}//EOC RaggedArrays
