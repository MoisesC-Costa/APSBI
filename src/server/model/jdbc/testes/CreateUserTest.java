package server.model.jdbc.testes;

import server.model.bean.User;
import server.model.jdbc.dao.UserDao;

public class CreateUserTest {
	public static void main(String[] agrs) {
		User user = new User();
		UserDao dao = new UserDao();
		
		user.setUsername("moises");
		user.setPassword("senha");
		user.setEmail("sesiom.br@gmail.com");
		
		dao.createUser(user);
	}
}
