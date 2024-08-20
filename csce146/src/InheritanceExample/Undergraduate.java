package InheritanceExample;

public class Undergraduate extends Student {

	//INSTANCE VARIABLES
	private int level;
	
	
	//DEFAULT CONSTRUCTOR
	public Undergraduate() {
		super();
		this.level = 1;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//PARAMETER CONSTRUCTOR
	public Undergraduate(String xName, int xId, int xLevel) {
		super(xName, xId);
		this.setLevel(xLevel);
	}//EOC PC

	
	//ACCESSOR METHOD
	public int getLevel() {
		return level;
	}//EOC getLevel

	
	//MUTATOR METHOD
	public void setLevel(int xLevel) {
		if(xLevel >= 1 && xLevel<= 4) {
			this.level = xLevel;
		}else {
			this.level = 1;
		}//EOC IF-ELSE
	}//EOC setLevel
	
	
	//MISC METHODS
	public String toString() {
		return super.toString() + "Level: " + this.level;
	}//EOC toString
	
	public boolean equals(Undergraduate xUndergraduate) {
		return xUndergraduate != null
				&& super.equals(xUndergraduate)
				&& this.level == xUndergraduate.getLevel();
	}//EOC equals

	
}//EOC Undergraduate
