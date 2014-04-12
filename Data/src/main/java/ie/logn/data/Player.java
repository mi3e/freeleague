package ie.logn.data;

public class Player extends BaseModel<Player> {

	public Player(final long id, final long team, final String firstName,
			final String lastName, final String phoneNumber) {
		super();
		this.id = id;
		this.team = team;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public Player(final long team, final String firstName,
			final String lastName, final String phoneNumber) {
		super();
		this.team = team;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public long getTeam() {
		return team;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	private final long team;
	private final String firstName;
	private final String lastName;
	private final String phoneNumber;

}
