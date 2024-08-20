package edu.usc.csce145.chapter05.Apples;

//Thomas Peterson
//I recommend collapsing all folds for an cleaner view of each required method.

public class Apple {

	//INSTANCE VARIABLES
	private String type;
	private double weight;
	private double price;
	
	//DEFAULT CONSTRUCTOR
	public Apple() {
		this.type = "Gala";
		this.weight = 0.5;
		this.price = 0.88;
	}//EOC DEFAULT CONSTRUCTOR
	
	//PARAMETERIZED CONTSTRUCTOR
	public Apple(String xType, double xWeight, double xPrice) {
		this.setType(xType);
		this.setWeight(xWeight);
		this.setPrice(xPrice);
	}//EOC PARAMETERIZED CONSTRUCTOR
	
	//MUTATOR METHODS
	public void setType(String xType) {
		if(xType.equalsIgnoreCase("Red Delicious")) {
			this.type = "Red Delicious";
		}else if(xType.equalsIgnoreCase("Golden Delicious")) {
			this.type = "Golden Delicious";
		}else if(xType.equalsIgnoreCase("Gala")) {
			this.type = "Gala";
		}else if(xType.equalsIgnoreCase("Granny Smith")) {
			this.type = "Granny Smith";
		}else {
			System.out.println("Sorry, that is an invalid choice of apple.");
			this.type = "Null";
		}//EOC IF-ELSE
	}//EOC setType
	
	public void setWeight(double xWeight) {
		if(xWeight <= 0 || xWeight > 2) {
			System.out.println("Sorry, that is an invalid weight.");
			this.weight = 0.00;
		}else {
			this.weight = xWeight;
		}//EOC IF-ELSE
	}//EOC setWeight
	
	public void setPrice(double xPrice) {
		if(xPrice < 0) {
			System.out.println("Sorry, that is an invalid price.");
			this.price = 0.00;
		}else {
			this.price = xPrice;
		}//EOC IF-ELSE
	}//EOC setPrice
	
	//ACCESSOR METHODS
	public String getType() {
		return this.type;
	}//EOC getType
	
	public double getWeight() {
		return this.weight;
	}//EOC getWeight
	
	public double getPrice() {
		return this.price;
	}//EOC getPrice
	
	//WRITEOUTPUT METHOD
	public String writeOutput() {
		return "The type is: " + this.type
			   + "\nThe weight is: " + this.weight + "kg"
			   + "\nThe price is: $" + this.price;
	}//EOC writeOutput
	
}//EOC Apples
