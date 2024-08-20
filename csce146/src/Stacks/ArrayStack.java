package Stacks;

public class ArrayStack <T> implements StackInterface<T>{

	private T[] stack;
	private int headIndex;//First null element of the stack. "Top" of stack is -1.
	public static final int DEF_SIZE = 100;
	
	//DEFAULT
	public ArrayStack() {
		init(DEF_SIZE);
	}
	
	//PARAMETER
	public ArrayStack(int size) {
		init(size);
	}
	
	//INIT
	private void init(int size) {
		this.headIndex = 0;
		if(size >= 1) {
			stack = (T[])(new Object[size]);
		}else {
			stack = (T[])(new Object[DEF_SIZE]);
		}//EOC IF-ELSE
	}
	
	//PUSH METHOD
	public void push(T xData) {
		if(headIndex >= stack.length) {
			return;//Full stack
		}//EOC IF
		stack[headIndex] = xData;
		headIndex++;
	}
	
	//POP METHOD
	public T pop() {
		if(headIndex ==0) {
			return null;
		}//EOC IF
		T ret = stack[headIndex-1];
		headIndex--;
		return ret;
	}
	
	//PEEK METHOD
	public T peek() {
		if(headIndex == 0) {
			return null;
		}//EOC IF
		return stack[headIndex-1];
	}
	
	//PRINT METHOD
	public void print() {
		for(int i = headIndex-1; i >= 0; i--) {
			System.out.println(stack[i]);
		}//EOC FOR
	}

	
	
}//EOC ArrayStack
