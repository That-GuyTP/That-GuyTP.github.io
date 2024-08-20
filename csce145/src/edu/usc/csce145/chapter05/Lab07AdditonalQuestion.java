package edu.usc.csce145.chapter05;

public class Lab07AdditonalQuestion {

	public static void main(String args[]) {
		
		int[] arrayA = new int[10];
		int[] arrayB = new int[10];
		
		for(int i=0; i < arrayA.length; i++) {
			if(arrayA[i] != arrayB[i]) {
				System.out.println("these arrays are not equal.");
				System.exit(0);
			}//EOC IF
		}//EOC FOR I
		System.out.println("These arrays are equal.");
		
	}//EOC MAIN METHOD
	
}//EOC Lab07AdditionalQuestion
