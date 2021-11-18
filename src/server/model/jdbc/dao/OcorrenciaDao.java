package server.model.jdbc.dao;

import server.model.bean.Ocorrencia;
import server.model.jdbc.factory.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;

public class OcorrenciaDao {

	private Connection connection;
	
	public OcorrenciaDao() {
		connection = ConnectionFactory.getConnection();
	}
	
	// Create
	public void createOcorrencia(Ocorrencia ocorrencia) {
		String sqlOcorrencia = "insert into ocorrencia() values(?, ?, ?, ?, ?)";
		String sqlLocalidade = "insert into localidade() values(?, ?, ?)";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sqlLocalidade);
				) {
			
			stmt.setString(1, ocorrencia.getEstado());
			stmt.setDouble(2, ocorrencia.getLongitude());
			stmt.setDouble(3, ocorrencia.getLatitude());
			
			stmt.execute();
			
		} catch (SQLException e) {}
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sqlOcorrencia);
				) {
			
			stmt.setInt(1, ocorrencia.getId());
			stmt.setDouble(3, ocorrencia.getLongitude());
			stmt.setDouble(4, ocorrencia.getLatitude());
			stmt.setString(5, ocorrencia.getBioma());
			
			Date data = new Date(ocorrencia.getDataOcorrencia().getTimeInMillis());
			stmt.setDate(2, data);
			
			stmt.execute();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	// Retrieve
	
	// Update
	
	// Remove
}
