package server.controller;

import org.json.JSONObject;

import server.controller.logic.Logic;
import server.session.Session;

public class Controller {
	
	private Controller() {}
	
	public static void execLogic(JSONObject packet, Session session) {
		String logicName = packet.getString("logic");
		String className = "server.controller.logic." + logicName;
		
		System.out.println("Executando Logica: " + logicName);
		try {
			Class<?> cls = Class.forName(className);
			Logic logic = (Logic) cls.getConstructor().newInstance();
			logic.exec(packet, session);
			
		} catch (Exception e) {
			throw new RuntimeException();

		}
		
	}
	
}
