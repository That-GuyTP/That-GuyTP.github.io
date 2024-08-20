package Homework00;

//THOMAS PETERSON
//I recommend using "ctrl + shift + numpad Divide" to automatically collapse all methods for a cleaner viewing experience.
//"ctrl + shift + numpad Multiply" can be used to open all methods.

import java.util.Scanner;
import java.lang.Math;

public class VectorCalculator {

	//STATIC SCANNER & VARIABLES
	public static final Scanner kb = new Scanner(System.in);
	public static boolean isDone = false;
	public static int choice = 0;
	public static int size = 0;
	public static double value = 0;
	public static boolean validSize = true;
	
	//MAIN METHOD
	public static void main(String[] args) {
		System.out.println("Hello and welcome to the vector Calculator");
		while(isDone != true) {
			calcOptions();
			switch(choice) {
			case 1:
				addVectors();
				break;
			case 2:
				subVectors();
				break;
			case 3:
				magVector();
				break;
			case 4:
				System.out.println("Quitting the program.... Have a nice day!");
				isDone = true;
				break;
			default:
				System.out.println("Sorry, not a valid choice. Try again.");
			}//EOC SWITCH
		}//EOC WHILE
	}//EOC MAIN METHOD
	
	//CALCULATOR OPTIONS METHOD
	//This method is used to store the calculator options print to help keep things tidy. It also is used to make sure the validSize boolean is reset to true if needed.
	public static void calcOptions() {
		validSize = true;//Reseting the valid size check incase the user inputed an invalid size and was brought back here.
		System.out.println("Please choose between the following options.");
		System.out.println("Enter 1 to add vectors, Enter 2 to subtract vectors, Enter 3 to find the magnitude of a vector, Enter 4 to quit the program.");
		choice = kb.nextInt();
	}//EOC calcOptions
	
	//CHECK SIZE METHOD
	//This method is used to check if the user inputed a size for a vector that is greater than or equal to 1.
	public static void checkSize() {
		if (size < 1) {
			System.out.println("Vectors must be larger than 1. Please try again.");
			validSize = false;
		}else {
			validSize = true;
		}//EOC IF-ELSE
	}//EOC checkSize
	
	//ADDING VECTORS METHOD
	//In this method the user is asked to put the sizes of their 2 vectors which are then checked for a valid size. If valid, the program then prompts and stores values for each vector.
	//After storing the values, each index value of both vectors is added and stored into a third array before being printed back to the user.
	public static void addVectors() {
		while(validSize != false) {
			System.out.println("Please enter the size of vector 1");
			size = kb.nextInt();
			kb.nextLine();
			checkSize();
			if(validSize == false) break; //This statement is being used to stop the rest of the while loop if the size isn't write.
			double[] v1 = new double[size];
			System.out.println("Please enter the size of vector 2");
			size = kb.nextInt();
			kb.nextLine();
			checkSize();
			if(validSize == false) break;
			double[] v2 = new double[size];
			if(v1.length != v2.length) {
				System.out.println("These arrays are not the same size. I cannot add them. Please try again.");
				System.out.println("");
			}else {
				double[] v3 = new double[size];
				for(int i = 0; i < v1.length; i++) {
					System.out.println("Please enter the value of vector 1 position " + i);
					value = kb.nextDouble();
					kb.nextLine();
					v1[i] = value;
				}//EOC FOR
				for(int i = 0; i < v2.length; i++) {
					System.out.println("Please enter the value of vector 2 position " + i);
					value = kb.nextDouble();
					kb.nextLine();
					v2[i] = value;
				}//EOC FOR
				for(int i = 0; i < v3.length; i++) {
					value = (v1[i] + v2[i]);
					v3[i] = value;
				}//EOC FOR
				System.out.println("The product vector is:");
				for(int i = 0; i < v3.length; i++) {
					System.out.println(v3[i]);
				}//EOC FOR
			}//EOC ELSE
		}//EOC WHILE
	}//EOC addVectors
	
	//SUBTRACTING VECTORS METHOD
	//This method follows the same pattern as addition but subtracts the values of v2 from v1 instead of adding them.
	public static void subVectors() {
		while(validSize != false) {
			System.out.println("Please enter the size of vector 1");
			size = kb.nextInt();
			kb.nextLine();
			checkSize();
			if(validSize == false) break;
			double[] v1 = new double[size];
			System.out.println("Please enter the size of vector 2");
			size = kb.nextInt();
			kb.nextLine();
			checkSize();
			if(validSize == false) break;
			double[] v2 = new double[size];
			if(v1.length != v2.length) {
				System.out.println("These arrays are not the same size. I cannot subtract them. Please try again.");
				System.out.println("");
			}else {
				double[] v3 = new double[size];
				for(int i = 0; i < v1.length; i++) {
					System.out.println("Please enter the value of vector 1 position " + i);
					value = kb.nextDouble();
					kb.nextLine();
					v1[i] = value;
				}//EOC FOR
				for(int i = 0; i < v2.length; i++) {
					System.out.println("Please enter the value of vector 2 position " + i);
					value = kb.nextDouble();
					kb.nextLine();
					v2[i] = value;
				}//EOC FOR
				for(int i = 0; i < v3.length; i++) {
					value = (v1[i] - v2[i]);
					v3[i] = value;
				}//EOC FOR
				System.out.println("The difference vector is:");
				for(int i = 0; i < v3.length; i++) {
					System.out.println(v3[i]);
				}//EOC FOR
			}//EOC ELSE
		}//EOC WHILE
	}//EOC subVectors
	
	//MAGNITUDE OF VECTOR METHOD
	//In this method the program asks the user for the size of their vector, checks if it's a valid size, proceeds to prompt and store values 
	//into the array, and then uses the Math java tool to calculate the magnitude.
	public static void magVector() {
		double magnitude = 0; //I've declared this variable inside this method since it's only needed inside this method.
		while(validSize != false) {
			System.out.println("Please enter the size of the vector");
			size = kb.nextInt();
			kb.nextLine();
			checkSize();
			if(validSize == false) break;
			double[] v = new double[size];
			for(int i = 0; i < v.length; i++) {
				System.out.println("Please enter the value of position " + i);
				value = kb.nextDouble();
				kb.nextLine();
				v[i] = value;
			}//EOC FOR
			System.out.println("The magnitude of this vector is: ");
			for(int i = 0; i < v.length; i++) {
				magnitude += (v[i] * v[i]);
			}//EOC FOR
			magnitude = Math.sqrt(magnitude);
			System.out.println(magnitude);
		}//EOC WHILE
	}//EOC magVector
	
}//EOC VectorCalculator
