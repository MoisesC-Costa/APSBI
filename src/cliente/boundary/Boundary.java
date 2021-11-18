package cliente.boundary;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

// Classe para fazer a comunicação com o Servidor
public class Boundary {
	
	private Socket connection;
	private PrintStream out;
	private Scanner scanner;
	
	public Boundary() {
		connection = socketFactory("127.0.0.1", 8080);
		out = printStreamFactory(connection);
		scanner = scannerFactory(connection);
	}

	public Boundary(String ip, int port) {
		connection = socketFactory(ip, port);
	}

	// Factorys para encapsular o IOException em uma exception não checada
	private Socket socketFactory(String ip, int port) {
		
		try {
			return new Socket(ip, port);
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		
		}
		
	}

	private PrintStream printStreamFactory(Socket conn) {
		try {
			return new PrintStream(conn.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	
	}

	private Scanner scannerFactory(Socket conn) {
		try {
			return new Scanner(conn.getInputStream());

		} catch (IOException e) {
			throw new RuntimeException(e);

		}

	}

	// Metodos
	public JSONObject request(JSONObject json) {
		out.println(json.toString());
		
		if (scanner.hasNext()) {
			return new JSONObject(scanner.nextLine());

		} else {
			return new JSONObject("['code':0, 'description':'Servidor não comunicante']");
			
		}

	}
	
}
