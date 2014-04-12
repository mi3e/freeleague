package ie.logn.data;

public class Team extends BaseModel<Team> {

	public Team(final long id, final String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Team(final String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private final String name;

}
