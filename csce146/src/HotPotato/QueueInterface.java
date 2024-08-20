package HotPotato;

public interface QueueInterface <T>{ //add "<T>" to allow for any data type use.

	public void enqueue(T xData);//Add to end
	public T dequeue();//Remove and print first
	public T peek(); //print next element
	public void print(); //prints all elements
	
	//ADDED FOR THIS PROJECT
	public int size();
	
	
}//EOC QueueInterface
