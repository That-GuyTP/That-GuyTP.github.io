package Lab05;

//THOMAS PETERSON

public class Process {

	//INSTANCE VARI
	private String name;
	private double completion;
	
	//DEFAULT CON
	public Process() {
		name = "none";
		completion = 0.0;
	}
	
	//PARA CON
	public Process(String xName, Double xCompletion) {
		this.completion = xCompletion;
		this.name = xName;
	}

	// GETTERS AND SETTERS
	public String getName() {
		return this.name;
	}

	public void setName(String xName) {
		if(xName != null) {
			this.name = xName;
		}else {
			this.name = "none";
		}//EOC IF-ELSE
	}

	public double getCompletionTime() {
		return this.completion;
	}

	public void setCompletionTime(double xCompletion) {
		if(xCompletion >= 0) {
			this.completion = xCompletion;
		}else {
			this.completion = 0.0;
		}//EOC IF-ELSE
	}
	
	//TOSTRING
	public String toString() {
		return "Process Name: " + this.name + " Completion Time: " + this.completion;
	}
	
}//EOC Process
