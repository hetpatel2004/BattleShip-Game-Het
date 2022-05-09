import java.util.*;
import java.io.*;

public class Game {
	
	private int level;
	private Display screen;
	private Message message;
	private Player player;
	private Input input;
	private Map map;
	private Location location;
	private Ship ship;
	private Trap trap;
	private Potion potion;
	private Random random = new Random();
	private Boolean statusWin;
	private Boolean statusLose;
	private String playerName;
	private saveFiles file;
	
	public Game() {
		screen = new Display();
		message = new Message();		
		input = new Input();
		statusWin = false;
		statusLose = false;
		file = new saveFiles();
	}
	
	
	public void Run() {
		screen.displayMessage(message.getMsgWelcome());
		playerName();
		selectLevel();
		construct();
		while(!statusWin || !statusLose) {
			display();
			shoot();
			if (statusWin || statusLose) {display(); break;}
		}
		if(statusWin) {endGameWin();}
		else {endGameLose();}
	}
	
	public void endGameWin() {
		screen.displayMessage("\nCongratulation Captain: " + playerName + ". You have destroyed 5 enemy!\n");
		screen.displayMessageLine("You stepped on " + trap.getTrapTriggerCount() + " traps and acquired  " + potion.getPotionTriggerCount() + " potions.");
		screen.displayMessageLine("Your score: " + player.getStepCount() + " steps\t\t" + player.getLifeCount() + " lifes");
		highScore();
		screen.displayMessage("\nHigh Score Board:\n");
		file.showHighScore();
	
	}
	
	public void endGameLose() {
		screen.displayMessage("\nSorry Captain: You run out of lives...");
		screen.displayMessage("\nGame Over");
		screen.displayMessage("\nHigh Score Board:\n");
	}
	
	public void highScore() {
		Boolean highScore;
		highScore = file.checkScore(player.getStepCount());
		if (highScore) {
			try {
			file.writeToFile(player.getName(), player.getStepCount());
			}
			catch(IOException e){
			}
		}
	}
	
	public void playerName() {
		Boolean error = true;
		screen.displayMessage(message.getMsgName());
		while(error) {
			try {
				playerName = input.inputName();
				error = false;
			}
			catch(Exception e) {
				screen.displayMessage("Invalid input! Please try again");
			}
		}
	}
	
 	public void selectLevel() {
 		Boolean error = true;
		screen.displayMessage(message.getMsgLevelSelection());
		screen.displayMessage(message.getMsgLevel1());
		screen.displayMessage(message.getMsgLevel2());
		screen.displayMessage(message.getMsgLevel3());
		screen.displayMessage("Level Number: ");
		while(error) {
			try {
				level = input.inputInt();
				input.inputLevel(level);
				error = false;
			}
			catch(BattleShipException e){
				screen.displayMessageLine("Your input " + e.getIn() + " is not in the level options. Please try again");
			}
			catch(Exception e) {
				screen.displayMessage("Invalid input! Please try again");
			}
		}
	}
	
	public void construct() {
		ship = new Ship(level);
		trap = new Trap (level);
		potion = new Potion(level);
		map = new Map(level);
		player = new Player(level);
		location = new Location(level);
		location.initShipLocation();
		location.initTrapLocation();
		location.initPotionLocation();
		player.setName(playerName);
	}
	
