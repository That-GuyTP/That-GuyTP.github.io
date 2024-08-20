package edu.usc.csce145.chapter05.Shapes;

public class Shapes_Rectangle extends Shapes_ShapeBasics implements Shapes_RectangleInterface{

	//NOTES
	//This is the child class of ShapeBasics since all rectangles are shapes but not every shape is a rectangle
	
	//INSTANCE VARIABLES
	private int height;
	private int width;
	
	
	//DEFAULT CONSTRUCTOR
	public Shapes_Rectangle() {
		super(); //Calls ShapeBasics' default constructor
		this.height = 0;
		this.width = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Shapes_Rectangle(int xOffset, int xHeight, int xWidth) {
		super(xOffset);
		this.set(xHeight, xWidth);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public int getHeight() {
		return this.height;
	}//EOC getHeight
	
	public int getWidth() {
		return this.width;
	}//EOC getWidth
	
	
	//MUTATOR METHODS
	public void set(int xHeight, int xWidth) {
		if(xHeight > 0 && xWidth > 0) { //Verifying valid inputs
			this.height = xHeight;
			this.width = xWidth;
		}//EOC FOR
	}//EOC set METHODS
	
	
	//DRAW METHODS
	public void drawHere() {
		drawHorizontalLine();
		drawSides();
		drawHorizontalLine();
	}//EOC drawHere
	
	public void drawHorizontalLine() {
		this.skipSpaces(this.getOffset());
		for(int i = 0; i < this.width; i++) {
			System.out.print("*");
		}//EOC FOR
	}//EOC drawHorizontalLine

	public void drawSides() {
		for(int i = 0; i < this.height - 2; i++) {
			this.skipSpaces(this.getOffset());
			System.out.print("*");
			this.skipSpaces(this.width - 2);
			System.out.print("*");
		}//EOC FOR
	}//EOC drawSides
	
	
	//PRIVATE METHOD
	//Private methods, similar to private variables, can only be accessed inside the class they are defined in.
	private void skipSpaces(int spaces) {
		for(int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}//EOC FOR
		
	}//EOC skipSpaces
	
	
	
}//EOC Shapes_Rectangle
