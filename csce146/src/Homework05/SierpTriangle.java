package Homework05;

//Thomas Peterson
//I'll be honest. I have no clue how my "STOP AT SIZE 4" if statement works. I couldn't for 3 hours figure out why it wasn't working and decided to go by powers of 4 since that's the limit. 
//32 was the answer that worked and had the system print out properly. But hey if it ain't broke, don't fix it.

import java.awt.*;
import javax.swing.*;

public class SierpTriangle extends Canvas{
	
	public static void main(String[] args) {
		
		//CREATING THE CANVAS
		JFrame frame = new JFrame("Sierp Triangle");
		frame.setSize(900, 900);
		SierpTriangle st = new SierpTriangle();
		frame.add(st);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//EOC MAIN METHOD
	
	//CREATING GRAPHICS OBJECT
	public void paint(Graphics g) {
		drawSTriangle(20, 880, 880, g);
	}//EOC PAINT
	
	//DRAWING TRIANGLE AND RECURSION
	public void drawSTriangle(int x, int y, int size, Graphics g) {
		
		System.out.println("Drawing triangle with size :" + size);
		
		//LEARNED FROM "ADDITIONAL NOTES"
		//Professor Shepard also suggested to take this approach after lecture one day and showed me and some colleges an example.
		int sub = size / 2;
		
		//MAIN TRIANGLE
		int[] xPoints = {x, x + size / 2, x + size};
		int[] yPoints = {y, y - size, y};
		g.setColor(Color.BLACK);
		g.fillPolygon(xPoints, yPoints, 3);
		
		//MIDDLE TRIANGLE
		int[] xMiddlePoints = {x + size / 4, x + size / 2, x + 3 * size / 4};
	    int[] yMiddlePoints = {y - size / 2, y, y - size / 2};
	    g.setColor(Color.WHITE);
	    g.fillPolygon(xMiddlePoints, yMiddlePoints, 3);
		
		//STOP AT SIZE 4
		if (size > 32) {
			drawSTriangle(x, y, sub, g);//LEFT
			drawSTriangle(x + sub / 2, y - sub, sub, g);//RIGHT
			drawSTriangle(x + sub, y, sub, g);//TOP
		}//EOC IF
		
	}//EOC drawSTriangle

}//EOC SierpTriangle