	public void shoot() {
		Boolean error = true;
		int check, row=0, col=0;
		screen.displayMessage("Where do you wish to shoot?\n");
		screen.displayMessage("Row:");
		while(error) {
			try {
				row = input.inputInt()-1;
				input.inputRow(row);
				error = false;
			}
			catch(BattleShipException e){
				screen.displayMessageLine("Captain! Your target, row " + e.getIn()+ " is out of our shooting range!");
			}
			catch(Exception e) {
				screen.displayMessage("Invalid input! Please try again");
			}
		}
		screen.displayMessage("Column:");
		error = true;
		while(error) {
			try {
				col = input.inputInt()-1;
				input.inputCol(col);
				error = false;
			}
			catch(BattleShipException e){
				screen.displayMessageLine("Captain! Your target, col " + e.getIn() + " is out of our shooting range!");
			}
			catch(Exception e) {
				screen.displayMessage("Invalid input! Please try again");
			}
		}
		check = location.checkLocation(row, col);
		
		screen.displayMessageLine("\n\nYou shot at row " + (row+1) + " column " + (col+1) + ". ");
		
		switch (check) {
		case 0:
			map.setMapMissed(row, col);
			screen.displayMessage(message.getMsgShotMiss());
			break;
			
		case 1:
			int shipGroup = location.checkShipGroup(row, col);
			for (int i = 0; i<location.checkShipGroupLength(shipGroup); i++) {
				map.setMapDestroyShip(location.getShipGroup()[shipGroup][i][0], location.getShipGroup()[shipGroup][i][1]);
				ship.setShipDestroyUnitCount();
			}
			screen.displayMessage(message.getMsgShotShip());
			location.setDestroyShipLocation(row, col, ship.getShipDestroyCount());
			ship.destroyOneShip();
			if(ship.getShipDestroyCount() >= 5) {statusWin = true;}
			break; 
			
		case 2:
			map.setMapTrap(row, col);
			screen.displayMessage(message.getMsgShotHDTrap());
			player.lossTwoLife();
			trap.triggerTrap();
			if(player.getLifeCount() <= 0) {statusLose = true;}
			break;
			
		case 3:
			map.setMapTrap(row, col);
			screen.displayMessage(message.getMsgShotLDTrap());
			player.lossOneLife();
			trap.triggerTrap();
			if(player.getLifeCount() <= 0) {statusLose = true;}
			break;
			
		case 4:
			map.setMapPot(row, col);
			screen.displayMessage(message.getMsgShotLSPot());
			player.addOneLife();
			break;
			
		case 5:
			int[] revealTrap;
			map.setMapPot(row, col);
			revealTrap = revealCoordinate(location.getTrapLoction(),location.getTrapTriggerLocation(),trap.getTrapCount(),trap.getTrapTriggerCount());
			screen.displayMessage(message.getMsgShotTRPot());
			map.setMapRevealTrap(revealTrap[0], revealTrap[1]);
			break;
			
		case 6:
			int[] revealShip;
			map.setMapPot(row, col);
			revealShip = revealCoordinate(location.getShipLocation(),location.getDestroyShipLocation(),location.getShipUnitCount(),ship.getShipDestroyUnitCount());
			int shipgroup = location.checkShipGroup(revealShip[0], revealShip[1]);
			screen.displayMessage(message.getMsgShotSRPot());
			for (int[] a : location.getShipGroup()[shipgroup]) {
				map.setMapRevealShip(a[0], a[1]);
			}
			break;
		} 
		
		player.addOneStep();
	}

	public int[] revealCoordinate(int[][] objLocation, int[][]objExcludeLocation, int objCount, int objExcludeCount) {
		int [][] objRemain = new int [objCount][2];
		int counter = 0;
		for (int[] a : objLocation) {
			for (int[] b : objExcludeLocation) {
				if (a != b) {
					objRemain[counter]=a;
					counter++;
					break;
				}
			}
			if(counter == objCount) {break;}
		}
		int rand = random.nextInt(objCount - objExcludeCount);
		return objRemain[rand];
	}
	
	public void display() {
		screen.displayMessage(message.getMsgDirectory());
		screen.displayMessage("\nCaptain: " + playerName + "\n");
		screen.displayMessage(message.getMsgLevel(level));
		screen.displayMessageLine("Life Count: " +  player.getLifeCount() + "\t\tShip Destroyed: " + ship.getShipDestroyCount() + "\t\tStep Count: " + player.getStepCount() + "\n");
		map.printMap();
	}
}

