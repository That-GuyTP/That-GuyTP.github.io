package Homework04;

//THOMAS PETERSON
//This class defines the object of a robot (really just it's x and y position) as well as it's moves)
//The idea with this class is to regulate all needed processes if the robot and movement to this one class. This way I can simply my code later down the line during the driver class.
//Instead of making the methods in ObstaccleMaze I've made them here so that all the processes for the maze setup can occur in it's class.

public class Robot {

	//INSTANCE VAR
	private int x;
	private int y;
	
	//DEFAULT CON
	public Robot() {
		this.x = 0;
		this.y = 0;
	}
	
	
	//GETTERS FOR X & Y
	public int getX() {
		return x;
	}//EOC getX
	
	public int getY() {
		return y;
	}//EOC
	
	
	//MOVEMENT METHODS
	//It's important to remeber that the char array that I will be using in my ObstacleMaze class isn't organized like a normal 2D grind. From the top left most position
	//the grind moves from 0->10 in both direcctons to the right and down. So to move north or west we have to decrease y and x respectively. To move east and south we have to increase
	//y and x respectively.
	public void moveUp() {
		y--;
	}
	
	public void moveRight() {
		x++;
	}
	
	public void moveDown() {
		y++;
	}
	
	public void moveLeft() {
		x--;
	}
	
}//EOC Robot
