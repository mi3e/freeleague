package ie.logn.data;

public class Competition implements IdAndType<Competition> {
	
	public Competition(final long id, final String name, final CompetitionType competitionType) {
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
	
	@Override
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public CompetitionType getCompetitionType() {
		return competitionType;
	}

	@Override
	public Class<? extends Competition> getType() {
		return this.getClass();
	}
	
	private long id;
	private final String name;
	private final CompetitionType competitionType;
	

}
