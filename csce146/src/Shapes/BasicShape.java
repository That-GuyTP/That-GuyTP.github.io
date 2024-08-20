package Shapes;

//THOMAS PETERSON
//When using the "make getters and setters" make sure to check: parameter variable name, method name/capitalization, & 
//Static methods can call on other static methods.

public class BasicShape implements Shape{

	//INSTANCE VARIABLES
	private int hSpace;
	
	
	//DEFAULT CONSTRUCTOR
	public BasicShape() {
		this.hSpace = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public BasicShape(int xH) {
		this.hSpace = xH;
	}//EOC PARAMETER CONSTRUCTOR

	
	//ACCESSOR METHOD
	public int getHSpace() {
		return hSpace;
	}//EOC gethSpace

	
	//MUTATOR METHODS
	public void setHSpace(int xH) {
		if(xH >= 0 ) {
			this.hSpace = xH;
		}else {
			this.hSpace = 0;
		}//EOC IF-ELSE
	}//EOC sethSpace
	
	
	//OTHER METHODS
	public static void skipSpace(int space) {
		for(int i = 0; i < space; i++) {
			System.out.print(" ");
		}//EOC FOR
	}//EOC skipSpace
	
	public void drawShape() {
		skipSpace(this.hSpace);
		System.out.println("*");
	}//EOC drawShape
	
	public void drawShapeAt(int lineNumber) {
		for(int i = 0; i < lineNumber; i++) {
			System.out.println();
			drawShape();
		}//EOC FOR
	}//EOC drawShapeAt
	
}//EOC BasicShape
