package server.session;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

import server.controller.Controller;

public class Session implements Runnable{
	
	private PrintStream out;
	private Scanner scanner;
	
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
		
	}
	
	public void request(JSONObject packet) {
		Controller.execLogic(packet, this);
	}
	
	public void response(JSONObject packet) {
		out.println(packet.toString());
		
	}
		
		
}
