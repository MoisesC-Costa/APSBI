package server.model.jdbc.testes;

import server.model.bean.User;
import server.model.jdbc.dao.UserDao;

public class GetUserByFieldTest {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		
		User user = dao.getUserByUsername("maylane");
		
		
	}
}
