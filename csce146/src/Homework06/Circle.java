package Homework06;

//Thomas Peterson
//Reference "Rectangle" class for notes.

public class Circle extends Shape{

	//INSTANCE VARIABLES
	private double radius;
	
	//DEF CON
	public Circle() {
		super();
		this.radius = 1;
	}//EOC DEF CON
	
	//PARA CON
	public Circle(Double xRadius) {
		this.setRadius(xRadius);
	}//EOC PARA CON

	//GETTERS AND SETTERS
	public double getRadius() {
		return this.radius;
	}//EOC getRadius

	public void setRadius(double xRadius) {
		if(xRadius <= 0) {
			this.radius = 1;
		}else {
			this.radius = xRadius;
		}//EOC I-E
	}//EOC setRadius
	
	public String setShapeName() {
		return "Circle";
	}
	
	//CALCAREA METHOD
	public double getArea() {
		return Math.PI * this.radius * this.radius;
	}//EOC calcArea
	
}
