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
			JSONObject erroPacket = new JSONObject("{'code':false, 'description':'GenericErro'}");
			System.out.println(e.getMessage());
			response(erroPacket);
			
		}
	}
	
	public void response(JSONObject packet) {		
			out.println(packet.toString());
	}

	public void setToken(String token) {
		this.token = token;
		Session.sessionTokens.add(token);
		System.out.print(Session.sessionTokens);
	}
	
	public String getToken() {
		return this.token;
	}
		
}
