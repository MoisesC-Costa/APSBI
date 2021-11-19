package server.session;

import java.io.PrintStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONObject;

import server.controller.Controller;
import server.model.bean.User;

public class Session implements Runnable{
	public static Set<String> sessionTokens = new HashSet<>();
	public static Set<String> userActives = new HashSet<>();
	
	private PrintStream out;
	private Scanner scanner;
	private String token;
	private User user;
	
	public Session(Socket cliente) {
		this.out = CommunicationFactory.getPrintStream(cliente);
		this.scanner = CommunicationFactory.getScanner(cliente);
	}

	
	// Abrindo o canal para receber as requisições
	@Override
	public void run() {
		
		while (scanner.hasNext()) {
			JSONObject packet = new JSONObject(scanner.nextLine());
			this.request(packet);
		}
		
		delAutenticateAtributes();
		
	}
	
	public void request(JSONObject packet) {
		
		try {
			Controller.execLogic(packet, this);

		} catch (Exception e) {
			JSONObject erroPacket = new JSONObject();
			String message = e.getMessage();
			
			erroPacket.put("code", false);
			erroPacket.put("description", message == null ? "GenericErro" : message);
			
			System.out.println(e.getMessage());
			response(erroPacket);
			
		}
	}
	
	public void response(JSONObject packet) {		
			out.println(packet.toString());
	}

	public void setAutenticateAtributes(String token, User user) {
		this.token = token;
		this.user = user;
		
		Session.sessionTokens.add(token);
		Session.userActives.add(user.getEmail());
		
		System.out.println(Session.sessionTokens);
	}

	private void delAutenticateAtributes() {
		Session.sessionTokens.remove(token);
		Session.userActives.remove(user.getEmail());
		
	}
	
}
