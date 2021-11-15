package cliente.frames;

import java.util.Scanner;

public class FrameRunner {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNext()) {
			String frameName = scanner.nextLine();
			String className = "cliente.frames." + frameName;
			new Thread(runnerFactory(className)).start();;
			
		}
		
		scanner.close();
		
	}

	public static Runnable runnerFactory(String className) {
		try {
			Class<?> cls = Class.forName(className);
			return (Runnable) cls.getConstructor().newInstance();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
		
	}
}
