package HotPotato;

import java.util.Random;

public class HotPotatoGame {

	public static final int MIN_RAND_TIME = 10;
	public static final int MAX_RAND_TIME = 21;
	QueueInterface<String> players;
	private int currentTime;
	
	public HotPotatoGame() {
		init();
	}
	
	private void init() {
		players = new LinkedListQueue<String>();
	}
	
	public void resetTime() {
		Random r = new Random();
		this.currentTime = r.nextInt(MAX_RAND_TIME)+MIN_RAND_TIME;
	}
	
	public void addPlayer(String xName) {
		if(xName == null) {
			return;
		}//EOC IF
		players.enqueue(xName);
	}
	
	public String getCurrentPlayer() {
		return players.dequeue();
	}
	
	public boolean hasLost(int time) {
		this.currentTime -= time;
		return this.currentTime <= 0;
	}
	
	public boolean getWIn() {
		return players.size() <= 1;
	}
	
}//EOC HotPotatoGame
