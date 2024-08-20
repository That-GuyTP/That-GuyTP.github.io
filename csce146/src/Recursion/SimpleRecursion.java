package Recursion;

public class SimpleRecursion {

	public static void main(String[] args) {
		countDown(5);
		factorial(5);
	}//EOC MAIN METHOD
	
	public static void countDown(int i) {
		if(i < 0) {//Halting Condition
			return;
		}//EOC IF
		System.out.println(i);
		countDown(i-1);//Recusive Call. "--i" also works. You can't use "i--" since the operation happens after causing a stack overflow.
	}//EOC countDown

	public static int factorial(int num) {
		if(num <= 0) {
			return 1;
		}else {
			return num * factorial(num-1);
		}//EOC IF-ELSE
	}//EOC factorial
	
	public static int gcd(int a, int b) {
		if(b == 0) {
			return a;
		}else {
			return gcd(b,a%b);
		}//EOC IF-ELSE
	}//EOC gcd
	
	public static int fibonacci(int num) {
		if(num == 1 || num == 2) {
			return 1;
		}else {
			return fibonacci(num-1)+fibonacci(num-2);
		}
	}
	
}//EOC SimpleRecursion
