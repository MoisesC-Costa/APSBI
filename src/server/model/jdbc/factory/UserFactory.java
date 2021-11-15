package server.model.jdbc.factory;

import server.model.bean.User;
import server.model.exceptions.UserNotFoundException;
import server.model.jdbc.dao.UserDao;

public class UserFactory {

	public static User getUserByUsername(UserDao dao, String username) {
		try {
			return dao.getUserByUsername(username);	

		} catch (UserNotFoundException e) {
			User user = new User();
			user.setUsername("");
			user.setPassword("");
			
			return user;
		}
		
	}
	
}
