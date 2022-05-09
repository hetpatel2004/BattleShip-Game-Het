
public class Rule {
	private int ship_num;
	private int trap_num;
	private int pot_num;
	private final int life_num = 15;
	private final int map_row = 20;
	private final int map_col = 60;
	private final int[] beginner = {80, 10, 18};
	private final int[] intermediate= {50, 20, 18};
	private final int[] advance = {20, 30, 18};
	private final int[][] shipLength = {{3},{5}};

	public Rule(int a) {
		switch (a){
		case 1:
			ship_num = beginner[0];
			trap_num = beginner[1];
			pot_num = beginner[2];
			break;
			
		case 2:
			ship_num = intermediate[0];
			trap_num = intermediate[1];
			pot_num = intermediate[2];
			break;
		case 3:
			ship_num = advance[0];
			trap_num = advance[1];
			pot_num = advance[2];
			break;
		}
	}
	
	
	public int getShipNum() {
		return ship_num;
	}
	
	public int getTrapNum() {
		return trap_num;
	}
	
	public int getPotNum() {
		return pot_num;
	}
	
	public int[][] getShipLength() {
		return shipLength;
	}
	
	public int getLifeNum() {
		return life_num;
	}
	
	public int getMapRow() {
		return map_row;
	}
	
	public int getMapCol() {
		return map_col;
	}
}

