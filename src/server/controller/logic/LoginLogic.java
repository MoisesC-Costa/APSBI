package server.controller.logic;

import org.json.JSONObject;

import server.factory.SecureTokenFactory;
import server.model.bean.User;
import server.model.jdbc.dao.UserDao;
import server.model.jdbc.factory.UserFactory;
import server.session.Session;

public class LoginLogic implements Logic{

	@Override
	public void exec(JSONObject packet, Session session) {
		JSONObject response = new JSONObject();
		UserDao dao = new UserDao();

		if (session.getToken() == null) {

			// Recuperando o usuario da requisi��o
			User user = new User();
			user.setEmail(packet.getString("email"));
			user.setPassword(packet.getString("password"));

			// Recuperando o usuari do banco de dados

			User another = UserFactory.getUser(dao, packet.getString("email"));

			// Comparando e vendo se os dados d�o match
			if (user.equals(another)) {
				System.out.println("Usuario autenticado!");
				String token = SecureTokenFactory.getUserToken();
				
				response.put("code", true);
				response.put("token", token);
				
				session.setToken(token);
				session.response(response);

			} else {
				System.out.println("Usuario n�o Autenticado!");

				response.put("code", false);
				response.put("description", "Usuario ou Senha invalidos");

				session.response(response);
			}
			
		} else {
			throw new RuntimeException();

		}

	}

}
