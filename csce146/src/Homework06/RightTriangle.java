package Homework06;

//Thomas Peterson
//Reference "Rectangle" class for notes

public class RightTriangle extends Shape{

	//INSTANCE VARIABLES
	private double base;
	private double height;
	
	//DEF CON
	public RightTriangle() {
		this.base = 1;
		this.height = 1;
	}//EOC DEF CON
	
	//PARA CON
	public RightTriangle(Double xBase, Double xHeight) {
		super();
		this.setBase(xBase);
		this.setHeight(xHeight);
	}//EOC PARA CON

	//GETTERS AND SETTERS
	public double getBase() {
		return this.base;
	}//EOC getBase

	public void setBase(double xBase) {
		if(xBase <= 0) {
			this.base = 1;
		}else {
			this.base = xBase;
		}//EOC I-E
	}//EOC setBase

	public double getHeight() {
		return this.height;
	}//EOC getHeight

	public void setHeight(double xHeight) {
		if(xHeight <= 0) {
			this.height = 1;
		}else {
			this.height = xHeight;
		}//EOC I-E
	}//EOC setHeight
	
	public String setShapeName() {
		return "Circle";
	}//EOC getShapeName
	
	//GETAREA METHOD
	public double getArea() {
		return this.base * this.height * 0.5;
	}//EOC ca;cArea
	
}//EOC RightTriangle
