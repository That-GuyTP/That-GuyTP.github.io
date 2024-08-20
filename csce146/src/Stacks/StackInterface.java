package Stacks;

public interface StackInterface <T> {

	public void push(T xData);//Adds to the head
	public T pop();//Removes from the head
	public T peek();//Returns but does not remove
	public void print();//Prints stack list.
	
}//EOC StackInterface
