package edu.usc.csce145.chapter06.HW07;

//Thomas Peterson

public class CargoShip extends Ship{

	//INSTANCE VARIABLES
	private double tonnage;
	private double maxSpeed;
	
	
	//DEFAULT CONSTRUCTOR
	public CargoShip() {
		tonnage = 0;
		maxSpeed = 1;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public CargoShip(String xShipName, String xLaunchDate, double xTonnage, double xMaxSpeed) {
		super(xShipName, xLaunchDate);
		this.tonnage = xTonnage;
		this.maxSpeed = xMaxSpeed;
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public double getTonnage() {
		return this.tonnage;
	}//EOC getTonnage
	
	public double getMaxSpeed() {
		return this.maxSpeed;
	}//EOC getMaxSpeed
	
	
	//MUTATOR METHODS
	public void setTonnage(double xTonnage) {
		this.tonnage = xTonnage;
	}//EOC setTonnage
	
	public void setMaxSpeed(double xMaxSpeed) {
		this.maxSpeed = xMaxSpeed;
	}//EOC setMaxSpeed
	
	
	//TOSTRING METHOD
	public String toString() {
		return "Ship name: "  + this.getShipName()
			 + "\nLaunch date: " + this.getLaunchDate()
			 + "\nTonnage: " + this.tonnage + " DWT"
			 + "\nMax speed: " + this.maxSpeed + " mph";
	}//EOC toString
	
}//EOC CargoShip
