package edu.usc.csce145.chapter05.PurchaseProject;

import java.util.Scanner;

public class PurchaseProject_ProductFrontEnd {

	public static void main(String[] args) {
		
		//CREATING THE FIRST OBJECT
		PurchaseProject_Product p1 = new PurchaseProject_Product();
		System.out.println(p1.getItemName());
		System.out.println(p1.getPrice());
		System.out.println(p1.getQuantity());
		
		//CHANGING p1'S VALUES AFTER PRINTING THEM OUT.
		//The program will print out the values using the default constructor, then assign the values of p1 to the new values we have written here.
		p1.setItemName("Laptop");
		p1.setPrice(899);
		p1.setQuantity(2);
		
		//SETTING VALUES FOR OBJECT 2 USING p1(OBJECT 1)'S VALUES
		PurchaseProject_Product p2 = new PurchaseProject_Product();
		System.out.println("The second object's values are:");
		System.out.println(p2.getItemName());
		System.out.println(p2.getPrice());
		System.out.println(p2.getQuantity());
		
		//SCANNER
		Scanner kb;
		kb = new Scanner(System.in);
		
		//OBJECT 3 VALUES DECLARATION
		System.out.println("Enter the name of the product");
		String name = kb.nextLine();
		System.out.println("Enter product price:");
		double price = kb.nextDouble();
		System.out.println("Enter quantity of product:");
		int quantity = kb.nextInt();
		
		//OBJECT 3 DECLARATION
		PurchaseProject_Product p3 = new PurchaseProject_Product(name, price, quantity);
		
		//PRINTING OUT p3(OBJECT 3)'S VALUES
		System.out.println("The third object's values are:");
		System.out.println(p3.toString());
		
		//COMPARING p2 TO p3
		System.out.println("Are p2 and p3 equal?");
		System.out.println(p2.equals(p3));
		
		//PRINTING METHOD - TOTAL COST
		System.out.println("Total Cost: $" + p3.totalCost());
		
		//PRINTING METHOD - OVERLOAD TOTAL COST
		//Set a new parameter in statement, changing the amount to 2.
		System.out.println("Total Cost: $" + p3.totalCost(2));
		
		//PRINTING METHOD 2 - OVERLOAD TOTAL COST
		//Set new parameters changing the price to $1575 and the amount to 5.
		System.out.println("total Cost: $" + p3.totalCost(1575, 5));
		
		
		kb.close();
	
	}//EOC MAIN METHOD

}//EOC PurchaseProject_ProductFrontEnd
