package server.controller.logic;

import org.json.JSONObject;

import server.session.Session;

public class TesteLogic implements Logic{

	@Override
	public void exec(JSONObject packet, Session session) {
		session.response(packet);
	}
	
}
