package edu.mtc.egr281.chapter06;

public class Horse {
//Constants
	
//FIELDS
	private String name;
	private int currentFurlong;
	
//CONSTRUCTORS
	public Horse(String newName) {
		this.setName(newName);
		this.setCurrentFurlong(0);
	}
	
//METHODS
	public String getName() { //String is an exception to a privacy check. Strings are enutable
		return this.name;
	}//EOC method getName
	
	private void setName(String newName) {
		this.name = newName;
	}//EOC method setName
	
	public int getCurrentFurlong() {
		return this.currentFurlong;
	}//EOC method getCurrentFurlong
	
	private void setCurrentFurlong(int newCurrentFurlong) {
		this.currentFurlong = newCurrentFurlong;
	}
	
	public void updateCurrentFurlong(int updateValue) {
		this.setCurrentFurlong(updateValue + this.getCurrentFurlong());
	}

}//EOC class Horse
