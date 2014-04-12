package ie.logn.data;

public class Score {
	
	public Score(long goals, long points) {
		super();
		this.goals = goals;
		this.points = points;
	}
	
	public Score(long goals)
	{
		this.goals = goals;
		this.points = LONGZERO;
	}
	
	public Long getGoals() {
		return goals;
	}
	public Long getPoints() {
		return points;
	}
	
	private Long goals = null;
	private Long points = null;

	private static Long LONGZERO = new Long(0);
	public static Score ZERO = new Score(0);
	public static Score ONE = new Score(1);
	public static Score TWO = new Score(2);
	public static Score THREE = new Score(3);
	public static Score FOUR = new Score(4);
	public static Score FIVE = new Score(5);
	public static Score SIX = new Score(6);
	public static Score SEVEN = new Score(7);
}
