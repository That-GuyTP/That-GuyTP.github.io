package Lab05;

//THOMAS PETERSON

public class ProcessScheduler {

	//PROPERTIES
	private LLQueue<Process> processes;
	private Process currentProcess;
	
	//DEFAULT CON
	public ProcessScheduler() {
		processes = new LLQueue<>();
		currentProcess = null;
	}
	
	//getCurrentProcess METHOD
	public Process getCurrentProcess() {
		return currentProcess;
	}//EOC
	
	//addProcess METHOD
	public void addProcess(Process xProcess) {
		if(xProcess == null) {
			currentProcess = xProcess;
		}else {
			processes.enqueue(xProcess);;
		}//EOC IF-ELSE
	}//EOC
	
	//runNextProcess METHOD
	public void runNextProcess() {
		if(processes == null) {
			return;
		}else {
			currentProcess = processes.dequeue();
		}//EOC IF-ELSE
	}//EOC
	
	//cancelCurrentProcess() {
	public void cancelCurrentProcess() {
		if(currentProcess == null) {
			return;
		}else {
			if(processes == null) {
				currentProcess = null;
			}else {
				currentProcess = processes.dequeue();
			}//EOC INNER IF-ELSE
		}//EOC IF-ELSE
	}
	
	//printProcessQueue METHOD
	public void printProcessQueue() {
		processes.print();
	}
	
	
}//EOC ProcessScheduler
