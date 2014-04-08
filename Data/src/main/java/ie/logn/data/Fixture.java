package ie.logn.data;

import java.util.Date;

public class Fixture implements IdAndType<Fixture>{
		public Fixture(final long id, final long competition, final long homeTeam, final long awayTeam, final Date matchDate) {
		super();
		this.id = id;
		this.competition = competition;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDate = matchDate;
	}
	
	public Fixture(final long competition, final long homeTeam, final long awayTeam, final Date matchDate) {
		super();
		this.competition = competition;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDate = matchDate;
	}

	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}

	public long getCompetition() {
		return competition;
	}
	
	public long getHomeTeam() {
		return homeTeam;
	}
	
	public long getAwayTeam() {
		return awayTeam;
	}

	public Date getMatchDate() {
		return matchDate;
	}
	@Override
	public Class<? extends Fixture> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	private long id;
	private final long competition;
	private final long homeTeam;
	private final long awayTeam;
	private final Date matchDate;

}
