package Lab03;

//THOMAS PETERSON
/*NOTES
 * For the required methods I'm changing up the order since they aren't required to be in a certian order.
 * Attempted to include a decimal format for the totalCost. Couldn't since I do not control the print command used in GroceryListTester.
 */

public class GroceryList {
	
	//LISTNODE
	private class ListNode {
		//INSTANCE VARIABLES
		GroceryItem data;
		ListNode link;
		
		//DEFAULT CONSTRUCTOR
		public ListNode() {
			data = null;
			link = null;
		}//EOC DEFAULT CONSTRUCTOR
		
		//PARAMETER CONSTRUCTOR
		public ListNode(GroceryItem xData, ListNode xLink) {
			data = xData;
			link = xLink;
		}//EOC PARAMETER CONSTRUCTOR
	}//EOC LISTNODE
	
	
	//INSTANCE VARIABLES
	private ListNode head;
	private ListNode current;
	private ListNode previous;
	
	
	//DEFAULT CONSTRUCTOR
	public GroceryList() {
		head = current = previous = null;
	}//EOC DEFAULT CONSTRUCTOR
	
	
	//gotoNext METHOD
	public void gotoNext() {
		if(current == null)
			return;
		previous = current; //WILL BE NEEDED FOR LATER PROCESSES
		current = current.link;
	}//EOC gotoNext
	
	//getCurrent METHOD
	public GroceryItem getCurrent() {
		if(current == null)
			return null;
		return current.data;
	}//EOC getCurrent
	
	//setCurrent METHOD
	public void setCurrent(GroceryItem xData) {
		if(current == null || xData == null)
			return;
		current.data = xData;
	}//EOC setCurrent
	
	//addItem METHOD
	public void addItem(GroceryItem xData) {
		ListNode newNode = new ListNode(xData, null);
		if(head == null) {
			head = current = newNode;
			return;
		}//EOC IF
		ListNode temp = head;
		while(temp.link != null) 
			temp = temp.link;
		temp.link = newNode;
	}//EOC add
	
	//addItemAfterCurrent METHOD
	public void addItemAfterCurrent(GroceryItem xData) {
		if(current == null)
			return;
		ListNode newNode = new ListNode (xData, current.link);
		current.link = newNode;
	}//EOC addAfterCurrent
	
	//removeCurrent METHOD
	public void removeCurrent() {
		if(current == head) {
			head = head.link;
			current = head;
		}else {
			previous.link = current.link;
			current = current.link;
		}//EOC IF-ELSE
	}//EOC removeCurrent
	
	//showList METHOD
	public void showList() {
		ListNode temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.link;
		}//EOC WHILE
	}//EOC print
	
	//contains METHOD
	//This method creates a temporary node and assigns it to the head, accepts the parameter values, and then goes through the linked list checking if any of the nodes contain similar data.
	public boolean contains(GroceryItem xData) {
		ListNode temp = head;
		while(temp.link != null) {
			if(temp.data.equals(xData)) {
				return true;
			}//EOC IF
			temp = temp.link;
		}//EOC WHILE
		current = head; //MUST ADD OTHERWISE ERROR OCCURS
		return false;
	}//EOC contains
	
	//totalCost METHOD
	//This method sets current back to head, add the value of a Grocery Item's value to a double variable, and continues through the list until all values have been added.
	public double totalCost() {
		double totalCost = 0;
		current = head;
		while(current != null) {
			totalCost += current.data.getValue();
			gotoNext();
		}//EOC while
		return totalCost;
	}//EOC totalCost
	
}//EOC GroceryList
