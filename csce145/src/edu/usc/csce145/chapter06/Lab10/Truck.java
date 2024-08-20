package edu.usc.csce145.chapter06.Lab10;

//Thomas Peterson

public class Truck extends Vehicle{

	//INSTANCE VARIABLES
	private double loadCapacity;
	private double towingCapacity;
	
	
	//DEFAULT CONSTRUCTOR
	public Truck() {
		loadCapacity = 0;
		towingCapacity = 0;
	}//EOC DEFAULT COSNTURCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Truck(String xManufacturerName, int xNumberOfCylinders, String xOwnerName, double xLoadCapacity, double xTowingCapacity) {
		super(xManufacturerName, xNumberOfCylinders, xOwnerName);
		this.setLoadCapacity(xLoadCapacity);
		this.setTowingCapacity(xTowingCapacity);
	}//EOC PARAMETER CONSTURCTOR
	
	
	//ACCESSOR METHODS
	public double getLoadCapacity() {
		return this.loadCapacity;
	}//EOC getLoadCapacity
	
	public double getTowingCapacity() {
		return this.towingCapacity;
	}//EOC getTowingCapacity
	
	
	//MUTATOR METHODS
	public void setLoadCapacity(double xLoadCapacity) {
		if (xLoadCapacity > 0) {
			this.loadCapacity = xLoadCapacity;
		}else {
			System.out.println("Sorry, that isn't a valid number for the load capacity");
		}//EOC IF-ELSE
	}//EOC setLoadCapacity
	
	public void setTowingCapacity(double xTowingCapacity) {
		if (xTowingCapacity > 0) {
			this.towingCapacity = xTowingCapacity;
		}else {
			System.out.println("Sorry, that isn't a valid number for the towing capacity");
		}//EOC IF-ELSE
	}//EOC setTowingCapacity
	
	
	//REQUIRED METHODS
	public boolean equals(Truck truckTest) { 
		return this.getManufacturerName().equalsIgnoreCase(truckTest.getManufacturerName())
			&& this.getNumberOfCylinders() == truckTest.getNumberOfCylinders()
			&& this.getOwnerName().equalsIgnoreCase(truckTest.getManufacturerName())
			&& this.loadCapacity == truckTest.getLoadCapacity()
			&& this.towingCapacity == truckTest.getLoadCapacity();
	}//EOC equals
	
	public String toString() { 
		return "Manufacturer: " + this.getManufacturerName()
			+  "\nNumber of cylinders: " + this.getNumberOfCylinders()
			+  "\nOwner's name: " + this.getOwnerName()
			+  "\nLoad capacity: " + this.loadCapacity
			+  "\nTowing capacity: " + this.towingCapacity;
	}//EOC toString
	
	
}//EOC Truck
