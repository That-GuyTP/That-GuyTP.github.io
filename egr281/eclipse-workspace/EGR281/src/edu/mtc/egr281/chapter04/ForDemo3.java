package edu.mtc.egr281.chapter04;

public class ForDemo3 {

	public static void main (String[] args) {
		
		//Nested loops require the value of the "OUTER for loop" controls the "Inner for loop"
		for(char c = 'A' ; c < 'Z' ; ++c) {
		
			for(char d ='A' ; d <= c; ++d) {
				System.out.print(d);
			}//EOC INNER for
			System.out.println();
			
		}//EOC OUTER for
		
		//Build down Z to A
		for(char c = 'Z'; c >= 'A'; --c) {
			for(char d = 'A'; d <= c; ++d) {
				System.out.print(d);				
				
			}//EOC INNER 2 for
			
			System.out.println();
		}//EOC OUTER 2 for
		
	}//EOC main
	
}//EOC ForDemo3
