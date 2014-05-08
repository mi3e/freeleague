package ie.logn.data;

public class Score extends BaseModel<Score> {

	public Score(long id, long primaryScore, long secondaryScore) {
		super(id);
		this.primaryScore = primaryScore;
		this.secondaryScore = secondaryScore;
	}

	public Score(long primaryScore, long secondaryScore) {
		super();
		this.primaryScore = primaryScore;
		this.secondaryScore = secondaryScore;
	}

	public Score(long primaryScore) {
		super();
		this.primaryScore = primaryScore;
	}

	public long getPrimaryScore() {
		return primaryScore;
	}

	public long getSecondaryScore() {
		return secondaryScore;
	}

	private long primaryScore;
	private long secondaryScore;
}
