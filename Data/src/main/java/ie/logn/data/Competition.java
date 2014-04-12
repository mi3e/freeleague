package ie.logn.data;

public class Competition extends BaseModel<Competition> {

	public Competition(final long id, final String name,
			final CompetitionType competitionType) {
		super();
		this.id = id;
		this.competitionType = competitionType;
		this.name = name;
	}

	public Competition(final String name, final CompetitionType competitionType) {
		super();
		this.name = name;
		this.competitionType = competitionType;
	}

	public String getName() {
		return name;
	}

	public CompetitionType getCompetitionType() {
		return competitionType;
	}

	private final String name;
	private final CompetitionType competitionType;

}
