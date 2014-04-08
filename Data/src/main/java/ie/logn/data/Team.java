package ie.logn.data;

public class Team implements IdAndType<Team> {
	
	public Team(final long id, final String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Team(final String name) {
		super();
		this.name = name;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public Class<? extends Team> getType() {
		return this.getClass();
	}	

	private long id;
	private final String name;

}
