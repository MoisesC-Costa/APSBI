package server.controller.logic;

import org.json.JSONObject;

import server.session.Session;

public interface Logic {
	public void exec(JSONObject packet, Session session);
}
