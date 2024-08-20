package edu.usc.csce145.chapter06.Lab10;

//Thomas Peterson

public class Vehicle {

	//INSTANCE VARIABLES
	private String manufacturerName;
	private int numberOfCylinders;
	private String ownerName;
	
	
	//DEFAULT CONSTRUCTOR
	public Vehicle() {
		manufacturerName = "Unknown";
		numberOfCylinders = 1;
		ownerName = "Unknown";
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Vehicle(String xManufacturerName, int xNumberOfCylinders, String xOwnerName) {
		this.setManufacturerName(xManufacturerName);
		this.setNumberOfCylinders(xNumberOfCylinders);
		this.setOwnerName(xOwnerName);
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public String getManufacturerName() {
		return this.manufacturerName;
	}//EOC getManufacturerName
	
	public int getNumberOfCylinders() {
		return this.numberOfCylinders;
	}//EOC getNumberOfCylinders
	
	public String getOwnerName() {
		return this.ownerName;
	}//EOC getOwnerName
	
	
	//MUTATOR METHODS
	public void setManufacturerName(String xManufacturerName) {
		this.manufacturerName = xManufacturerName;
	}//EOC setManufacturerName
	
	public void setNumberOfCylinders(int xNumberOfCylinders) {
		if(xNumberOfCylinders >= 1) {
			this.numberOfCylinders = xNumberOfCylinders;
		}else {
			System.out.println("Sorry, that is an invalid amount of cylinders");
		}//EOC IF-ELSE
	}//EOC setNumberOfCylinders
	
	public void setOwnerName(String xOwnerName) {
		this.ownerName = xOwnerName;
	}//EOC setOwnerName
	
	
	//REQUIRED METHODS
	public boolean equals(Vehicle objectInParathesis) {
		return this.manufacturerName.equalsIgnoreCase(objectInParathesis.getManufacturerName())
			&& this.numberOfCylinders == objectInParathesis.getNumberOfCylinders()
			&& this.ownerName.equalsIgnoreCase(objectInParathesis.getOwnerName());
	}//EOC equals
	
	public String toString() {
		return "Manufacturer: " + this.manufacturerName
			 + "\nNumber of cylinders: " + this.numberOfCylinders
			 + "\nOwner's name: " + this.ownerName;
	}//EOC toString
	
}//EOC Vehicle
