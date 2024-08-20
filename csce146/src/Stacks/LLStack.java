package Stacks;

public class LLStack<T> implements StackInterface<T> {

	private class ListNode {
		T data;
		ListNode link;
		public ListNode(T xData, ListNode xLink) {
			data = xData;
			link = xLink;
		}//EOC PARAMETER
	}
	
	//ELEMENTS OF LL
	private ListNode head;
	
	//DEFAULT CONSTRUCTOR
	public LLStack() {
		head = null;
	}
	
	//PUSH METHOD
	public void push(T xData) {
		ListNode newNode = new ListNode(xData, head);
		head = newNode;
	}
	
	//POP METHOD
	public T pop() {
		if(head == null) {
			return null;
		}//EOC IF
		T ret = head.data;
		head = head.link;
		return ret;
	}

	//PEEK METHOD
	public T peek() {
		if(head == null) {
			return null;
		}//EOC IF
		return head.data;
	}
	
	//PRINT METHOD
	public void print() {
		ListNode temp = head;
		while(temp != null) {
			System.out.println(temp.data);
			temp = temp.link;
		}//EOC WHILE
	}
	
	
}//EOC LLStack
