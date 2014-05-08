package ie.logn.data;

public class Competition extends BaseModel<Competition> {

	public Competition(final long id, final String name,
			final CompetitionType competitionType, final GameType gameType) {
		super();
		this.id = id;
		this.competitionType = competitionType;
		this.name = name;
		this.gameType = gameType;
	}

	public Competition(final String name,
			final CompetitionType competitionType, final GameType gameType) {
		super();
		this.name = name;
		this.competitionType = competitionType;
		this.gameType = gameType;
	}

	public String getName() {
		return name;
	}

	public CompetitionType getCompetitionType() {
		return competitionType;
	}

	public GameType getGameType() {
		return gameType;
	}

	private final String name;
	private final CompetitionType competitionType;
	private final GameType gameType;

}
