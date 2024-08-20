package edu.usc.csce145.chapter05.Apples;

//Thomas Peterson

import java.util.Scanner;

public class AppleTester {

	public static void main(String args[]) {
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//VARIABLES
		String type;
		double weight;
		double price;
		
		//OBJECT 1
		Apple o1 = new Apple();
		System.out.println("The first apple object's values are:");
		System.out.println(o1.getType());
		System.out.println(o1.getWeight());
		System.out.println(o1.getPrice());
		
		//OBJECT 2
		System.out.println("Please enter the the type of apple. (Red delicious, golden delicious, gala, or granny smith");
		type = kb.nextLine();
		System.out.println("Please enter the weight of the apple. (Only values 0kg-2kg are accepted. 0 is not an acceptable value.)");
		weight = kb.nextDouble();
		System.out.println("Please enter the price of the apple. (Only values above $0.00 will be accepted)");
		price = kb.nextDouble();
		Apple o2 = new Apple(type, weight, price);
		System.out.println("The values of the second apple are as follows.");
		System.out.println(o2.writeOutput());
		
		
		kb.close();
		
	}//EOC MAIN METHOD
	
}//EOC AppleTester
