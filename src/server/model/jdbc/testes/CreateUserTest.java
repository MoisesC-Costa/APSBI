package server.model.jdbc.testes;

import server.model.bean.User;
import server.model.jdbc.dao.UserDao;

public class CreateUserTest {
	public static void main(String[] agrs) {
		User user = new User();
		UserDao dao = new UserDao();
		
		user.setNome("Moises Costa Caldas");
		user.setPassword("sesiom.br@gmail.com");
		user.setEmail("senha");
		
		dao.createUser(user);
	}
}
