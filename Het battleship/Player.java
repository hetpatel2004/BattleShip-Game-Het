
public class Player {
	private String name;
	private int lifeCount;
	private int stepCount;
	private Rule rule;
	
	
	public Player(int level) {
		rule = new Rule(level);
		lifeCount = rule.getLifeNum();
		stepCount = 0;
	}
	
	public void addOneStep() {
		stepCount++;
	}
	
	public void lossTwoLife() {
		lifeCount = lifeCount - 2;
	}
	
	public void lossOneLife() {
		lifeCount--;
	}
	
	public void addOneLife() {
		lifeCount++;
	}
	
	
	public int getLifeCount() {
		return lifeCount;
	}
	
	public int getStepCount() {
		return stepCount;
	}
	
	public void setName(String a) {
		name = a;
	}
	
	public String getName() {
		return name;
	}
	
	

}
