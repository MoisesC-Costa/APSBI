package server.controller.logic;

import org.json.JSONObject;

import server.session.Session;

public class GetInfoUsuario implements Logic {

	@Override
	public void exec(JSONObject packet, Session session) {
		JSONObject response = new JSONObject();
		
		try {
			String nome = session.getUser().getNome();
			String email = session.getUser().getEmail();
			
			response.put("code", true);
			response.put("email", email);
			response.put("nome", nome);
						
		} catch (Exception e) {
			response.put("code", false);
			response.put("description", "Ocorreu um erro no servidor");
			
		}
		
		session.response(response);
		
	}
	
}
