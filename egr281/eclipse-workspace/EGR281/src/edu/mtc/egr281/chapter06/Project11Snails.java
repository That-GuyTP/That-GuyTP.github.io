package edu.mtc.egr281.chapter06;

//Thomas Peterson
//Project11
//Current Date: 11-11-2022
//Due Date: 11-10-22
//To create a snail racing game using object orientated programming methods.

//ALGORITHM DESIGN
//Set up string and boolean 
//Display

//-color : String
//-currentSpace : int
//+getCurrentSpace() : int
//-setCurrentSpace(int) : void
//+incrementCurrentSpace() : void
//+getColor() : String
//-setColor(String) : void

public class Project11Snails {

//Fields
	private String color;
	private int currentSpace;
	private int currentDiceRoll;
	private int orangeDiceRoll;
	private int greenDiceRoll;
	private int pinkDiceRoll;
	private int redDiceRoll;
	private int blueDiceRoll;
	private int yellowDiceRoll;
	
	//private String[][] snailGameBoard = { {"Orange |", "Orange |", "Orange |", "Orange |", "Orange |", "Orange |", "Orange |", "Orange |", "Orange |"},
	//									  {"Green |", "Green |", "Green |", "Green |", "Green |", "Green |", "Green |", "Green |", "Green |"},
	//									  {"Pink |", "Pink |", "Pink |", "Pink |", "Pink |", "Pink |", "Pink |", "Pink |", "Pink |"},
	//									  {"Red |", "Red |", "Red |", "Red |", "Red |", "Red |", "Red |", "Red |", "Red |"},
	//									  {"Blue |", "Blue |", "Blue |", "Blue |", "Blue |", "Blue |", "Blue |", "Blue |", "Blue |"},
	//									  {"Yellow |", "Yellow |", "Yellow |", "Yellow |", "Yellow |", "Yellow |", "Yellow |", "Yellow |", "Yellow |"} };
	//
	//private boolean[][] boardCurrentSpace = { {false, false, false, false, false, false, false, false, false, },
	//										{false, false, false, false, false, false, false, false, false, },
	//										{false, false, false, false, false, false, false, false, false, },
	//										{false, false, false, false, false, false, false, false, false, },
	//										{false, false, false, false, false, false, false, false, false, },
	//										{false, false, false, false, false, false, false, false, false, } };
	
//Constants
	
	
//Constructors
	public Project11Snails(String newColor) {
		this.setColor(newColor);
		this.setCurrentSpace(0);
		this.setDiceRoll(0);
		this.setOrangeDiceRoll(0);
		this.setGreenDiceRoll(0);
		this.setPinkDiceRoll(0);
		this.setRedDiceRoll(0);
		this.setBlueDiceRoll(0);
		this.setYellowDiceRoll(0);
	}//EOC constructor default
	
	
//Methods
	public int getCurrentSpace() {
		return this.currentSpace;
	}//EOC getCurrentSpace
	
	private void setCurrentSpace(int newCurrentSpace) {
		this.currentSpace = newCurrentSpace;
	}//EOC setCurrentSpace
	
	public void incrementCurrentSpace() {
		this.setCurrentSpace(+1);
	}//EOC incrementCurrentSpace
	
	public String getColor() {
		return this.color;
	}//EOC getColor
	
	private void setColor(String newColor) {
		this.color = newColor;
	}//EOC setColor
	
	public int getDiceRoll() {
		return this.currentDiceRoll;
	}//EOC getDiceRoll
	
	private void setDiceRoll(int newDiceRoll) {
		this.currentDiceRoll = newDiceRoll;
	}//EOC setDiceRoll
	
	public int getOrangeDiceRoll() {
		return this.orangeDiceRoll;
	}//EOC getOrangeDiceRoll
	
	private void setOrangeDiceRoll(int orangeRoll) {
		this.orangeDiceRoll = orangeRoll;
	}//EOC setOrangeDiceRoll
	
	public int getGreenDiceRoll() {
		return this.greenDiceRoll;
	}//EOC getGreenDiceRoll
	
	private void setGreenDiceRoll(int greenRoll) {
		this.greenDiceRoll = greenRoll;
	}//EOC setGreenDiceRoll
	
	public int getPinkDiceRoll() {
		return this.pinkDiceRoll;
	}//EOC getPinkDiceRoll
	
	private void setPinkDiceRoll(int pinkRoll) {
		this.pinkDiceRoll = pinkRoll;
	}//EOC setPinkDiceRoll
	
	public int getRedDiceRoll() {
		return this.redDiceRoll;
	}//EOC getRedDiceRoll
	
	private void setRedDiceRoll(int redRoll) {
		this.redDiceRoll = redRoll;
	}//EOC setRedDiceRoll
	
	public int getBlueDiceRoll() {
		return this.blueDiceRoll;
	}//EOC getBlueDiceRoll
	
	private void setBlueDiceRoll(int blueRoll) {
		this.redDiceRoll = blueRoll;
	}//EOC setBlueDiceRoll
	
	public int getYellowDiceRoll() {
		return this.yellowDiceRoll;
	}//EOC getYellowDiceRoll
	
	private void setYellowDiceRoll(int yellowRoll) {
		this.yellowDiceRoll = yellowRoll;
	}//EOC setYellowDiceRoll
	
	
	//PLACEMENT UPDATE
	public String roll(int update) {
		String rv = "The dice is being rolled...";
		this.setDiceRoll(update + this.getDiceRoll());
		
	//ROLL VALUES EQUAL TO COLOR
		switch(this.getDiceRoll()) {
		case 1:
			rv = rv + "Orange has been rolled. They move up a spot.";
			this.setDiceRoll(0);
			this.setOrangeDiceRoll(++orangeDiceRoll);
			break;
		case 2:
			rv = rv + "Green has been rolled. They move up a spot.";
			this.setDiceRoll(0);
			this.setGreenDiceRoll(++greenDiceRoll);
			break;
		case 3:
			rv = rv + "Pink has been rolled. They move up a spot.";
			this.setDiceRoll(0);
			this.setGreenDiceRoll(++pinkDiceRoll);
			break;
		case 4:
			rv = rv + "Red has been rolled. They move up a spot.";
			this.setDiceRoll(0);
			this.setGreenDiceRoll(++redDiceRoll);
			break;
		case 5:
			rv = rv + "Blue has been rolled. They move up a spot.";
			this.setDiceRoll(0);
			this.setGreenDiceRoll(++blueDiceRoll);
			break;
		case 6:
			rv = rv + "Yellow has been rolled. They move up a spot.";
			this.setDiceRoll(0);
			this.setGreenDiceRoll(++yellowDiceRoll);
			break;
		default:
			rv = rv + this.getDiceRoll();
		}//EOC switch
		
		return rv;
	}//EOC roll
	
	//public void printSnailGameBoard() {
	//	for(int row = 0; row < snailGameBoard.length; ++row) {
	//		for(int column = 0; column < snailGameBoard[row].length; ++column) {
	//			System.out.print(snailGameBoard[row][column]);
	//		}
	//	}
	//}
	
}//EOC
