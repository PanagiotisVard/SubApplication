package subApplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import subApplication.model.Client;
import subApplication.model.User;

public class UserDAO {
	
	private static UserDAO instance = null; 
	private Database database;
	private Connection connection;

	private UserDAO() {
		database = Database.getInstance();
		connection = database.getConnection();
		
	}
	
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
		
	}

	
	
	public User selectByPhoneNumber(String username) {
		String sql = "SELECT * FROM user WHERE username = ?;";
		User user = null;
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();

			
            while (rs.next()) {
            	user = new User(rs.getString("username"), rs.getString("password"));
            	
            }	
			
			
			
		} catch (SQLException e) {
		}
		
		return user;
	}
	
	public void insert(User user) {
		String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}

}
