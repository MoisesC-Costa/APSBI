package server.model.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import server.model.jdbc.factory.ConnectionFactory;

public class CasosPorEstadosDao {
	private Connection connection;
	
	public CasosPorEstadosDao() {
		connection = ConnectionFactory.getConnection();
	}
	
	public JSONObject getCasos() {
		String sql = "select estado, count(estado) as casos "
				+ "from localidade "
				+ "join ocorrencia on ocorrencia.latitude = localidade.latitude "
				+ "group by localidade.estado";
		
		JSONObject data = new JSONObject();
		
		try (
				Statement st = connection.createStatement()
				) {
			
			ResultSet rs = st.executeQuery(sql);
			System.out.println("pesquisou");
			
			while(rs.next()) {
				data.put(rs.getString("estado"), rs.getInt("casos"));
			}
			
			return data;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
	
}
