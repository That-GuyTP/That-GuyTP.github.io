package Lab04;

//THOMAS PETERSON
/* NOTES
 * DLL are different to SLL in the following aspects:
 * Instead of a single reference connecting nodes, there's two. One referencing the next, the other referencing the previous.
 * 
 */

public class DoubleDoubleLL {

	//CREATING LISTNODE
	private class ListNode {
		
		//ELEMENTS
		private Double data;
		private ListNode next;
		private ListNode previous;
		
		//PARAMETER CONSTRUCTOR
		public ListNode(Double xData) {
			data = xData;
			next = null;
			previous = null;
		}//EOC PARAMETER CONSTRUCTOR
		
	}//EOC LISTNODE

	
	//DDLL CREATION
	//We don't need to create a next or previous element here since they are apart of a node itself rather than just being a pointer.
	private ListNode head;
	private ListNode current;
	
	public DoubleDoubleLL() {
		head = current = null;
	}//EOC DEFAULT
	
	
	//gotoNext, gotoPrev, reset, gotoEnd, & hasMore METHODS
	public void gotoNext() {
		if(current == null) {
			return;
		}else {
			current = current.next;
		}//EOC IF-ELSE
	}
	
	public void gotoPrev() {
		if(current == null) {
			return;
		}else {
			current = current.previous;
		}//EOC IF-ELSE
	}
	
	public void reset() {
		current = head;
	}
	
	public void gotoEnd() {
	//What this is doing is moving down the list until the link in current is null (AKA has reached the end)
	//And then set current equal to the last node it reached.
		while(current != null && current.next != null) {
			current = current.next;
		}//EOC WHILE
	}
	
	public boolean hasMore() {
		return current != null;
	}
	
	
	//getCurrent & setCurrent METHODS
	public Double getCurrent() {
		if(current == null) {
			return null;
		}else {
			return current.data;
		}//EOC IF-ELSE
	}
	
	public void setCurrent(Double xData) {
		//Checking to see if there is a current.
		if(current == null) {
			return;
		}else {
			current.data = xData;
		}//EOC IF-ELSE
	}
	
	
	//add & addAfterCurrent METHODS
	public void add(Double xData) {
	//Here we have a usual add method for a LL. All that was done is adjustments for the change into a doubly linked list instead of singley
		ListNode newNode = new ListNode(xData);
		if(head == null) {
			head = newNode;
			current = newNode;
		}else {
			ListNode temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}//EOC WHILE
			temp.next = newNode;
			newNode.previous = temp;
		}//EPC IF-ELSE
	}
	
	public void addAfterCurrent(Double xData) {
	/*EXPLAINATION
	 * Checks if current is valid
	 * Creates a newNode with the inputed data making sure to add both nulls for the parameters
	 * It then adjust both the next and previous pointers to their needed values
	 * If the current node was not the last in the list then it adds the new data into the list after current.
	 * Previous is also changed to make sure it points to the newly created node from the node after it's "current.previous".
	 */
		if (current == null) {
			return;
		}//EOC IF
		ListNode newNode = new ListNode(xData);
		newNode.next = current.next;
		newNode.previous = current;
		if(current.next != null) {
			current.next.previous = newNode;
		}//EOC IF
		current.next = newNode;
	}
	
	//remove & removeCurrent METHODS
	public void remove(Double xData) {
	/*EXPLAINATION
	 * This method first creates a temporary node that starts at the head.
	 * It then searches the LL for a node that contains the inputed data.
	 * If it finds a node with the same data, it overwrites the node's pointers similar to removeCurrent methods in previous LLs.
	 * Otherwise it keeps moving down the list.
	 * If it finds the node that needs to be removed and removes it, and returns to prevent any more nodes from being deleted.
	 * If it can't find a node with matching data, it moves to the next node.
	 * There is also a check to see if the removed node is the head, which is set to the temp node to prevent the list from being deleted entirely.
	 */
		ListNode temp = head;
		while (temp != null) {
			if(temp.data.equals(xData)) {
				if(temp.previous != null) {
					temp.previous.next = temp.next;
				}else {
					head = temp.next;
				}//EOC IF-ELSE 1
				if(temp.next != null) {
					temp.next.previous = temp.previous;
				}//EOC IF
				return;
			}//EOC IF
			temp = temp.next;//DON'T FORGET
		}//EOC WHILE
	}
	
	public void removeCurrent() {
	/* EXPLAINATION
	 * Similar to a removeCurrent method from previous LL examples. Minor adjustments were made to adjust for it being a doubly linked list instead of a singly one.	
	 */
		if(current == null) {
			return;
		}else {
			if(current == head) {
				head = current.next;
				if(head != null) {
					head.previous = null;
				}//EOC INNER IF
			}else {
				current.previous.next = current.next;
				if(current.next != null) {
					current.next.previous = current.previous;
				}//EOC INNER IF
			}//EOC INNER IF-ELSE
			current = current.next;
		}//EOC IF-ELSE
	}
	
	//print METHOD
	public void print() {
		ListNode temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			if(temp.next == null) { //Added this check so the "print last element" test would work.
				current = temp;
			}//EOC IF
			temp = temp.next;
		}//EOC WHILE
	}
	
	//contains METHOD
	public boolean contains(Double xData) {
		ListNode temp = head;
		while(temp != null) {
			if(temp.data.equals(xData)) {
				return true;
			}//EOC IF
			temp = temp.next;
		}//EOC WHILE
		return false;
	}
	
}//EOC DoubleDoubleLL
