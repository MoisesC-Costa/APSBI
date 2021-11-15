package server.model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
	
	public static Connection  getConnection() {
		String user = "root";
		String pass = "1234";
		String url = "jdbc:mysql://localhost/db_ong";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(url, user, pass);
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		
	}
	
}
