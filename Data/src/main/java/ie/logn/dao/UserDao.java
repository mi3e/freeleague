package ie.logn.dao;

import ie.logn.data.User;

public interface UserDao {
	
	User getUser(long userId);
	
	void saveUser(User user);
	
	void deleteUser(long userId);

}
