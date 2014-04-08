package ie.logn.data;


public class Result implements IdAndType<Result>{
	
	public Result(final long id, final long fixture, final String homeScore, final String awayScore) {
		super();
		this.id = id;
		this.fixture = fixture;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	public Result(final long fixture, final String homeScore, final String awayScore) {
		super();
		this.fixture = fixture;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	@Override
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getFixture() {
		return fixture;
	}

	public String getHomeScore() {
		return homeScore;
	}

	public String getAwayScore() {
		return awayScore;
	}

	@Override
	public Class<? extends Result> getType() {
		// TODO Auto-generated method stub
		return this.getClass();
	}	
	
	private long id;
	private final long fixture;
	private final String homeScore;
	private final String awayScore;

}
