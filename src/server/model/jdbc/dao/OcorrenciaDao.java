package server.model.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import server.model.bean.Ocorrencia;
import server.model.jdbc.factory.ConnectionFactory;

public class OcorrenciaDao {

	private Connection connection;
	
	public OcorrenciaDao() {
		connection = ConnectionFactory.getConnection();
	}
	
	// Create
	public void createOcorrencia(Ocorrencia ocorrencia) {
		String sqlOcorrencia = "insert into ocorrencia() values(?, ?, ?, ?, ?)";
		String sqlLocalidade = "insert into localidade() values(?, ?, ?, ?)";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sqlLocalidade);
				) {
			
			stmt.setString(1, ocorrencia.getEstado());
			stmt.setDouble(2, ocorrencia.getLongitude());
			stmt.setDouble(3, ocorrencia.getLatitude());
			stmt.setString(4, ocorrencia.getMunicipio());
			
			stmt.execute();
			
		} catch (SQLException e) {}
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sqlOcorrencia);
				) {
			
			stmt.setInt(1, ocorrencia.getId());
			stmt.setDouble(3, ocorrencia.getLongitude());
			stmt.setDouble(4, ocorrencia.getLatitude());
			stmt.setString(5, ocorrencia.getBioma());
			
			stmt.setLong(2, ocorrencia.getDataOcorrencia().getTimeInMillis());
			
			stmt.execute();
			
			
		} catch (SQLException e) {}
		
	}
	
	// Retrieve
	
	// Update
	
	// Remove
}
