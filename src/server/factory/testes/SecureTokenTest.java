package server.factory.testes;

import java.util.HashSet;
import java.util.Set;

import server.factory.SecureTokenFactory;

public class SecureTokenTest {
	public static void main(String[] args) {
		Set<String> tokens = new HashSet<>();
		
		for (int c = 0 ; c < 100000 ; c++) {
			String token = SecureTokenFactory.getUserToken();
			tokens.add(token);
		}
		
	}
	
}
