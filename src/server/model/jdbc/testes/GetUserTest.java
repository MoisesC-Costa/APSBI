package server.model.jdbc.testes;

import server.model.bean.User;
import server.model.jdbc.dao.UserDao;
import server.model.jdbc.factory.UserFactory;

public class GetUserTest {
	
	public static void main(String[] args) {
		UserDao dao = new UserDao();
	
		User user = UserFactory.getUser(dao, "sesiom.br@gmail.com");
	
		User another = new User();
	
		another.setEmail("sesiom.br@gmail.com");
		another.setPassword("senha");
		
		System.out.println(user.equals(another));
	}
}
