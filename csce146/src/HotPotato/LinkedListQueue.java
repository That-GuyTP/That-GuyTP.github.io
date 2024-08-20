package HotPotato;

public class LinkedListQueue<T> implements QueueInterface<T> {

	private class ListNode {
		//LIST NODE VARIABLES
		T data;
		ListNode link;
		
		//CONSTRUCTOR
		public ListNode(T xData, ListNode xLink) {
			data = xData;
			link = xLink;
		}//EOC
	}//EOC ListNode
	
	private ListNode head;//First element in queue
	private ListNode tail;//Last element in queue
	private int size;//ADDED FOR NEW PROJECT
	
	//DEFAULT CONSTRUCTOR
	public LinkedListQueue() {
		head = tail = null;
	}
	
	//ENQUEUE METHOD
	public void enqueue(T xData) {
		ListNode newNode = new ListNode(xData, null);
		
		//CHECKING IF THIS IS FIRST ELEMENT CREATED IN LL
		if(head == null) {//Means queue is empty 
			head = tail = newNode;
			size = 1;//ADDED FOR PROJECT
			return;
		}//EOC IF
		
		//ADDING TO END OF LL
		tail.link = newNode;
		tail = tail.link;
		size++;//ADDED FOR PROJECT
	}
	
	//DEQUEUE METHOD
	public T dequeue() {
		//CHECKING IF LL IS EMPTY
		if(head == null) {
			return null;
		}
		
		//REMOVING ELEMENT
		T ret = head.data;
		head = head.link;
		size--;//ADDED FOR PROJECT
		return ret;
	}
	
	//PEEK METHOD
	public T peek() {
		//CHECKING IF HEAD HAS DATA
		if(head == null) {
			return null;
		}
		//RETURNING DATA OF FIRST ELEMENT
		return head.data;
	}
	
	//PRINT METHOD
	public void print() {
		for(ListNode temp = head; temp != null; temp = temp.link) {
			System.out.println(temp.data);
		}//EOC FOR
	}
	
	//GET SIZE METHOD
	public int size() {
		return this.size;
	}//EOC
	
}//EOC LinkedList Queue
