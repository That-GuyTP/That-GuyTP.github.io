package Lab01;

public class LabReportQuestions {

	public static void main(String[] args) {
	
		
		/* #7
		char[] abcs = new char[26];	
		for(int i = 0; i <= abcs.length; i++) {
			abcs[i] = "a"+i;
		}//EOC 
		*/
		
		/* #8
		int[] array = {36, 25, 80, 95, 54};
		int div4 = 0;
		int div5 = 0;
		for(int i = 0; i < array.length; i = 0) {
			if(array[i] % 4 == 0) {
				div4++;
			}else if(array[i] % 5 == 0) {
				div5++;
			}//EOC IF-ELSEIF
		}//EOC FOR
		System.out.println("Number of value divisible by 4: "+div4);
		System.out.println("Number of value divisible by 5: "+div5);
		*/
		
		
		double[] array = {1,2,3,4,5};
		double[] doubledArray = new double[array.length*2];
		for(int i = 0; i < array.length; i++) {
			doubledArray[i] = array[i];
		}
		for(int i = 0; i < array.length; i++) {
			doubledArray[i + array.length] = array[i]*2;
		}
		for(int i = 0; i < array.length; i++) {
			System.out.println(doubledArray[i]);
		}
		
	}//EOC MAIN METHOD
			
}//EOC LRQ
