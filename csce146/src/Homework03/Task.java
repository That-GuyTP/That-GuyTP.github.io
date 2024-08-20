package Homework03;

//THOMAS PETERSON
//NOTES
//This class is where a "task" object is declared and defined.
//It also contains the needed getters and setters for a task's action and priority.

public class Task {

	//INST VARIABLES
	private String action;
	private int priority;
	
	//DEFAULT CON
	public Task() {
		action = "none";
		priority = 4;
	}//EOC 
	
	//PARA CON
	public Task(String xAction, int xPriority) {
		setAction(xAction);
		setPriority(xPriority);
	}

	//GETTERS AND SETTERS
	public String getAction() {
		return this.action;
	}

	public void setAction(String xAction) {
		if(xAction != null) {
			this.action = xAction;
		}else {
			this.action = "none";
		}//EOC IF-ELSE
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int xPriority) {
		if(xPriority >= 0 && xPriority <= 4) {
			this.priority = xPriority;
		}else {
			xPriority = 4;
		}//EOC IF-ELSE
	}
	
	//TOSTIRNG
	public String toString() {
		return "Priority: " + priority + ", Action: " + action;
	}//EOC
	
	
	
}//EOC Task
