package edu.usc.csce145.chapter05;

//Thomas Peterson

public class Coffee {

	//INSTANCE VARIABLES
	private String name;
	private int caffeineContent;
	
	
	//DEFAULT CONSTRUCTOR
	public Coffee() {
		name = "none";
		caffeineContent = 50;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Coffee(String xName, int xCaffeineContent) {
		this.setName(xName);
		this.setCaffeineContent(xCaffeineContent);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public String getName() {
		return this.name;
	}//EOC getName
	
	public int getCaffeineContent() {
		return this.caffeineContent;
	}//EOC getCaffeineContent
	
	
	//MUTATOR METHODS
	public void setName(String xName) {
		this.name = xName;
	}//EOC setName
	
	public void setCaffeineContent(int xCaffeineContent) {
		if(xCaffeineContent < 50 || xCaffeineContent > 300) {
			System.out.println("Sorry, that's not a valid amount of caffeine.");
			this.caffeineContent = 50;
		}else {
			this.caffeineContent = xCaffeineContent;
		}//EOC IF-ELSE
	}//EOC setCaffeineContent
	
	
	//RISKYAMOUNT METHOD
	public double RiskyAmount() {
		return (180.0 / ((getCaffeineContent() / 100.0) * 6.0));
	}//EOC RiskyAmount
	
	
	//EQUALS METHOD
	public boolean equals(Coffee objectInParathesis) {
		return this.name.equalsIgnoreCase(objectInParathesis.getName())
			&& this.caffeineContent == objectInParathesis.getCaffeineContent();
	}//EOC equals

	
	//PRINT METHOD
	public String toString() {
		return "Name: " + this.name
			 + "\nCaffeine Amount: " + this.caffeineContent;
	}//EOC PRINT METHOD
	
}//EOC Coffee
