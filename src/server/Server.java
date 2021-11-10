package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileLockInterruptionException;
import java.util.Scanner;

import server.functions.Function;
import server.functions.RunningFunction;
import server.session.Session;


public class Server {
	public static void main(String[] args) {
		log("Inicializando o Servidor");
		ServerSocket server = getServer();
		Scanner scanner = new Scanner(System.in);
		
		Runnable runner = new Runnable() {
				
			@Override
			public void run() {
				
				while(RunningFunction.getState()) {
					Socket cliente = acceptClient(server);
					Thread session = new Thread(new Session(cliente));
					session.start();
					
				}
				
			}
			
		};
		
		Thread serverRunner = new Thread(runner);
		serverRunner.start();
		
		while (RunningFunction.getState()) {
			String functionName = scanner.nextLine();
			String className = "server.functions." + functionName;
			
			try {
				Class<?> cls = Class.forName(className);
				
				Function function = (Function) cls.getConstructor().newInstance();
				function.exec();
				
			} catch (ClassNotFoundException e) {
				log("Função não implementada!");

			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
		scanner.close();
		serverRunner.interrupt();
		log("" + serverRunner.isAlive());
		log("Servidor Desligado!");
		
	}
	
	public static void log(String string) {
		System.out.println(string);
	}

	public static ServerSocket getServer() {
		try {
			return new ServerSocket(8080);

		} catch (IOException e) {
			throw new RuntimeException("Porta Conectada já ocupada!");
			
		}

	}

	public static Socket acceptClient(ServerSocket server) {
		try {
			return server.accept();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
