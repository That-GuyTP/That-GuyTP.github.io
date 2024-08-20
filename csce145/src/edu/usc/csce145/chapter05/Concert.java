package edu.usc.csce145.chapter05;

//Thomas Peterson
//I suggest collapsing all methods in order to view the class better.

public class Concert {

	//Instance Variables
		private String bandName;
		private int capacity;
		private int numberOfTicketsByPhone;
		private double priceByPhone;
		private int numberOfTicketsAtVenue;
		private double priceAtTheVenue;
		
		
	//DEFAULT CONSTRUCTOR
	public Concert() {
		bandName = "Unknown";
		capacity = 15;
		numberOfTicketsByPhone = 1;
		priceByPhone = 5;
		numberOfTicketsAtVenue = 1;
		priceAtTheVenue = 10;
	}//EOC DEFAULT CONSTRUCTOR

	
	//PARAMETER CONSTUCTOR 1
	public Concert(String xBandName, int xCapacity, double xPriceByPhone, double xPriceAtVenue) {
		this.setBandName(xBandName);
		this.setCapacity(xCapacity);
		this.setPriceByPhone(xPriceByPhone);
		this.setPriceAtTheVenue(xPriceAtVenue);
	}//EOC PARAMETER CONSTRUCTOR 1

	
	//PARAMETER CONSTRUCTOR 2
	public Concert(String xBandName, int xCapacity, int xNumberOfTicketsByPhone, int xNumberOfTicketsAtVenue) {
		this.setBandName(xBandName);
		this.setCapacity(xCapacity);
		this.setNumberOfTicketsByPhone(xNumberOfTicketsByPhone);
		this.setNumberOfTicketsAtVenue(xNumberOfTicketsAtVenue);
	}//EOC PARAMETER CONSTRUCTOR 2
	
	
	//ACCESSOR METHODS
	public String getBandName() {
		return this.bandName;
	}//EOC getBandName
	
	public int getCapacity() {
		return this.capacity;
	}//EOC getCpacity
	
	public int getNumOfTicketsByPhone() {
		return this.numberOfTicketsByPhone;
	}//EOC getNumberOfTicketsByPhone
	
	public double getPriceByPhone() {
		return this.priceByPhone;
	}//EOC getPriceByPhone
	
	public int getNumOfTicketsAtVenue() {
		return this.numberOfTicketsAtVenue;
	}//EOC getNumberOfTicketsAtVenue
	
	public double getPriceAtTheVenue() {
		return this.priceAtTheVenue;
	}//EOC getPriceAtTheVenue
	
	
	//MUTATOR METHODS
	public void setBandName(String xBandName) {
		this.bandName = xBandName;
	}//EOC setBandName
	
	public void setCapacity(int xCapacity) {
		if(xCapacity > 0) {
			this.capacity = xCapacity;
		}else {
			System.out.println("Sorry, cannot have a capacity lower than 0.");
		}//EPC IF-ELSE
	}//EOC setCapacity
	
	public void setNumberOfTicketsByPhone(int xNumberOfTicketsByPhone) {
		if(xNumberOfTicketsByPhone > 0) {
			this.numberOfTicketsByPhone = xNumberOfTicketsByPhone;
		}else {
			System.out.println("Sorry, cannot have a ticket sale lower than 0.");
		}//EPC IF-ELSE
	}//EOC setNumberOfTicketsByPhone
	
	public void setPriceByPhone(double xPriceByPhone) {
		if(xPriceByPhone > 0) {
			this.priceByPhone = xPriceByPhone;
		}else {
			System.out.println("Sorry, cannot have a price lower than $0.");
		}//EOC IF-ELSE
	}//EOC setPriceByPhone
	
	public void setNumberOfTicketsAtVenue(int xNumberOfTicketsAtVenue) {
		if(xNumberOfTicketsAtVenue > 0) {
			this.numberOfTicketsAtVenue = xNumberOfTicketsAtVenue;
		}else {
			System.out.println("Sorry, cannot have a ticket sale lower than 0.");
			}//EPC IF-ELSE
	}//EOC setNumberOFTicketsAtVenue
	
	public void setPriceAtTheVenue(double xPriceAtTheVenue) {
		if(xPriceAtTheVenue > 0) {
			this.priceAtTheVenue = xPriceAtTheVenue;
		}else {
			System.out.println("Sorry, cannot have a price lower than $0.");
		}//EOC IF-ELSE
	}//EOC setPriceAtTheVenue
	
	
	//REQUIRED METHODS
	public int totalNumberOfTicketsSold() {
		return this.numberOfTicketsByPhone + this.numberOfTicketsAtVenue;
	}//EOC totalNumberOfTicketsSold

	public int ticketsRemaining() {
		return capacity - totalNumberOfTicketsSold();
	}//EOC ticketsRemaining

	public void buyTicketsAtVenue(int xTicketsAtVenue) {
		//If this is not supposed to return any value, what is its purpose? Would it not already be covered by other methods?
		System.out.println("The tickets remaining are: " + this.ticketsRemaining());
		System.out.println("They cost $" + this.priceAtTheVenue);
	}//EOC buyTicketsAtVenue

	public void buyTicketsByPhone(int xTicketsByPhone) {
		//If this is not supposed to return any value, what is its purpose? Would it not already be covered by other methods?
		System.out.println("The tickets remaining are: " + this.ticketsRemaining());
		System.out.println("They cost $" + this.priceByPhone);
	}//EOC buyTicketsByPhone

	public double totalSales() {
		return (this.priceAtTheVenue * this.getNumOfTicketsAtVenue()) + (this.priceByPhone * this.getNumOfTicketsByPhone());
	}//EOC totalSales
	
}//EOC Concert
