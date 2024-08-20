package edu.mtc.egr281.chapter01;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class HappyFace extends JFrame {

	public static final long serialVersionUID = 1L;
	
	public HappyFace() {
		
		super();
		setTitle("My First GUI");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}// EOC constructor
	
	public void paint(Graphics brush) {
		
		//FACE
		brush.setColor(Color.YELLOW);
		brush.fillOval(100, 50, 225, 250);
		brush.setColor(Color.BLACK);
		brush.drawOval(100, 50, 225, 250);
		brush.setColor(new Color(165, 42, 42));
		
		//LAYERING - OVERWRITING LINES
		//brush.setColor(Color.PINK);
		//brush.drawOval(100, 50, 200, 250);
		
		//EYES
		brush.setColor(new Color(165, 42, 42));
		brush.fillOval(150, 110, 30, 15);
		
		brush.setColor(Color.BLACK);
		brush.drawArc(150, 105, 30, 5, 30, 120);
		
		//MOUTH
		brush.setColor(Color.RED);
		brush.drawArc(150, 160, 100, 50, 185, 170);
		
		//NOSE
		brush.setColor(Color.RED);
		brush.drawLine(203, 125, 195, 150);
		brush.drawLine(195, 150, 215, 155);
		
		//EARS
		brush.setColor(Color.RED);
		brush.fillOval(x,x,x,x);
		
	}// EOC paint
	
	public static void main(String[] args) {
		
		HappyFace hf = new HappyFace();
		hf.setVisible(true);		
				
	}// EOC main
	
}// EOC HappyFace
