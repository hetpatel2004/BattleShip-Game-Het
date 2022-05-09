import java.util.Scanner;

public class Input {
	private Scanner input;
	
	public Input() {
		input= new Scanner(System.in);
	}
	
	public int inputInt() {
		return input.nextInt();
	}
	
	public void inputLevel(int level) throws BattleShipException{
		if(level >= 1 && level <= 3) {}
		else {throw new BattleShipException(level);}
	}
	
	public void inputRow(int row) throws BattleShipException{
		if(row >= 1 && row <= 20) {}
		else {throw new BattleShipException(row);}
	}
	
	public void inputCol(int col) throws BattleShipException{
		if(col >= 1 && col <= 60) {}
		else {throw new BattleShipException(col);}
	}
	
	public String inputName() {
		return input.nextLine();
	}

}
