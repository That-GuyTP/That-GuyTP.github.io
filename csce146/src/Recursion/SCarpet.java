package Recursion;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SCarpet extends Canvas{ //We extend "Canvas" to help specify what we are drawing.

	public static void main(String[] args) {
		
		//CREATING CARPET
		JFrame frame = new JFrame("S Carpet"); //Creating the carpet/square
		frame.setSize(900,900); //Declaring carpet size
		
		//DRAWING CARPET
		SCarpet sp = new SCarpet();
		frame.add(sp);
		
		//DONE AT END OF MAIN METHOD
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closing the frame closes the method.
		
	}//EOC MAIN METHOD
	
	
	//OVERWRITING A METHOD THAT WE NEVER USE FOR FUTURE SAKE
	public void paint(Graphics g) {
		drawCarpet(0, 0, this.getSize().height, g);
	}//EOC paint
	
	
	public void drawCarpet(int x, int y, int s, Graphics g) { //(Origin, Origin, Carpet Size, Graphics brush)
		int sub = s / 3;
		g.fillRect(x+sub, y+sub, sub, sub);
		if(sub >= 3) {
			//TOP
			drawCarpet(x, y, sub, g);//LEFT
			drawCarpet(x+sub, y, sub, g);//MIDDLE
			drawCarpet(x+sub*2, y, sub, g);//RIGHT
			
			//MIDDLE
			drawCarpet(x, y+sub, sub, g);//LEFT
			//MIDDLE is already drawn from top of method.
			drawCarpet(x+sub*2, y+sub, sub, g);//RIGHT
			
			//BOTTOM
			drawCarpet(x, y+sub*2, sub, g);//LEFT
			drawCarpet(x+sub, y+sub*2, sub, g);//MIDDLE
			drawCarpet(x+sub*2, y+sub*2, sub, g);//RIGHT
		}
	}//EOC drawCarpet
	
}//EOC SCarpet