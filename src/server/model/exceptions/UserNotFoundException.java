package server.model.exceptions;

public class UserNotFoundException extends Exception{
	
	public UserNotFoundException() {
		super("Usuario não encontrado");
	}

}
