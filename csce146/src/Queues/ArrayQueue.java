package Queues;

public class ArrayQueue <T> implements QueueInterface<T>{ 
	//add "<T>" to allow it to be any data type.
	//Don't forget "<T>" after the interface implement.

	//DECLARING ARRAY & NEEDED VARIABLES
	private T[] queue;
	private int headIndex; //Ref first element in queue. Index 0 not always first element.
	private int tailIndex; //Ref first null element or one after last element. Can be used instead of a for loop to add things to the end of the array.
	public static final int DEF_SIZE = 100; //Need a default size for an array.
	
	//DEFAULT CONSTRUCTOR
	public ArrayQueue() {
		init(DEF_SIZE);
	}
	
	//PARAMETER CONSTRUCTOR
	public ArrayQueue(int size) {
		init(size);
	}
	
	//INIT METHOD
	//Used to combine both para & default constructor together.
	public void init(int size) {
		headIndex = tailIndex = 0;
		
		//We are constructing an object of a T array interface since we can't construct an interface. The object is type casted into a T interface array
		if(size >= 1) {
			queue = (T[])(new Object[size]);
		}else {
			queue = (T[])(new Object[DEF_SIZE]);
		}//EOC IF-ELSE
	}
	
	//ENQUEUE METHOD
	public void enqueue(T xData) {
		if(queue[headIndex] != null && headIndex == tailIndex) {
			return;//Means the queue is full. Tail is wrapped around and is next to the head.
		}//EOC IF
		queue[tailIndex] = xData;
		tailIndex = (tailIndex+1)%queue.length;
	}
	
	//DEQUEUE METHOD
	public T dequeue() {
		T ret = queue[headIndex];
		headIndex = (headIndex+1)%queue.length;
		return ret;
	}
	
	//PEEK METHOD
	public T peek() {
		return queue[headIndex];
	}
	
	//PRINT METHOD
	public void print() {
		System.out.println(queue[headIndex]);
		for(int i = (headIndex+1)%queue.length; i != tailIndex; i = (i+1)%queue.length) {
			System.out.println(queue[i]);
		}//EOC FOR
	}
	
}//EOC ArrayQueue
