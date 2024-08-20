package Homework06;

//Thomas Peterson
//This is the front end class of the HW06.

import java.util.Scanner;

public class ShapeSorter {

	//CONSTANTS
	static Scanner kb = new Scanner(System.in);
	static LinkedBSTree<Shape> ShapeSorter = new LinkedBSTree<Shape>();
	
	public static void main(String[] args) {
		
		//VARIABLES
		int option = 0;
		boolean quit = false;
		
		//INTRO
		System.out.println("Welcome to the Shape Sorter. In this program you can sort shapes based of their name and area");
		
		//WHILE LOOP
		while(quit != true) {
			options();
			option = kb.nextInt();
			kb.nextLine();
			switch(option) {
			case 1:
				readShapeFileFE();
				break;
			case 2:
				writeShapeFileFE();
				break;
			case 3:
				addShapeFE();
				break;
			case 4:
				removeShapeFE();
				break;
			case 5:
				searchShapeFE();
				break;
			case 6:
				System.out.println("The shape with the largest area is: " + ShapeSorter.findLargestArea());
				break;
			case 7:
				removeGT();
				break;
			case 8:
				printOutOptionsFE();
				break;
			case 9:
				System.out.println("Have a good day!");
				quit = true;
			default:
				System.out.println("Not a valid option. Try again.");
				System.out.println("");
				quit = false;//Just double checking.
			}//EOC SWITCH
			
		}//EOC WHILE
		
	}//EOC MAIN METHOD
	
	public static void options() {
		System.out.println("Please choose between the following options:");
		System.out.println("Enter 1 to read a shape file");
		System.out.println("Enter 2 to write a shape file");
		System.out.println("Enter 3 to add a shape");
		System.out.println("Enter 4 to remove a shape");
		System.out.println("Enter 5 to search for a shape");
		System.out.println("Enter 6 to find the shape with the largest area");
		System.out.println("Enter 7 to remove shapes greater than a value");
		System.out.println("Enter 8 to choose how you would like to have the shapes printed out");
		System.out.println("Enter 9 to quit");
	}//EOC Options
	
	public static void readShapeFileFE() {
		System.out.println("Please enter the name of the file (without extensions or file path");
		String fileName = kb.nextLine();
		ShapeSorter.readShapeFile(fileName);
	}//EOC readShapeFileFE
	
	public static void writeShapeFileFE() {
		System.out.println("Please enter the name of the file you would like to create");
		String text = kb.nextLine();
		ShapeSorter.writeShapeFile(text);
	}//EOC writeShapeFileFE
	
	public static void addShapeFE() {
		System.out.println("Please enter the type of shape (Rectangle, Circle, or Right Triangle");
		String shapeName = kb.nextLine();
		kb.nextLine();
		if(shapeName.equalsIgnoreCase("Rectangle")) {
			System.out.println("Enter the length of the Rectangle");
			double length = kb.nextDouble();
			kb.nextLine();
			System.out.println("Enter the width of the Rectangle");
			double width = kb.nextDouble();
			kb.nextLine();
			Rectangle xRectangle = new Rectangle(length, width);
			ShapeSorter.add(xRectangle);
		}else if (shapeName.equalsIgnoreCase("Circle")) {
			System.out.println("Enter the radius of the Circle");
			double radius = kb.nextDouble();
			kb.nextLine();
			Circle xCircle = new Circle(radius);
			ShapeSorter.add(xCircle);
		}else if (shapeName.equalsIgnoreCase("Right Triangle")) {
			System.out.println("Enter the base of the Right Triangle");
			Double base = kb.nextDouble();
			kb.nextLine();
			System.out.println("Enter the height of the Right Triangle");
			Double height = kb.nextDouble();
			kb.nextLine();
			RightTriangle xRT = new RightTriangle(base, height);
			ShapeSorter.add(xRT);
		}else {
			System.out.println("Sorry, that wasn't a valid option.");
		}//EOC IF-EI-EI-E
	}//EOC addShapeFE
	
	public static void removeShapeFE() {
		System.out.println("Enter the type of shape (Rectangle, Circle, or Right Triangle)");
		String shapeName = kb.nextLine();
		System.out.println("Enter the area of that shape");
		double area = kb.nextDouble();
		kb.nextLine();
		Shape shape = new Shape(shapeName, area);
		ShapeSorter.remove(shape);
	}//EOC removeShapeFE
	
	public static void searchShapeFE() {
		System.out.println("Enter the type of shape (Rectangle, Circle, or Right Triangle)");
		String shapeName = kb.nextLine();
		System.out.println("Enter the area of that shape");
		double area = kb.nextDouble();
		kb.nextLine();
		Shape shape = new Shape(shapeName, area);
		ShapeSorter.search(shape);
	}//EOC searchShapeFE
	
	public static void removeGT() {
		System.out.println("Enter the type of shape (Rectangle, Circle, or Right Triangle)");
		String shapeName = kb.nextLine();
		System.out.println("Enter the area of that shape");
		double area = kb.nextDouble();
		kb.nextLine();
		Shape shape = new Shape(shapeName, area);
		System.out.println("Looking to delete any " + shapeName + "s with an area greater than " + area);
		ShapeSorter.removeShapesGreaterThan(shape);
	}//EOC removeGT
	
	public static void printOutOptionsFE() {
		System.out.println("Enter an option from the following choices");
		System.out.println("Enter 1 to print in Pre-Order. Enter 2 to print In-Order. Enter 3 to print in Post-Order");
		int option = kb.nextInt();
		kb.nextLine();
		if(option == 1) {
			ShapeSorter.printPreorder();
		}else if(option == 2) {
			ShapeSorter.printInorder();
		}else if(option == 3 ) {
			ShapeSorter.printPostorder();
		}else {
			System.out.println("Sorry, that isn't a valid option.");
		}//EOC I-EI-EI-E
	}//EOC printOutOptionsFE
	
}//EOC ShapeSorter
