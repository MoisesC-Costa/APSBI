package server.session;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CommunicationFactory {
	private CommunicationFactory() {}
	
	public static PrintStream getPrintStream(Socket cliente) {
		try {
			
			return new PrintStream(cliente.getOutputStream());
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static Scanner getScanner(Socket cliente) {
		try {
			return new Scanner(cliente.getInputStream());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
}
