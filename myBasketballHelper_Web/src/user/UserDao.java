package user;

import java.util.List;
import java.util.Map;

public interface UserDao {

	int insert(User user);

	int update(User user, byte[] image);

	int delete(String userAccount);

	List<User> getUser(String teamInfo);
	
	List<User> getManager(String teamInfo);

	User login(String userAccount, String userPassword);
	
	byte[] getImage(String userAccount);
	
	Map<String, String> findById(String userAccount);
	
	int passwordUpdate(User user);
	
	int changePriority(String userAccount, int priority);
}
