
public class Trap {
	private Rule rule;
	private int trapCount;
	private int trapTriggerCount;


	public Trap(int level) {
		rule = new Rule(level);
		trapTriggerCount = 0;
		trapCount = rule.getTrapNum();
	}
	
	public void triggerTrap() {
		trapTriggerCount++;
	}
	
	public int getTrapTriggerCount() {
		return trapTriggerCount;
	}
	
	public int getTrapCount() {
		return trapCount;
	}
	

	
	
}
