package server.model.jdbc.testes;

import server.model.bean.User;
import server.model.jdbc.dao.UserDao;

public class UpdateUserTest {
	public static void main(String[] args) {
		User user = new User();
		UserDao dao = new UserDao();
		
		user.setId(1);
		user.setUsername("maylane");
		user.setPassword("fofa");
		user.setEmail("email@email.com");
		
		dao.updateUser(user);
		
	}
	
}
