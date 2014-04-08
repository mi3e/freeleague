package ie.logn.dao.springjdbc;

import ie.logn.dao.UserDao;
import ie.logn.data.User;

import javax.sql.DataSource;

import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class UserJdbcDao extends SpringJdbcDao<User> implements UserDao {

	public UserJdbcDao(final DataSource dataSource,  final DataFieldMaxValueIncrementer incrementer)
	{
		super(dataSource, incrementer);
		this.insert.withTableName("USER").setGeneratedKeyName("ID");
	}
	
	@Override
	public User getUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		super.saveIdentityObject(user);

	}

	@Override
	public void deleteUser(long userId) {
		super.deleteObject(userId);
	}
}
