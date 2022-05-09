import java.util.Arrays;

/* Directory
 * empty			- 0 " "
 * Ship 			- 1 "X"
 * Reveal ship		- 2 "O" (Not Destroyed)
 * Triggered Trap	- 3 "*"
 * Reveal Trap		- 4 "-" (Not Triggered)
 * Triggered Potion	- 5 "@"
 */

public class Map {
	Rule rule;
	private String [][] map;
	
	public Map(int level) {
		rule = new Rule(level);
		map = new String [rule.getMapRow()][rule.getMapCol()];
		for (String[]row : map) {
			Arrays.fill(row, "#");
		}
	}
	
	public void printMap() {
		System.out.print("   ");
		for (int i=1; i<=rule.getMapCol(); i++) {
			if (i<10) {System.out.print("0"+i+" ");}
			else {System.out.print(i+" ");}
		}
		System.out.println();
		int i = 1;
		for(String[] row : map) {
			if (i<10) {System.out.print("0"+i+"  ");}
			else {System.out.print(i+"  ");}
			for(String col : row) {
				System.out.print(col);
	            System.out.print("  ");
	        }
			System.out.println();
			i++;
        }
	}
	
	public void setMapMissed(int row, int col) {
		map[row][col] = " ";
	}
	
	public void setMapDestroyShip(int row, int col) {
		map[row][col] = "X";
	}
	
	public void setMapRevealShip(int row, int col) {
		map[row][col] = "O";
	}
	
	public void setMapRevealTrap(int row, int col) {
		map[row][col] = "T";
	}
	
	
	public void setMapTrap(int row, int col) {
		map[row][col] = "@";
	}
	
	public void setMapPot(int row, int col) {
		map[row][col] = "8";
	}
	
	
	
	
		
	
	
}
