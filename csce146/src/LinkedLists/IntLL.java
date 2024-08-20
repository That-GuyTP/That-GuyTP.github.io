package LinkedLists;

public class IntLL {

	private class ListNode {
		int data;
		ListNode link; //Indentifier only contains memory address. We pass this on to aLink below.
		public ListNode(int aData, ListNode aLink) {
			data = aData;
			link = aLink;
		}//PARAMETER CONSTRUCTOR
	}//EOC INNERListNode
	
	private ListNode head; //First element
	private ListNode current;//Current node of interest
	private ListNode previous;//One node behind current
	
	public IntLL() {
		head = current = previous = null;
	}//DEFAULT CONSTRUCTOR
	
	//ADD ELEMENT TO LIST
	public void add(int aData) {
		ListNode newNode = new ListNode(aData, null);
		if(head == null) { //Means we have a empty list.
			head = current = newNode; //Head points to current which points to the newly created Node.
			return;
		}//EOC IF
		ListNode temp = head; //Holds memory address of head
		while(temp.link != null)  //link is memory address that points to next object
			temp = temp.link; //stores memory in memory address of next;
		temp.link = newNode;
	}//EOC add
	
	//ADD ELEMENT AFTER CURRENT
	public void addAfterCurrent(int aData) {
		if (current == null)
			return;
		ListNode newNode = new ListNode(aData, current.link);
		current.link = newNode;
	}//EOC addAfterCurrent
	
	//PRINT LIST
	public void print() {
		ListNode temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.link;
		}//EOC WHILE
	}//EOC print
	
	public int getCurrent() {
		if(current == null)
			return 0;
		return current.data;
	}//EOC getCurrent
	
	public void setCurrent(int aData) {
		if(current == null)
			return;
		current.data = aData;
	}//EOC setCurrent
	
	public void gotoNext() {
		if(current != null)
			previous = current;
			current = current.link;
	}//EOC gotoNext
	
	public boolean hasMore() {
		return current != null;
	}//EOC hasMore
	
	public void reset() {
		current = head;
		previous = null;
	}//EOC reset
	
	public void removeCurrent() {
		if(current != null && previous != null) {
			previous.link = current.link; //Skips over current
			current = current.link; //whatever was being refered to in current parameter is now deleted.
		}else if(current != null && previous == null) {
			head = head.link;//current = current.link; cannot be used because head refers to the first element. Using the proper code doesn't change the value of head.
			current = head;
		}// EOC IF-ELSEIF
	}//EOC removeCurrent
	
}//EOC IntLL
