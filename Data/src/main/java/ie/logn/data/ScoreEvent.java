package ie.logn.data;

public class ScoreEvent extends Event {

	public ScoreEvent(int time, long player, long otherPlayer,
			ScoreType scoreType) {
		super(EventType.score, time, player, otherPlayer);
		this.scoreType = scoreType;
	}

	public ScoreEvent(long id, int time, long player, long otherPlayer,
			ScoreType scoreType) {
		super(id, EventType.score, time, player, otherPlayer);
		this.scoreType = scoreType;
	}

	public final ScoreType getScoreType() {
		return scoreType;
	}

	private final ScoreType scoreType;
}
