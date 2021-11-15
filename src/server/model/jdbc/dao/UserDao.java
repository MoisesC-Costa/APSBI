package server.model.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.bean.User;
import server.model.exceptions.UserNotFoundException;
import server.model.jdbc.ConnectionFactory;


public class UserDao {
	final private UserNotFoundException userNotFound = new UserNotFoundException();
	
	Connection connection;

	public UserDao() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	// Create
	public void createUser(User user) {
		String sql = "insert into user() values(?, ?, ?, ?);";

		try (			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
		){
			
			stmt.setLong(1, user.getId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			
			stmt.execute();			
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
	// Retrive
	public User getUser(User user) throws UserNotFoundException {
		String sql = "select * from user where id = ?;";
		
		try (
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				){
			
			stmt.setLong(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("userpass"));
				user.setEmail(rs.getString("email"));
				
				return user;

			} else {
				throw userNotFound;

			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		
		
	}
	
	public User getUserByUsername(String username) throws UserNotFoundException {
		String sql = "select * from user where username = ?";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
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
		String sql = "update user "
				+ "set username = ?, userpass = ?, email = ? "
				+ "where id = ?";
		
		try (
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
			
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setLong(4, user.getId());

			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}
	
	// Delete
}
