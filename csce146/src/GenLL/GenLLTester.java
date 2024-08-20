package GenLL;

public class GenLLTester {

	public static void main(String[] args) {
		GenLL<Integer> iLL = new GenLL<Integer>(); //This is where you turn a generic LL into the data type you want!
		GenLL<Double> dLL = new GenLL<Double>();
		GenLL<String> strLL = new GenLL<String>();
		
		//TEST 1
		for (int i = 4; i >= 0; i--) {
			iLL.add(i);
			dLL.add((double)i);
			strLL.add("str: "  + i);
		}//EOC FOR
		iLL.print();
		dLL.print();
		strLL.print();
		
		
	}//EOC MAIN METHOD

}//EOC GenLLTester
