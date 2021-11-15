package server.factory;

import java.security.SecureRandom;

public class SecureTokenFactory {
	final static private String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	final static private int size = chars.length();
	
	// Factory para criar nossos tokens
	public static String getUserToken() {
		
		char buf[] = new char[256];
		
		SecureRandom rng = new SecureRandom();
		
		for (int i = 0 ; i < 256 ; i++) {
			buf[i] = chars.charAt(rng.nextInt(size));
		}
		
		return new String(buf);
		
	}
	
}
