package Shapes;

//THOMAS PETERSON

public class BasicRectangle extends BasicShape implements Rectangle{

	//INSTANCE VARIABLES
	private int width, height;
	
	
	//DEFAULT CONSTRUCTOR
	public BasicRectangle() {
		this.width = this.height = 1;
	}//DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public BasicRectangle(int xH, int xW, int xHe) {
		super(xH);
		this.setHeight(xHe);
		this.setWidth(xW);
	}//EOC PARAMETER COSNTRUCTOR


	//ACCESSOR METHODS
	public int getWidth() {
		return width;
	}//EOC getWidth

	public int getHeight() {
		return height;
	}//EOC getHeight


	//MUTATOR METHODS
	public void setHeight(int xHe) {
		if(xHe >= 0) {
			this.height = xHe;
		}else {
			this.height = 0;
		}//EOC IF-ELSE
	}//EOC setHeight
	
	public void setWidth(int xW) {
		if(xW >= 0) {
			this.width = xW;
		}else {
			this.width = 0;
		}//EOC IF-ELSE
	}//EOC setWidth
	
	
	//OTHER METHODS
	public void drawShape() {
		for(int i = 0; i < this.height; i++) {
			skipSpace(super.getHSpace());
			for(int j = 0; j < this.width; j++) {
				System.out.println("*");
			}//EOC INNER FOR
			System.out.println();
		}//EOC FOR
	}//EOC drawShape
	
	
}//EOC BasicRectangle
