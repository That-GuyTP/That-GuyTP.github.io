package edu.mtc.egr281.chapter06;

public class Player {

	//CONSTANTS
	private static final String LADDER = "\n+++ Ladder up to +++ \n";
	private static final String CHUTE = "\n--- Chute down to --- \n";
	
	//FIELDS
	private String name;
	private int currentSquareNumber;
	
	//CONSTRUCTORS
	public Player(String newName) {
		this.setName(newName);
		this.setCurrentSquareNumber(0);
	}//EOC construction "Not default because commands inside"
	
	//METHODS
	public String getName() {
		return this.name;
	}//EOC getName
	
	private void setName(String newName) {
		this.name = newName;
	}//EOC setName
	
	public int getCurrentSquareNumber() { 
		return this.currentSquareNumber;
	}//EOC getCurrentSquareNumber
	
	private void setCurrentSquareNumber(int newCurrentSquareNumber) {
		this.currentSquareNumber = newCurrentSquareNumber;
	}//EOC setCurrentSquareNumber
	
	public String updateCurrentSquareNumber(int update){ 
		String rv = this.getName() + " is now on Square ";
		this.setCurrentSquareNumber(update + this.getCurrentSquareNumber());
		
		switch(this.getCurrentSquareNumber()) {
			case 1: 
				rv = rv + "1: You started a garden" + Player.LADDER +
				"Square 38: You now have beautiful flowers";
				this.setCurrentSquareNumber(38);
				break;
			case 4:
				rv = rv + "You started to bake a cake" + Player.LADDER +
				"Square 14: You baked a delicious cake for everyone to eat";
				this.setCurrentSquareNumber(14);
				break;
			case 9: 
				rv = rv + "You did your chores and mowed the lawn" + Player.LADDER +
				"Square 31: You get to go to the fair and have fun";
				this.setCurrentSquareNumber(31);
				break;
			case 21: 
				rv = rv + "You put a bandage on a hurt pet" + Player.LADDER + 
				"Square 42: Pet is back to being healthy and happy ☺";
				this.setCurrentSquareNumber(42);
				break;
			case 28: 
				rv = rv + "You rescued a cate from a tree" + Player.LADDER + 
				"Square 84: You have a new best friend (Mr. Bigglesworth)";
				this.setCurrentSquareNumber(84);
				break;
			case 36: 
				rv = rv + "You ate a healthy morning breakfast" + Player.LADDER +
				"Square 44: You grow up tall and strong";
				this.setCurrentSquareNumber(44);
				break;
			case 51: 
				rv = rv + "You swept and cleaned the house" + Player.LADDER +
				"Square 67: You get to go to the movie theater";
				this.setCurrentSquareNumber(67);
				break;
			case 71:
				rv = rv + "You returned a missing purse" + Player.LADDER + 
				"Square 91: As a reward, the nice lady bought you ice cream";
				this.setCurrentSquareNumber(91);
				break;
			case 80: 
				rv = rv + "You won best in show" + Player.LADDER + 
				"Square 100: YOU WIN THE GAME!!!!";
				this.setCurrentSquareNumber(100);
				break;
			case 98: 
				rv = rv + "You pulled on a cat’s tail" + Player.CHUTE + 
				"Square 78: The cat scratched you";
				this.setCurrentSquareNumber(78);
				break;
			case 95: 
				rv = rv + "You broke a mirror" + Player.CHUTE + 
				"Square 75: You have to empty your piggy bank to pay for it";
				this.setCurrentSquareNumber(75);
				break;
			case 93: 
				rv = rv + "You drew on the wall" + Player.CHUTE +
				"Square 73: You have to scrub it off the wall";
				this.setCurrentSquareNumber(73);
				break;
			case 87: 
				rv = rv + "You peeked in the cookie jar" + Player.CHUTE +
				"Square 24: you were clumsy and knocked over the cookie jar";
				this.setCurrentSquareNumber(24);
				break;
			case 64: 
				rv = rv + "You were careless on when riding a bicycle" + Player.CHUTE +
				"Square 60: You fell and hurt your arm";
				this.setCurrentSquareNumber(60);
				break;
			case 62:  
				rv = rv + "You carried a heavy load of plates" +
				"Square 19:  You dropped and broke the heavy pile of plates";
				this.setCurrentSquareNumber(19);
				break;
			case 56: 
				rv = rv + "You played in the rain without wearing rain gear" + Player.CHUTE + 
				"Square 53: You got a sick and have a cold";
				this.setCurrentSquareNumber(53);
				break;
			case 49: 
				rv = rv + "You ate an entire box of cookies" + Player.CHUTE + 
				"Square: 11: You got an upset stomach from too much food";
				this.setCurrentSquareNumber(11);
				break;
			case 47: 
				rv = rv + "You ice skated where you were not supposed to" + Player.CHUTE + 
				"Square 26: You broke through the ice and got cold";
				this.setCurrentSquareNumber(26);
				break;
			case 16: 
				rv = rv + "You read comic books instead of studying" + Player.CHUTE + 
				"Square 6: You must now wear the cone of shame";
				this.setCurrentSquareNumber(6);
				break;
			default:
				rv = rv + this.getCurrentSquareNumber();
		}//EOC switch
		
		if(this.getCurrentSquareNumber() > 100) {
			rv = "Oops! Your spin would take you past square #1000, don't move. " + 
					"Try again on your next turn";
			this.setCurrentSquareNumber(this.getCurrentSquareNumber() - update);
		}//EOC if
		
		return rv;
	}//EOC updateCurrentSquareNumber


	
}//EOC Player
