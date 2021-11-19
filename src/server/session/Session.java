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
	
	// Nova Requisição do cliente
	public void request(JSONObject packet) {
		
		try {
			Controller con = new Controller(packet, this);
			new Thread(con).start();

		} catch (Exception e) {
			JSONObject erroPacket = new JSONObject();
			String message = e.getMessage();
			
			erroPacket.put("code", false);
			erroPacket.put("description", message == null ? "GenericErro" : message);
			
			System.out.println(e.getMessage());
			response(erroPacket);
			
		}
	}
	
	// Resposta para o cliente
	public void response(JSONObject packet) {		
			out.println(packet.toString());
	}

	// Sistema para não ter 2 usuarios logado no mesmo cadastro
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

	// Sistema para autenticar as tokens
	public static void autToken(String token) {
		boolean tokenNotFound = true;
		for (String item : Session.sessionTokens) {
			if (item.equals(token)) {
				tokenNotFound = false;

			}

		}

		if (tokenNotFound) {
			throw new RuntimeException("Usuario não autenticado!");
		}
		
	}
	
	public User getUser() {
		return user;
	}
	
}
