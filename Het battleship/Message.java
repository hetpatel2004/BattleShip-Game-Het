
public class Message {
	private String msgDirectory = ("\nDirectory \n# is the fog \nX is destroyed enemy ship \nO is revealed enemy ship \n@ is triggered trap \nT is revealed trap \n8 is triggered potion\n");
	private String msgWelcome = ("Welcome to Battle Ship\n");
	private String msgGameover = ("Game Over!");
	private String msgShotOut = ("Coordinate Out of Bound\n");
	private String msgShotMiss = ("Nothing is here, Captain!\n");
	private String msgShotShip = ("Gotcha! Captian! You shot an enemy ship!\n");
	private String msgShotLDTrap = ("It's a trap! You lose 1 life...\n");
	private String msgShotHDTrap = ("Boom! A huge trap! You lose 2 life!\n");
	private String msgShotLSPot = ("Life Potion! You gain 1 life!\n");
	private String msgShotTRPot = ("A trap just revealed! Avoid it, Captain!\n");
	private String msgShotSRPot = ("Captain, look! A ship just revealed.\n");
	private String msgLevelSelection = ("Please select a level:\n");
	private String msgLevel1 = ("1) Beginner     >> 80 ships   10 traps   18 potions\n");
	private String msgLevel2 = ("2) Intermediate >> 50 ships   20 traps   18 potions\n");
	private String msgLevel3 = ("3) Advance      >> 20 ships   30 traps   18 potions\n");
	private String msgName = ("What is your name? Captain:");

	
	public String getMsgLevel(int level) {
		switch(level) {
		case 1:
			return ("Beginner >> 80 ships   10 traps   18 potions\n");
			
		case 2:
			return ("Intermediate >> 50 ships   20 traps   18 potions\n");
			
		case 3:
			return ("Advance >> 20 ships   30 traps   18 potions\n");
		}
		return "";
	}
	public String getMsgDirectory() {
		return msgDirectory;
	}
	
	public String getMsgName() {
		return msgName;
	}
		
	public String getMsgWelcome() {
		return msgWelcome;
	}
	
	public String getMsgGameover() {
		return msgGameover;
	}
	
	public String getMsgShotOut() {
		return msgShotOut;
	}
	
	public String getMsgShotMiss() {
		return msgShotMiss;
	}
	
	public String getMsgShotShip() {
		return msgShotShip;
	}
	
	public String getMsgShotLDTrap() {
		return msgShotLDTrap;
	}
	
	public String getMsgShotHDTrap() {
		return msgShotHDTrap;
	}
	
	public String getMsgShotLSPot() {
		return msgShotLSPot;
	}
	
	public String getMsgShotTRPot() {
		return msgShotTRPot;
	}
	
	public String getMsgShotSRPot() {
		return msgShotSRPot;
	}
	
	public String getMsgLevelSelection() {
		return msgLevelSelection;
	}
	
	public String getMsgLevel1() {
		return msgLevel1;
	}
	
	public String getMsgLevel2() {
		return msgLevel2;
	}
	
	public String getMsgLevel3() {
		return msgLevel3;
	}
	
	
}
