package server.functions;

public class Shutdown implements Function {

	@Override
	public void exec() {
		System.out.println("Desligado o Servidor!");
		System.exit(0);
		
	}

}
