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

		// Recuperando o usuario da requisição
		User user = new User();
		user.setUsername(packet.getString("username"));
		user.setPassword(packet.getString("password"));
		
		// Recuperando o usuari do banco de dados
		User another = dao.getUserByUsername(user.getUsername());
		
		
		// Comparando e vendo se os dados dão match
		if (user.equals(another)) {
			System.out.println("Usuario autenticado!");

			response.put("code", 1);
			response.put("token", SecureTokenFactory.getUserToken());
			
			session.response(response);

		} else {
			System.out.println("Usuario não Autenticado!");

			response.put("code", 0);
			response.put("description", "Usuario ou Senha invalidos");
			
		}
		
	}

}
