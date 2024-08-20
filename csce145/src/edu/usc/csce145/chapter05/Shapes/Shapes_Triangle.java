package edu.usc.csce145.chapter05.Shapes;

public class Shapes_Triangle extends Shapes_ShapeBasics {
	
	//NOTES
	//This isn't a child class of Shapes_Rectangle, it's a child of Shapes_ShapeBasics

	//INSTANCE VARIABLES
	private int base;
	
	
	//DEFAULT CONSTRUCTOR
	public Shapes_Triangle() {
		super();
		this.base = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSRUCTOR
	public Shapes_Triangle(int xOffset, int xBase) {
		super(xOffset);
		this.setBase(xBase);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public int getBase() {
		return this.base;
	}//EOC getBase
	
	
	//MUTATOR METHODS
	public void setBase(int xBase) {
		if(xBase >= 0 && xBase % 2 == 1) {
			this.base = xBase;
		}//EOC IF
	}//EOC setBase
	
	
	//DRAW METHODS
	public void drawHere() {
		drawTop();
		drawBase();
	}//EOC drawHere
	
	public void drawBase() {
		this.skipSpaces(this.getOffset());
		for(int i = 0; i < this.base; i++) {
			System.out.print("*");
		}//EOC FOR
	}//EOC drawBase
	
	public void drawTop() {
		
		//VARIABLES
		int center = this.base/2;
		int start = this.getOffset() + center;
		int insideWidth = 1;
		int count = center - 1;
		
		//TOP OF TRIANGLE BEGINS
		this.skipSpaces(start);
		System.out.println("*");
		
		//START OF SIDES OF TRIANGLE
		for(int i = 0; i < count; i++) {
			start--;
			System.out.print("*");
			this.skipSpaces(insideWidth); //Using our skipSpaces method we can skip the spaces determined by our insideWidth variable
			System.out.println("*");
			insideWidth += 2; //We add this so that we can increase the inside width of each line segemnt of the side of the triangle to the correct width with every following line.
		}//EOC FOR
		
	}//EOC drawTop
	
	private void skipSpaces(int spaces) {
		for(int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}//EOC FOR
	}//EOC skipSpaces
	
	
}//EOC Shapes_Triangle
