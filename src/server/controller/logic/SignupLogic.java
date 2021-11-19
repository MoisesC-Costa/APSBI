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
		
		String nome = packet.getString("nome");
		String email = packet.getString("email");
		String password = packet.getString("password");
		
		user.setNome(nome);
		user.setPassword(password);
		user.setEmail(email);
		
		try {
			dao.createUser(user);
			String token = SecureTokenFactory.getUserToken();
			
			message.put("code", true);
			message.put("token", token);
			
			session.setAutenticateAtributes(token, user);
			session.response(message);
			
		} catch(Exception e) {
			session.response(new JSONObject("{'code':false, 'description':'Valores repetidos'}"));
			
		}
		
	}
	

}
