package Homework02;

//THOMAS PETERSON
//This is a basic class defining what an object "Video Game" would contain.

public class VideoGame {

	//INSTANCE VARIABLES
	private String name;
	private String console;
	
	//DEFAULT CONSTRUCTOR
	public VideoGame() {
		this.name = "null";
		this.console = "null";
	}
	
	//PARAMETER CONSTRUCTOR
	public VideoGame(String xName, String xConsole) {
		this.setName(xName);
		this.setConsole(xConsole);
	}

	//ACCESSORS AND MUTATORS
	public String getName() {
		return this.name;
	}

	public void setName(String xName) {
		if(this.name != null)
			this.name = xName;
		this.name = "null";
	}

	public String getConsole() {
		return this.console;
	}

	public void setConsole(String xConsole) {
		if(xConsole != null) 
			this.console = xConsole;
		this.console = "null";
	}
	
	//PRINT METHODS
	public String toString() {
		return "Name: " + this.name + ", Console: " + this.console;
	}
	
}//EOC VideoGame
