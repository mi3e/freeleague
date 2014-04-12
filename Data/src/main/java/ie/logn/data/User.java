package ie.logn.data;

public class User extends BaseModel<User> {

	public User(final long id, final String email, final String firstName,
			final String lastName, final Long age) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public User(final String email, final String firstName,
			final String lastName, final Long age) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Long getAge() {
		return age;
	}

	private final String email;
	private final String firstName;
	private final String lastName;
	private final Long age;
}
