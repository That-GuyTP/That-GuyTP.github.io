package GenLL;

public class GenLL <T>{

	//CREATING A NODE OBJECT
	private class ListNode {
		T data;
		ListNode link;
		public ListNode(T aData, ListNode aLink) {
			data = aData;
			link = aLink;
		}//EOC PARAMETER	
	}//EOC ListNode
	
	//CREATING ELEMENTS OF LL
	private ListNode head;
	private ListNode current;
	private ListNode previous;
	public GenLL() {
		head = current = previous = null;
	}//EOC DEFAULT
	
	//ADDING
	public void add(T aData) {
		ListNode newNode = new ListNode(aData, null);
		if(head == null) {
			head = current = newNode;
			return;
		}//EOC IF
		ListNode temp = head;
		while(temp.link != null) 
			temp = temp.link;
		temp.link = newNode;
	}//EOC add
	
	public void print() {
		ListNode temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.link;
		}//EOC WHILE
	}//EOC print
	
	//ADDING AFTER CURRENT
	public void addAfterCurrent(T aData) {
		if(current == null) 
			return;
		ListNode newNode = new ListNode(aData, current.link);
		current.link = newNode;
	}//EOC addAfterCurrent
	
	//ACCESSOR & MUTATOR
	public T getCurrent() {
		if(current == null)
			return null;
		return current.data;
	}
	
	public void setCurrent(T aData) {
		if(aData == null || current == null) 
			return;
		current.data = aData;
	}
	
	//MOVING CURRENT
	public void gotoNext() {
		if(current == null)
			return;
		previous = current;
		current = current.link;
	}
	
	//RESET CURRENT
	public void reset() {
		current = head;
		previous = null;
	}
	
	//SHOW IF THERE IS MORE
	public boolean hasMore() {
		return current != null;
	}
	
	//REMOVE NODE FROM LL
	public void removeCurrent() {
		if(current == head) {
			head = head.link;
			current = head;
		}else {
			previous.link = current.link;
			current = current.link;
		}//EOC IF-ELSE
	}
	
}//EOC GenLL
