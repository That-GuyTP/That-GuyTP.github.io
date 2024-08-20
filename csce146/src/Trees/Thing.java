package Trees;

public class Thing implements Comparable<Thing>{ //Must add "implements Comaprable<x>" in order to make this object comparable.

	//INSTANCE VARIABLES
	public double value;
	public String str;
	
	//COMPARETO METHOD
	//If v1 >= v2 return positive, if v1 = v2 0 return equal, if <= return negative.
	public int compareTo(Thing o) {
		if(o == null) {
			return -1;
		}else if(this.value > o.value) {
			return 1;
		}else if(this.value < o.value) {
			return -1;
		}else {
			//return 0 - Used for comparing just numbers
			return this.str.compareTo(o.str); //Used to compare strings by converting them into their value in binary.
		}//EOC IF-ELSEIF-ELSEIF-ELSE
	}//EOC compareTo
	
}//EOC Thing
