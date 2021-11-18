package server.controller.logic;

import org.json.JSONObject;

import server.session.Session;

public class UploadDBQueimadasLogic implements Logic {

	@Override
	public void exec(JSONObject packet, Session session) {
		String autToken = packet.getString("token");
		System.out.println(Session.sessionTokens);
		
		// Verificar se o usuario está autenticado
		boolean tokenNotFound = true;
		for (String token : Session.sessionTokens) {
			if (token.equals(autToken)) {
				tokenNotFound = false;
				
			}
			
		}
		
		if (tokenNotFound) {
			throw new RuntimeException("Usuario não autenticado!");
			
		} else {
			JSONObject csv = packet.getJSONObject("csv");
			
			session.response(new JSONObject("{'code':true}"));
			
		}
		
	}

}
