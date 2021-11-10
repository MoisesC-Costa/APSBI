package testes;

import org.json.JSONObject;

import cliente.boundary.Boundary;
import server.Server;

public class ConnectionTest {

	public static void main(String[] args) {
		
		Runnable serverRunner = new Runnable() {
			
			@Override
			public void run() {
				Server.main(args);
			}
			
		};
		
		new Thread(serverRunner).start();
		
		Boundary bound = new Boundary();
		
		JSONObject test = bound.request(new JSONObject("{'logic':'TesteLogic', 'message':':)'}"));
		
		System.out.print(test.getString("message"));
	}

}
