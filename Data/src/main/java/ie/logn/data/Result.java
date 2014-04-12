package ie.logn.data;

public class Result extends BaseModel<Result> {

	public Result(final long id, final long fixture, final Score homeScore,
			final Score awayScore) {
		super();
		this.id = id;
		this.fixture = fixture;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	public Result(final long fixture, final Score homeScore,
			final Score awayScore) {
		super();
		this.fixture = fixture;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	public long getFixture() {
		return fixture;
	}

	public Score getHomeScore() {
		return homeScore;
	}

	public Score getAwayScore() {
		return awayScore;
	}

	private final long fixture;
	private final Score homeScore;
	private final Score awayScore;

}
