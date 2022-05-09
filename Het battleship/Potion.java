
public class Potion {
	Location location;
	private Rule rule;
	private int potionCount;
	private int potionTriggerCount;

	public Potion(int level) {
		rule = new Rule(level);
		potionCount = rule.getPotNum();
		potionTriggerCount = 0;
	}
	
	public int getPotionCount() {
		return potionCount;
	}

	public void potionTrigger(int[]a) {
		potionTriggerCount++;
	}
	
	public int getPotionTriggerCount() {
		return potionTriggerCount;
	}
	

}
