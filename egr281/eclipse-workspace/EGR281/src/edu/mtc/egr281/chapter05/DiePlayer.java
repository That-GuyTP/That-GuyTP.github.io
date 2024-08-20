package edu.mtc.egr281.chapter05;

public class DiePlayer {
	
	private String name;
	private int score;
	
	//Constructor
	public DiePlayer(String newName) {
		this.setName(newName);
		this.setScore(0);
	}

	//Fields

	public String getName() {
		return this.name;
	}//EOC method getName
	
	private void setName(String newName) {
		this.name = newName;
	}//EOC method setName
	
	public int getScore() {
		return this.score;
	}//EOC method getScore
	
	private void setScore(int newScore) {
		this.score = newScore;
	}//EOC method setScore
	
	public void updateScore(int updateValue) {
		this.setScore(updateValue + this.getScore());;
	}
	
}//EOC DiePlayer
