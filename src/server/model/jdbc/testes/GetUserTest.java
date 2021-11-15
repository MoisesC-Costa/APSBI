package server.model.jdbc.testes;

import server.model.bean.User;
import server.model.jdbc.dao.UserDao;

public class GetUserTest {
	
	public static void main(String[] args) {
		User user = new User();
		UserDao dao = new UserDao();
		
		user.setId(1);
		dao.getUser(user);
		
	}
	
}
