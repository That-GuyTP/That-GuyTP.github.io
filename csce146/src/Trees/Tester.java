package Trees;

public class Tester {

	public static void main(String[] args) {
		LinkedBSTree<Integer> iBST = new LinkedBSTree<Integer>();
		LinkedBSTree<String> strBST = new LinkedBSTree<String>();
		//LinkedBSTree<Object> pBST = new LinkedBSTree<Object>(); Doesn't work because Objects are not comparable.
		LinkedBSTree<Thing> thingBst = new LinkedBSTree<Thing>(); //Works because Obejects are not comparable but we've made a case to make them comparable.
		
		iBST.add(8);
		iBST.add(16);
		iBST.add(2);
		iBST.add(15);
		iBST.add(7);
		iBST.add(4);
		
		iBST.printInorder();
		
	}//EOC MAIN METHOD

}//EOC Tester
