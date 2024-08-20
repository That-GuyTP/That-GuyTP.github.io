package edu.usc.csce145.chapter06.Lab10;

//Thomas Peterson

public class Car extends Vehicle{

	//INSTANCE VARIABLES
	private double gasMileage;
	private int numberOfPassengers;
	
	
	//DEFAULT CONSTRUCTOR
	public Car() {
		gasMileage = 0.00;
		numberOfPassengers = 0;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMTER CONSTURCTOR
	public Car(String xManufacturerName, int xNumberOfCylinders, String xOwnerName, double xGasMileage, int xNumberOfPassengers) {
		super(xManufacturerName, xNumberOfCylinders, xOwnerName);
		this.setGasMileage(xGasMileage);
		this.setNumberOfPassengers(xNumberOfPassengers);
	}// EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public double getGasMileage() {
		return this.gasMileage;
	}//EOC getGasMileage
	
	public int getNumberOfPassengers() {
		return this.numberOfPassengers;
	}//EOC getNumberOfPassengers
	
	
	//MUTATOR METHODS
	public void setGasMileage(double xGasMileage) {
		if(xGasMileage > 0.00) {
			this.gasMileage = xGasMileage;
		}else {
			System.out.println("Sorry, that isn't a valid number for gas mileage");
		}//EOC IF-ELSE
	}// setGasMileage
	
	public void setNumberOfPassengers(double xNumberOfPassengers) {
		if(xNumberOfPassengers >= 0) {
			this.gasMileage = xNumberOfPassengers;
		}else {
			System.out.println("Sorry, that isn't a valid amount of passengers");
		}//EOC IF-ELSE
	}// setGasMileage
	
	
	//REQUIRED METHODS
	public boolean equals(Car carTest) { 
		return this.getManufacturerName().equalsIgnoreCase(carTest.getManufacturerName())
			&& this.getNumberOfCylinders() == carTest.getNumberOfCylinders()
			&& this.getOwnerName().equalsIgnoreCase(carTest.getManufacturerName())
			&& this.gasMileage == carTest.getGasMileage()
			&& this.numberOfPassengers == carTest.getNumberOfPassengers();
	}//EOC equals
	
	public String toString() { 
		return "Manufacturer: " + this.getManufacturerName()
			+  "\nNumber of cylinders: " + this.getNumberOfCylinders()
			+  "\nOwner's name: " + this.getOwnerName()
			+  "\nGas mileage: " + this.gasMileage
			+  "\nNumber of passengers: " + this.numberOfPassengers;
	}//EOC toString
	
	
}//EOC Car
