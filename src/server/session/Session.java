package server.session;

import java.io.PrintStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

import org.json.JSONObject;

import server.controller.Controller;

public class Session implements Runnable{
	public static Set<String> sessionTokens = new HashSet<>();
	
	private PrintStream out;
	private Scanner scanner;
	private String token;
	private boolean authenticated = false;
	
	public Session(Socket cliente) {
		this.out = CommunicationFactory.getPrintStream(cliente);
		this.scanner = CommunicationFactory.getScanner(cliente);
	}
	
	@Override
	public void run() {
		
		while (scanner.hasNext()) {
			JSONObject packet = new JSONObject(scanner.nextLine());
			this.request(packet);
		}
		
		sessionTokens.remove(token);
		
	}
	
	public void request(JSONObject packet) {
		
		try {
			Controller.execLogic(packet, this);

		} catch (Exception e) {
			JSONObject erroPacket = new JSONObject("{'code':0, 'description':'GenericErro'}");
			response(erroPacket);
			
		}
	}
	
	public void response(JSONObject packet) {
		
		if (authenticated) {
			out.println(packet.toString());

		} else {
			System.out.println("Usuario ainda não autenticado");
			
			token = packet.getString("token");
			sessionTokens.add(token);
			out.println(packet.toString());

			authenticated = true;
		}

	}	
		
}
