package LinkedLists;

public class IntLLTester {

	public static void main(String[] args) {
		IntLL iLL;
		iLL = new IntLL();
		
		//BEGIN LINKED LIST
		for(int i = 4; i >=0; i--) {
			iLL.add(i);
		}//EOC FOR
		
		//ADD ELEMENT AFTER CURRENT
		iLL.print();
		iLL.addAfterCurrent(22);
		iLL.print();
		
		//USING gotoNext METHOD
		iLL.gotoNext();
		iLL.addAfterCurrent(23);
		iLL.print();
		
		//USING setCurrent METHOD
		iLL.setCurrent(42);
		iLL.print();
		
		//USING REMOVAL METHODS
		iLL.removeCurrent();
		iLL.print();
		
	}//MAIN METHOD

}//EOC IntLLTester
