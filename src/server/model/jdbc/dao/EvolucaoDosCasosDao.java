package server.model.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.json.JSONArray;

import server.model.jdbc.factory.ConnectionFactory;

public class EvolucaoDosCasosDao {
	
	private Connection connection;
	
	public EvolucaoDosCasosDao() {
		connection = ConnectionFactory.getConnection();
	}
	
	public JSONArray getCasos(int ano) {
		JSONArray data = new JSONArray();
		
		Calendar inicio = Calendar.getInstance();
		Calendar fim = Calendar.getInstance();
		
		inicio.set(ano, 1, 1);
		fim.set(ano + 1, 1, 1);
		
		String sql = "select count(data_ocorrencia) casos "
				+ "from ocorrencia "
				+ "where data_ocorrencia >= ? and data_ocorrencia <= ? "
				 + "group by data_ocorrencia";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
			
			stmt.setDate(1, new Date(inicio.getTimeInMillis()));
			stmt.setDate(2, new Date(fim.getTimeInMillis()));
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				data.put(rs.getInt("casos"));				
			}
			
			return data;
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		
	}
}
