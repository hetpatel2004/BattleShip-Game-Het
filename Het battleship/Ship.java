import java.util.Random;

public class Ship{
	private Rule rule;
	private Random random = new Random();
	private int shipDestroyCount;
	private int shipDestroyUnitCount;
	private int shipCount;

	
	public Ship(int level) {
		rule = new Rule(level);
		shipCount = rule.getShipNum();
		shipDestroyCount = 0;
		shipDestroyUnitCount = 0;

	}
	
	public void destroyOneShip() {
		shipDestroyCount++;
	}
	
	public int getShipDestroyCount() {
		return shipDestroyCount;
	}
	
	public void setShipDestroyUnitCount() {
		shipDestroyUnitCount++;
	}
	
	public int getShipDestroyUnitCount() {
		return shipDestroyUnitCount;
	}
	
	public int randomShipLength() {
		int length, min, max;
		min = rule.getShipLength()[0][0];
		max = rule.getShipLength()[1][0];
		length = random.nextInt((max-min)+1) + min;
		return length;
	}
	
	public int getShipNum() {
		return shipCount;
	}
	
}
