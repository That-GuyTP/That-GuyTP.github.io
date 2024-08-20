package Lab08;

//Thomas Peterson

public class OrderScheduler {

	//INSTANCE VARIABLES
	public MinHeap<Order> Orders = new MinHeap<Order>();
	public Order currentOrder = new Order();
	public int currentMinute = 0;
	public int totalOrders = 0;
	public int summedWaitingTimes = 0;
	
	//ADDORDER METHOD
	public void addOrder(Order xOrder) {
		if(currentOrder.getCustomer().equals("none")) {
			currentOrder = xOrder;
			Orders.add(xOrder);
		}else {
			Orders.add(xOrder);
		}//EOC I-E
		totalOrders++;
	}//EOC addOrder
	
	//ADVANCEDONEMINUTE METHOD
	public void advanceOneMinute() {
		currentMinute++;
		currentOrder.cookForOneMinute();
		if(currentOrder.isDone()) {
			summedWaitingTimes += currentMinute - currentOrder.getArrivalTime();
			Orders.remove();
		}//EOC IF
	}//EOC advanceOneMInutes
	
	//ISDONE METHOD
	public boolean isDone() {
		return currentOrder.isDone();
	}//EOC isDone
	
	//GETAVERAGEWAITINGTIME METHOD
	public double getAverageWaitingTime() {
		return summedWaitingTimes / totalOrders;
	}//EOC getAverageWaitingTime
	
	//GETCURRENTORDER
	//This method wasn't listed in the lab instructions but the driver file calls on a method with this name.
	public Order getCurrentOrder() {
		return this.currentOrder;
	}//EOC getCurrentOrder
	
}//EOC OrderScheduler
