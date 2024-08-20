package edu.usc.csce145.chapter06.HW07;

//Thomas Peterson

public class CruiseShip extends Ship{

	//INSTANCE VARIABLES
	private int passengerCapacity;
	private int numberOfCrewMembers;
	
	
	//DEFAULT CONSTRUCTOR
	public CruiseShip() {
		passengerCapacity = 1;
		numberOfCrewMembers = 1;
	}//EOC DEFAULT CONSTURCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public CruiseShip(String xShipName, String xLaunchDate, int xPassengerCapacity, int xNumberOfCrewMembers) {
		super(xShipName, xLaunchDate);
		this.passengerCapacity = xPassengerCapacity;
		this.numberOfCrewMembers = xNumberOfCrewMembers;
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public int getPassengerCapacity() {
		return this.passengerCapacity;
	}//EOC getPassengerCapacity
	
	public int getNumberOfCrewMembers() {
		return this.numberOfCrewMembers;
	}//EOC getNumberOfCrewMembers
	
	
	//MUTATOR METHODS
	public void setPassengerCapacity(int xPassengerCapacity) {
		if(xPassengerCapacity >= 0) {
			this.passengerCapacity = xPassengerCapacity;
		}else {
			System.out.println("Sorry, we can't accept a negative value.");
		}//EOC IF-ELSE
	}//EOC setPassengerCapacity
	
	public void setNumberOfCrewMembers(int xNumberOfCrewMembers) {
		if(xNumberOfCrewMembers >= 1) {
			this.numberOfCrewMembers = xNumberOfCrewMembers;
		}else {
			System.out.println("A ship cannot be manned with less than 1 person.");
		}//EOC IF-ELSE
	}//EOC setNumberOfCrewMembers
	
	
	//TOSTRING METHOD
	public String toString() {
		return "Ship name:" + this.getShipName()
			 + "\nLaunch date:" + this.getLaunchDate()
			 + "\nPassenger capacity: " + this.passengerCapacity
			 + "\nNumber of crew members: " + this.numberOfCrewMembers;
	}//EOC toString
	
}//EOC CruiseShip
