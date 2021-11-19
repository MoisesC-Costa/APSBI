package server.model.jdbc.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import server.model.jdbc.factory.ConnectionFactory;

public class CasosPorBiomasDao {
	private Connection connection;
	
	public CasosPorBiomasDao() {
		connection = ConnectionFactory.getConnection();
		
	}
	
	public JSONObject getCasos() {
		JSONObject data = new JSONObject();
		
		String sql = "select bioma, count(bioma) as casos "
				+ "from ocorrencia "
				+ "group by bioma";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
						
				ResultSet rs = stmt.executeQuery();
				System.out.println();
				
				while(rs.next()) {
					data.put(rs.getString("bioma"), rs.getInt("casos"));
				}
				
				return data;
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		
	}
	
}
