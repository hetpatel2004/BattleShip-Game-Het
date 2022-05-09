

public class BattleShipException extends Exception {
	
	private int in;
	
	public BattleShipException(int in) {
		this.in = in;
	}
	
	public int getIn() {
		return in;
	}
}
