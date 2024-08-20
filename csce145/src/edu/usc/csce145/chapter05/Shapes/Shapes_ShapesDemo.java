package edu.usc.csce145.chapter05.Shapes;

public class Shapes_ShapesDemo {
	
	public static void main(String[] args) {
		Shapes_ShapeBasics[] sh = new Shapes_ShapeBasics[2];
		sh[0] = new Shapes_Rectangle(5, 10, 15);
		sh[1] = new Shapes_Triangle(5, 15);
		
		for(int i = 0; i < sh.length; i++) {
			sh[i].drawHere();
		}//EOC FOR
		
	}//EOC MAIN METHOD
	
}//EOC Shapes_ShapesDemo
