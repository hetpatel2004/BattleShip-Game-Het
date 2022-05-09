import java.util.Arrays;
import java.util.Random;

/* Directory
 * Ship 	- 1
 * HD Trap 	- 2
 * LD Trap 	- 3
 * LS Potion- 4
 * TR Potion- 5
 * SR Potion- 6
 */

public class Location{
	private Rule rule;
	private int [][] location;
	
	//Ship
	private Ship ship;
	private int [][] shipLocation;
	private int [][][] shipGroup;
	private int [][] shipDestroyLocation;
	private int [] shipGroupLength;
	private int shipUnitCount;
	
	
	private Trap trap;
	private int [][] trapLocation;
	private int [][] trapHighLocation;
	private int [][] trapLowLocation;
	private int [][] trapTriggerLocation;
	
	private Potion potion;
	private int[][] potionLocation;
	private int[][] potionLSLocation;
	private int[][] potionTRLocation;
	private int[][] potionSRLocation;
	private int[][] potionTriggerLocation;
	
	
	private int mapRow;
	private int mapCol;
	private Random random = new Random();
	
	
	public Location(int level) {
		rule = new Rule(level);
		mapRow = rule.getMapRow();
		mapCol = rule.getMapCol();
		ship = new Ship(level);
		trap = new Trap(level);
		potion = new Potion (level);
		location = new int[rule.getMapRow()][rule.getMapCol()];
		for (int[] row : location) {
			Arrays.fill(row, 0);
		}
	}
	
	// Ship Methods
	public void initShipLocation() {
		shipUnitCount = 0;
		int shipCount = rule.getShipNum();
		shipLocation = new int[shipCount*rule.getShipLength()[1][0]][2];
		shipDestroyLocation = new int[shipCount*rule.getShipLength()[1][0]][2];
		shipGroup = new int [shipCount][rule.getShipLength()[1][0]][2];
		shipGroupLength = new int [shipCount];
		int[] row = new int[rule.getShipLength()[1][0]];
		int[] col = new int[rule.getShipLength()[1][0]];
		int direction, length;
		Boolean objPlaced;
		
		for(int group = 0; group<shipCount; group++){
			length = ship.randomShipLength();
			objPlaced = true;
			while(objPlaced) {
				row[0] = random.nextInt(mapRow-1);
				col[0] = random.nextInt(mapCol-1);
				direction = random.nextInt(2);
				for (int i=1; i<length; i++) {
					
					switch (direction) {
					case 0:
						row[i] = row[i-1]+1;
						col[i] = col[i-1];
						break;
					case 1:
						row[i] = row[i-1];
						col[i] = col[i-1]+1;
						break;
					}
					if (row[i]>=mapRow || col[i]>=mapCol) {
						objPlaced= true;
						i = length;
					}
					else{
						if(checkLocation(row[i],col[i])!=0) {
							objPlaced = true;
							i = length;
							}
						else {objPlaced = false;}
					}
				}
			}
			for (int i=0; i<length; i ++) {
				shipLocation[shipUnitCount][0]=row[i];
				shipLocation[shipUnitCount][1]=col[i];
				setShipLocation(row[i], col[i]);
				shipGroup[group][i][0] = row[i];
				shipGroup[group][i][1] = col[i];
				shipUnitCount++;
			}
			shipGroupLength[group] = length;
		}
	}
	
	public void setDestroyShipLocation(int row, int col, int shipDestroyCount) {
		shipDestroyLocation[shipDestroyCount][0] = row;
		shipDestroyLocation[shipDestroyCount][1] = col;
	}
	
	public int[][] getDestroyShipLocation() {
		return shipDestroyLocation;
	}
	
	public int getShipUnitCount() {
		return shipUnitCount;
	}
	
	public int checkShipGroup(int row, int col) {
		int group = 0, counter = 0;
		for (int[][] a : shipGroup) {
			for (int[] b : a) {
				if(b[0]==row && b[1]==col) {
					group = counter;
				}
			}
			counter ++;
		}
		return group;
	}
	
	public int checkShipGroupLength (int shipGroup) {
		return shipGroupLength[shipGroup];
	}
	
	public int[][][] getShipGroup(){
		return shipGroup;
	}
	
