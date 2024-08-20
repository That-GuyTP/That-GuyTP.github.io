package edu.mtc.egr281.chapter06;

//Thomas Peterson
//Project10
//Current Date: 11-4-2022
//Due Date" 11-3-2022
//Create a game based off the instructions that will allow kids to play it.

//Algorithm Design
//I know it only asks for the project 10 file, I just felt like adding this one as well just for continuity and trust worthiness sake.
//Understand what the project is asking of me
//Need to create a driver to run some of the methods based off of the Die class in order for the game to work
//Create a string that collects the player's name from the "Project10Player" and adds them to a string
//Use the Die class to roll a dice
//Set up the parameters of the game as well as the rules/cases of certain scenarios
//This includes adding requiring the body first, the rest of the parts to win, and not allowing duplicate parts.
//EXTRA: Add a second roll if player gets a roll they needed AKA "snowballing".

public class Project10Driver {

//CONSTANTS
	
	
//FIELDS
	private String name;
	private int currentDiceRoll;
	private int bodyDiceRoll;
	private int engineDiceRoll;
	private int handlesDiceRoll;
	private int lightsDiceRoll;
	private int seatDiceRoll;
	private int tiresDiceRoll;
	private int k = 0;
	
//CONSTRUCTORS
	public Project10Driver(String newName) {
		this.setName(newName);
		this.setDiceRoll(0);
		this.setBodyRoll(0);
		this.setEngineRoll(0);
		this.setHandlebarRoll(0);
		this.setLightsRoll(0);
		this.setTireRoll(0);
	}//EOC constructor default
	
//METHODS
	public String getName() {
		return this.name;
	}//EOC getName
	
	private void setName(String newName) {
		this.name = newName;
	}//EOC setName
	
	public int getDiceRoll() {
		return this.currentDiceRoll;
	}//EOC getDiceRoll
	
	private void setDiceRoll(int newDiceRoll) {
		this.currentDiceRoll = newDiceRoll;
	}//EOC setDiceRoll
	
	public int getBodyRoll() {
		return this.bodyDiceRoll;
	}//EOC getBodyRoll
	
	private void setBodyRoll(int bodyRoll) {
		this.bodyDiceRoll = bodyRoll;
	}//EOC setBodyRoll
	
	public int getEngineRoll() {
		return this.engineDiceRoll;
	}//EOC getEngineRoll
	
	private void setEngineRoll(int engineRoll) {
		this.engineDiceRoll = engineRoll;
	}//EOC setBodyRoll
	
	public int getHandlebarRoll() {
		return this.handlesDiceRoll;
	}//EOC getHandlebarRoll
	
	private void setHandlebarRoll(int handlesRoll) {
		this.handlesDiceRoll = handlesRoll;
	}//EOC setHandlebarRoll
	
	public int getLightsRoll() {
		return this.lightsDiceRoll;
	}//EOC getLightsRoll
	
	private void setLightsRoll(int lightsRoll) {
		this.lightsDiceRoll = lightsRoll;
	}//EOC setLightsRoll
	
	public int getSeatRoll() {
		return this.seatDiceRoll;
	}//EOC getSeatRoll
	
	private void setSeatRoll(int seatRoll) {
		this.seatDiceRoll = seatRoll;
	}//EOC setSeatRoll
	
	public int getTireRoll() {
		return this.tiresDiceRoll;
	}//EOC getTireRoll
	
	private void setTireRoll(int tireRoll) {
		this.tiresDiceRoll = tireRoll;
	}//EOC setTireRoll
	
	//PART CODE
	public String addATVPart(int update){
		String rv = this.getName() + " has rolled a ";
		this.setDiceRoll(update + this.getDiceRoll());
		
	//PART GIVING
		switch(this.getDiceRoll()) {
		case 1:
			rv = rv + "1: You have gotten the body of the ATV, you can now get the rest!";
			
			this.setDiceRoll(0);
			this.setBodyRoll(1);
			break;
		case 2:
			rv = rv + "2: You have gotten the engine of the ATV";
			this.setDiceRoll(0);
			this.setEngineRoll(1);
			break;
		case 3:
			rv = rv + "3: You have gotten the handlebars of the ATV";
			this.setDiceRoll(0);
			this.setHandlebarRoll(1);
			break;
		case 4:
			rv = rv + "4: You have gotten the headlights of the ATV";
			this.setDiceRoll(0);
			this.setLightsRoll(1);
			break;
		case 5:
			rv = rv + "5: You have gotten the seat of the ATV";
			this.setDiceRoll(0);
			this.setSeatRoll(1);
			break;
		case 6:
			rv = rv + "6: You've got one of the tires, remember you need 6 to win!";
			this.setDiceRoll(0);
			this.setTireRoll(k+1);
			break;
		default:
			rv = rv + this.getDiceRoll();
		}//EOC switch
		
	//PART CHECKING
		if(this.getBodyRoll() == 0 && this.getEngineRoll() >= 0 && this.getHandlebarRoll() >= 0 && this.getLightsRoll() >= 0 && this.getSeatRoll() >= 0 && this.getTireRoll() >= 0) {
			rv = "You need to get your body first! (roll a 1)";
			this.setEngineRoll(0);
			this.setHandlebarRoll(0);
			this.setLightsRoll(0);
			this.setSeatRoll(0);
			this.setTireRoll(0);
		}//EOC if body needed 
		
		if(this.getBodyRoll() == 1 && this.getEngineRoll() == 0 && this.getHandlebarRoll() >= 0 && this.getLightsRoll() >= 0 && this.getSeatRoll() >= 0 && this.getTireRoll() >= 0) {
			rv = "You got the body. Remember you need to get your engine second! (roll a 2)";
			this.setHandlebarRoll(0);
			this.setLightsRoll(0);
			this.setSeatRoll(0);
			this.setTireRoll(0);
		}//EOC if engine needed 
		
		if(this.getBodyRoll() > 1) {
			rv = "You already have a body, better luck next roll.";
			this.setBodyRoll(1);
		}//EOC if body
		
		if(this.getEngineRoll() > 1) {
			rv = "You already have an engine, better luck next roll.";
			this.setEngineRoll(1);
		}//EOC of engine
		
		if(this.getHandlebarRoll() > 1) {
			rv = "You already have an handlebar, better luck next roll.";
			this.setHandlebarRoll(1);
		}//EOC of handles
		
		if(this.getLightsRoll() > 1) {
			rv = "You already have lights, better luck next roll.";
			this.setLightsRoll(1);
		}//EOC lights
		
		if(this.getSeatRoll() > 1) {
			rv = "You already have a seat, better luck next roll.";
			this.setSeatRoll(1);;
		}//EPC of seat
		
		if(this.getTireRoll() > 6) {
			rv = "You already have all the tires you need, better luck next roll.";
			this.setTireRoll(6);;
		}//EPC of tires
		
		//ALL PARTS AQUIRED
		if(this.getBodyRoll() == 1 && this.getEngineRoll() == 1 && this.getHandlebarRoll() == 1 && this.getLightsRoll() == 1 && this.getSeatRoll() == 1 && this.getTireRoll() == 1) {
			this.setDiceRoll(7);
		}//EOC winning condition
		
		return rv;
	}//EOC updateATVPart
	
}//EOC Project10Driver
