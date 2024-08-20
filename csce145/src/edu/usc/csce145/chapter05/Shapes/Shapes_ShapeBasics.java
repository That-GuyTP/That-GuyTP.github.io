package edu.usc.csce145.chapter05.Shapes;

public class Shapes_ShapeBasics implements Shapes_ShapesInterface {

	//NOTES
	//We want to use an "offset" since we don't want to start drawing the shape against the margin. (Left & right space)
	//Using "implements" will add our organizationf from out Shapes_ShapesInterface interface.

	
	//INSTANCE VARIABLES
	private int offset;
	
	
	//DEFAULT CONSTRUCTOR
	public Shapes_ShapeBasics() {
		this.offset = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Shapes_ShapeBasics(int xOffset) {
		this.setOffset(xOffset);
	}//EOC PARAMETER COSNTRUCTOR
	
	
	//ACCESSOR METHODS
	public int getOffset() {
		return this.offset;
	}//EOC get Offset
	
	
	//MUTATOR METHOD
	public void setOffset(int xOffset) {
		if(xOffset >= 0) {
			this.offset = xOffset;
		}//EOC if
	}//EOC setOffset
	
	
	//DRAWAT METHOD
	//Method used to declare where the drawing will begin in terms of more println spaces. (Up & down space)
	public void drawAt(int lineNumber) {
		for(int i = 0; i < lineNumber; i++) {
			System.out.println();
		}//EOC FOR
		drawHere();
	}//EOC drawAt
	
	
	//DRAWHERE METHOD
	//Using offset as a base for how far out from the left side of the console, it will begin to print out the shape as desired.
	public void drawHere() {
		for(int i = 0; i < this.offset; i++) {
			System.out.print(" ");
		}//EOC FOR
			System.out.println("*");
	}//EOC drawHERE
	
	
}//EOC Shapes_ShapeBasics
