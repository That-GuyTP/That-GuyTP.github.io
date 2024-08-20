package edu.usc.csce145.chapter06.HW07;

//Thomas Peterson

public class Ship {

	//INSTANCE VARIABLES
	private String shipName;
	private String launchDate;
	
	
	//DEFAULT CONSTRUCTOR
	public Ship() {
		this.shipName = "Unknown";
		this.launchDate = "MM/DD/YYYY";
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Ship(String xShipName, String xLaunchDate) {
		this.shipName = xShipName;
		this.launchDate = xLaunchDate;
	}//EOC PARAMETER CONSTRUCTOR
	
	
	//ACCESSOR METHODS
	public String getShipName() {
		return this.shipName;
	}//EOC getShipName
	
	public String getLaunchDate() {
		return this.launchDate;
	}//EOC getLaunchDate
	
	
	//MUTATOR METHODS
	public void setShipName(String xShipName) {
		this.shipName = xShipName;
	}//EOC setShipName
	
	public void setLaunchDate(String xLaunchDate) {
		int shipYear = xLaunchDate.charAt(6-9);
		if(shipYear >= 1990 | shipYear <= 2019) {
			this.launchDate = xLaunchDate;
		}else {
			System.out.println("Sorry only ships launched between 1990-2019 are allowed.");
		}//EOC IF-ELSE
	}//EOC setLaunchDate
	
	
	//TOSTRING METHOD
	public String toString() {
		return "Ship name: " + this.getShipName()
			+  "Launch date: " + this.getLaunchDate();
	}//EOC TOSTRING
	
}//EOC Ship
