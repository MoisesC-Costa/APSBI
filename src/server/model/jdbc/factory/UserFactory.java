package server.model.jdbc.factory;

import server.model.bean.User;
import server.model.exceptions.UserNotFoundException;
import server.model.jdbc.dao.UserDao;

public class UserFactory {

	public static User getUser(UserDao dao, String email) {
		try {
			return dao.getUser(email);
		} catch (UserNotFoundException e) {
			throw new RuntimeException(e);

		}

	}

}
