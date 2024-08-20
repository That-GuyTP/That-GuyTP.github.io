package Homework06;

//Thomas Peterson
//This is the class that defines a shape and it's aspects.

public class Shape implements Comparable<Shape>{

	//INSTANCE VARIABLES
	private double area;
	private String shapeName;
	
	//DEF CONS
	public Shape() {
		this.area = 1;
		this.shapeName = "None";
	}//EOC DEF CONS
	
	//PARA CONS
	public Shape(String xShapeName, Double xArea) {
		this.setShapeName(xShapeName);
		this.setArea(xArea);
	}//PARA CONS
	
	//GETTERS AND SETTERS
	public double getArea() {
		return this.area;
	}//EOC getArea

	public void setArea(double xArea) {
		this.area = xArea;
	}//EOC setArea

	public String getShapeName() {
		return shapeName;
	}//EOC getShapeName

	public void setShapeName(String xShapeName) {
		this.shapeName = xShapeName;
	}//EOC setShapeName
	
	//COMPARETO METHOD
	public int compareTo(Shape o) {
		if(o == null) {
			return -1;
		}else if(this.area > o.area) {
			return 1;
		}else if(this.area < o.area) {
			return -1;
		}else {
			//return 0 - Used for comparing just numbers
			return this.shapeName.compareTo(o.shapeName); //Used to compare strings by converting them into their value in binary.
		}//EOC IF-ELSEIF-ELSEIF-ELSE
	}//EOC compareTo


}//EOC Shape
