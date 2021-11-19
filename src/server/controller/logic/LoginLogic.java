package server.controller.logic;

import org.json.JSONObject;

import server.factory.SecureTokenFactory;
import server.model.bean.User;
import server.model.jdbc.dao.UserDao;
import server.session.Session;

public class LoginLogic implements Logic{

	@Override
	public void exec(JSONObject packet, Session session) {
		JSONObject response = new JSONObject();
		UserDao dao = new UserDao();

		try {
			
			User user = new User();
			
			user.setEmail(packet.getString("email"));
			user.setPassword(packet.getString("password"));
			
			User test = dao.getUser(user.getEmail());
			
			// Impedindo que exista varias sess�es para o mesmo usuario
			if (Session.userActives.contains(user.getEmail())) {
				response.put("code", false);
				response.put("description", "Usuario j� logado");
				
			} else if (test.equals(user)) {
				//Usuario Logado com sucesso
				response.put("code", true);
				
				String token = SecureTokenFactory.getUserToken();
				session.setAutenticateAtributes(token, user);
				
				response.put("token", token);
				
			} else {
				// Caso as informa��es n�o correspondem
				response.put("code", false);
				response.put("description", "Senha ou E-Mail invalidos");
				
			}
			
			session.response(response);
			
		} catch(Exception e) {
			throw new RuntimeException(e);
			
		}
		
	}

}
