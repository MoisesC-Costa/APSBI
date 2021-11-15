package testes.server;

import org.json.JSONObject;

import cliente.boundary.Boundary;
import server.Server;

public class LoginTeste {

	public static void main(String[] args) {
		Runnable serverRunner = new Runnable() {
			
			@Override
			public void run() {
				Server.main(args);
			}
			
		};
		
		new Thread(serverRunner).start();
		Boundary bound = new Boundary();
		JSONObject message = new JSONObject();
		
		message.put("logic", "LoginLogic");
		message.put("password", "fofa");
		message.put("username", "maylane");
	
		JSONObject response = bound.request(message);
		System.out.print(response.get("token"));
		
	}
	
}
