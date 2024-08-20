package Stacks;

public class Tester {

	public static void main(String[] args) {
		StackInterface<String> strStack = new ArrayStack();
											 //Swap ArrayStack with LLStack to switch from array and linked lists.
		
		for(int i = 0; i < 5; i++) {
			strStack.push("String " + i);
		}
		System.out.println(strStack.peek() + "\n");
		strStack.print();
		
		for(int i = 0; i < 5; i++) {
			System.out.println(strStack.pop());
		}//EOC FOR
		System.out.println("\n" + strStack.peek());

	}//EOC MAIN METHOD

}//EOC TESTER
