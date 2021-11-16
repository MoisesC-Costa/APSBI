package server.controller.logic;

import org.json.JSONObject;

import server.factory.SecureTokenFactory;
import server.model.bean.User;
import server.model.jdbc.dao.UserDao;
import server.session.Session;

public class SignupLogic implements Logic{

	@Override
	public void exec(JSONObject packet, Session session) {
		User user = new User();
		UserDao dao = new UserDao();
		JSONObject message = new JSONObject();
		
		String username = packet.getString("username");
		String password = packet.getString("password");
		String email = packet.getString("email");
		
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		try {
			dao.createUser(user);
			String token = SecureTokenFactory.getUserToken();
			
			message.put("code", 1);
			message.put("token", token);
			
			session.setToken(token);
			session.response(message);
			
		} catch(Exception e) {
			session.response(new JSONObject("{'code':0, 'description':'Valores repetidos'}"));
			
		}
		
	}
	

}
