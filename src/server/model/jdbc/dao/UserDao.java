package server.model.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.bean.User;
import server.model.exceptions.UserNotFoundException;
import server.model.jdbc.factory.ConnectionFactory;


public class UserDao {
	final private UserNotFoundException userNotFound = new UserNotFoundException();
	
	Connection connection;

	public UserDao() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	// Create
	public void createUser(User user) {
		String sql = "insert into usuario(id, nome, userpass, email) values(?, ?, ?, ?);";

		try (			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
		){
			
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getNome());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			
			stmt.execute();			
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
	// Retrive
	
	public User getUser(String Nome) throws UserNotFoundException {
		String sql = "select * from usuario where email = ?";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
			
			stmt.setString(1, Nome);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("Nome"));
				user.setPassword(rs.getString("userpass"));
				user.setEmail(rs.getString("email"));
	
				return user;

			} else {
				throw userNotFound;
				
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	// Update
	public void updateUser(User user) {
		String sql = "update usuario "
				+ "set Nome = ?, userpass = ?, email = ? "
				+ "where id = ?";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
			
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setInt(4, user.getId());

			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}
	
	// Delete
}
