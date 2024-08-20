package Queues;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		
		//TESTING QUEUE METHODS CREATED
		QueueInterface<String> sQ = new ArrayQueue<String>(); //NOTE - If you switch ArrayQueue with LinkedListQueue you will receive the same output.
		for(int i=0; i < 5; i++) {
			sQ.enqueue("String " + i);
		}//EOC FOR
		System.out.println(sQ.peek() + "\n");
		sQ.print();
		for(int i = 0; i < 5; i++) {
			System.out.println(sQ.dequeue());
		}//EOC FOR
		
		//TESTING java.util.*
		LinkedList<String> strLL = new LinkedList<String>();//Creates a doubly linked list of the type string.
		Queue<String> sQ2 = new LinkedList<String>();//Must create a new ???<String> since Queue is an interface.
		
	}//EOC MAIN METHOD
}//EOC Test
