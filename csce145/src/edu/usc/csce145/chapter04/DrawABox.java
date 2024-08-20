package edu.usc.csce145.chapter04;

import java.util.Scanner;

public class DrawABox {

	//The for statement "i" will continue until the inside for statement "j" completes it's task. Once the parameters have been satisfied, it will exit the j for loop, increase the value of i by 1 and then continue to
	//complete the inside look uptil the parameter for the "i" statement is satisfied.
	
	public static void main(String args[]) {
	
	//Scanner
	Scanner kb;
	kb = new Scanner(System.in);
	
	//Height Prompt
	System.out.println("enter the height of the box:");
	int height;
	height = kb.nextInt();
	
	//Width Prompt
	System.out.println("Enter the width of the box:");
	int width;
	width = kb.nextInt();
	
	//Box Program
	for(int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			System.out.print("*");	
		}//EOC for j
		System.out.println();
	}//EOC for i
	
	
	kb.close();
	
	}//EOC main
	
}//EOC DrawABox
