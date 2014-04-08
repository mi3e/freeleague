package ie.logn.data;

public class LeaguePosition {
	
	public LeaguePosition(final long teamId, final long leagueId, final int position,
			final int homeWins, final int awayWins, final int homeDraws, final int awayDraws,
			final int homeLoses, final int awayLoses, final int homeGoals, final int awayGoals) {
		super();
		this.teamId = teamId;
		this.leagueId = leagueId;
		this.position = position;
		this.homeWins = homeWins;
		this.awayWins = awayWins;
		this.homeDraws = homeDraws;
		this.awayDraws = awayDraws;
		this.homeLoses = homeLoses;
		this.awayLoses = awayLoses;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}
	
	public long getTeamId() {
		return teamId;
	}
	public long getLeagueId() {
		return leagueId;
	}
	public int getPosition() {
		return position;
	}
	public int getHomeWins() {
		return homeWins;
	}
	public int getAwayWins() {
		return awayWins;
	}
	public int getHomeDraws() {
		return homeDraws;
	}
	public int getAwayDraws() {
		return awayDraws;
	}
	public int getHomeLoses() {
		return homeLoses;
	}
	public int getAwayLoses() {
		return awayLoses;
	}
	public int getHomeGoals() {
		return homeGoals;
	}
	public int getAwayGoals() {
		return awayGoals;
	}
	
	private final long teamId;
	private final long leagueId;
	private final int position;
	private final int homeWins;
	private final int awayWins;
	private final int homeDraws;
	private final int awayDraws;
	private final int homeLoses;
	private final int awayLoses;
	private final int homeGoals;
	private final int awayGoals;
}
