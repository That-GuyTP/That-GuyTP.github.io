package Homework06;

//Thomas Peterson
//Based off the hints and my Shape Interface package from CSCE 145 I thought it best to have different classes (for each shape) that extend the base shape methods so that creating
//individual methods would be more organized/easier to create.

public class Rectangle extends Shape{

	//INSTANCE VARIABLES
	private double length;
	private double width;
	
	//DEFAULT CONS
	public Rectangle() {
		this.length = 1;
		this.width = 1;
	}//EOC DEF CON
	
	//PARAMETER CONS
	public Rectangle(Double xLength, Double xWidth) {
		super();
		this.setLength(xLength);
		this.setWidth(xWidth);
	}//EOC PARA CONS

	//GETTERS AND SETTERS
	public double getLength() {
		return this.length;
	}//EOC getLength

	public void setLength(double xLength) {
		if(xLength <= 0) {
			this.length = 1;
		}else {
			this.length = xLength;
		}//EOC I-E
	}//EOC setLength

	public double getWidth() {
		return this.width;
	}//EOC getWidth

	public void setWidth(double xWidth) {
		if(xWidth <= 0) {
			this.width = 1;
		}else {
			this.width = xWidth;
		}//EOC I-E
	}//EOC setWidth
	
	public String setShapeName() {
		return "Rectangle";
	}
	
	//CALCAREA METHOD
	public double getArea() {
		 return this.length * this.width;
	}//EOC getArea
	
}//EOC Shape
