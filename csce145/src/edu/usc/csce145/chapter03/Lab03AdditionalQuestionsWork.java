package edu.usc.csce145.chapter03;

public class Lab03AdditionalQuestionsWork {

	public static void main(String args[]) {
		
		int number = 3;
		if(number <5){
			System.out.println("A");
		}else if(number < 100 && number > 5){
			System.out.println("B");
		}else if (number < 20){
			System.out.println("C");
		}else {
			System.out.println("D");
		}if(number > 1 || number <=5){
			System.out.println("E");
		}else if(number > 2 && number <=40){
			System.out.println("F");
		}else {
		System.out.println("G");
		}//EOC if chain
			
		System.exit(0);
	}//EOC main
	
}//EOC class