	public int[][] getShipLocation(){
		return shipLocation;
	}
	
	
	//Trap Methods
	public void initTrapLocation() {
		int trapCount = rule.getTrapNum();
		trapLocation = new int[trapCount][2];
		trapTriggerLocation = new int[trapCount][2];
		int row = 0, col = 0;
		Boolean objPlaced;
		for(int placed=0 ; placed<trapCount  ; placed++){
			objPlaced = true;
			while(objPlaced) {
				row = random.nextInt(mapRow);
				col = random.nextInt(mapCol);
				if(checkLocation(row,col)!=0) {objPlaced= true;}
				else {objPlaced = false;}
			}
			trapLocation[placed][0]=row;
			trapLocation[placed][1]=col;
		}
		
		int trapLDCount, trapHDCount = 0;
		while(trapHDCount == 0 || trapHDCount == 18) {trapHDCount = random.nextInt(trapCount);}
		trapLDCount = trapCount-trapHDCount;
		trapHighLocation = new int[trapHDCount][2];
		trapLowLocation = new int[trapLDCount][2];
		for (int i=0; i<trapHDCount; i++) {
			trapHighLocation[i] = trapLocation[i];
			setTrapHDLocation(trapLocation[i][0],trapLocation[i][1]);
		}
		for (int i=0; i<trapLDCount; i++) {
			trapLowLocation[i] = trapLocation[i+trapHDCount];
			setTrapLDLocation(trapLocation[i+trapHDCount][0],trapLocation[i+trapHDCount][1]);
		}
	}
	
	public void setTrapTriggeredLocation(int row, int col, int trapTriggerCount) {
		trapTriggerLocation[trapTriggerCount][0] = row;
		trapTriggerLocation[trapTriggerCount][1] = col;
	}
	
	public int [][] getTrapLoction(){
		return trapLocation;
	}
	
	public int[][] getTrapTriggerLocation(){
		return trapTriggerLocation;
	}
	
	public int[][] getTrapHDLocation() {
		return trapHighLocation;
	}
	
	public int[][] getTrapLDLocation() {
		return trapLowLocation;
	}
	
	
	//Potion Methods
	public void initPotionLocation() {
		int potionCount = rule.getPotNum();
		potionLocation = new int[potionCount][2];
		potionTriggerLocation = new int[potionCount][2];
		
		int row = 0, col = 0;
		Boolean objPlaced;
		for(int placed=0 ; placed<potionCount  ; placed++){
			objPlaced = true;
			while(objPlaced) {
				row = random.nextInt(mapRow);
				col = random.nextInt(mapCol);
				if(checkLocation(row,col)!=0) {objPlaced= true;}
				else {objPlaced = false;}
			}
			potionLocation[placed][0]=row;
			potionLocation[placed][1]=col;
		}
		int potionSRCount, potionTRCount = 0, potionLSCount = 0;
		while(potionTRCount == 0 || potionTRCount == potionCount) {potionTRCount = random.nextInt(potionCount);}
		while(potionLSCount == 0 || potionLSCount == potionCount - potionTRCount) {potionLSCount = random.nextInt(potionCount-potionTRCount);}
		potionSRCount = potionCount - potionLSCount - potionTRCount;
		potionLSLocation = new int[potionLSCount][2];
		potionTRLocation = new int[potionTRCount][2];
		potionSRLocation = new int[potionSRCount][2];
		for (int i=0; i<potionLSCount; i++) {
			potionLSLocation[i] = potionLocation[i];
			setLSPotLocation(potionLocation[i][0],potionLocation[i][1]);
		}
		for (int i=0; i<potionTRCount; i++) {
			potionTRLocation[i] = potionLocation[i+potionLSCount];
			setTRPotLocation(potionLocation[i+potionLSCount][0],potionLocation[i+potionLSCount][1]);
		}
		for (int i=0; i<potionSRCount; i++) {
			potionSRLocation[i] = potionLocation[i+potionTRCount+potionLSCount];
			setSRPotLocation(potionLocation[i+potionTRCount+potionLSCount][0],potionLocation[i+potionTRCount+potionLSCount][1]);
		}
	}
	
	public void setPotionTriggered(int row, int col, int potionTriggerCount) {
		potionTriggerLocation[potionTriggerCount][0] = row;
		potionTriggerLocation[potionTriggerCount][1] = col;
	}
	
	
	
	//Location Methods
	public int checkLocation(int row, int col) {
		return location[row][col];
	}
	
	public void setShipLocation(int row, int col) {
		location[row][col]=1;
	}
	
	public void setTrapHDLocation(int row, int col) {
		location[row][col]=2;
	}
	
	public void setTrapLDLocation(int row, int col) {
		location[row][col]=3;
	}
	
	public void setLSPotLocation(int row, int col) {
		location[row][col]=4;
	}
	
	public void setTRPotLocation(int row, int col) {
		location[row][col]=5;
	}
	
	public void setSRPotLocation(int row, int col) {
		location[row][col]=6;
	}
	
	public void printMap() {
		System.out.print("   ");
		for (int i=1; i<=rule.getMapCol(); i++) {
			if (i<10) {System.out.print("0"+i+" ");}
			else {System.out.print(i+" ");}
		}
		System.out.println();
		int i = 1;
		for(int[] row : location) {
			if (i<10) {System.out.print("0"+i+"  ");}
			else {System.out.print(i+"  ");}
			for(int col : row) {
				System.out.print(col);
	            System.out.print(" ");
	        }
			System.out.println();
			i++;
        }
	}
}
