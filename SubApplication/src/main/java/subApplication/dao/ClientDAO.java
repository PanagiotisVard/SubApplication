package subApplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import subApplication.model.Client;

public class ClientDAO {
	private static Connection connection;
	private static String dbUrl;

	public ClientDAO() {
		connection = null;
		connect();
		
		
	}
	
	
	
	private static void connect() {
		try {
			dbUrl = "jdbc:sqlite:db/clients.db";
			connection = DriverManager.getConnection(dbUrl);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void selectAll() {
		String sql = "SELECT firstName FROM client;";
		try (	
	             Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	            while (rs.next()) {
	                System.out.println(rs.getString("firstName"));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	public static void insert(Client client) {
		String sql = "INSERT INTO client(firstName, lastName, fatherfirstName, address, zipCode, "
				+ "kindOfSubscription, kindOfExercise, phoneNumber, birthDate) VALUES(?,?,?,?,?,?,?,?,?)";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getFatherFirstName());
			preparedStatement.setString(4, client.getAddress());
			preparedStatement.setInt(5, client.getZipCode());
			preparedStatement.setString(6, client.getKindOfSubscription());
			preparedStatement.setString(7, client.getKindOfExercise());
			preparedStatement.setLong(8, client.getPhoneNumber());
			preparedStatement.setString(9, client.getBirthDate());
			preparedStatement.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	
	public static void delete(int id) {
		String sql = "DELETE FROM client WHERE id = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	
	public static void update(int id, Client client) {
		String sql = "UPDATE client SET firstName = ?, lastName = ?, fatherfirstName = ?, address = ?, zipCode = ?, "
				+ "kindOfSubscription = ?, kindOfExercise = ?, phoneNumber = ?, birthDate = ? WHERE id = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getFatherFirstName());
			preparedStatement.setString(4, client.getAddress());
			preparedStatement.setInt(5, client.getZipCode());
			preparedStatement.setString(6, client.getKindOfSubscription());
			preparedStatement.setString(7, client.getKindOfExercise());
			preparedStatement.setLong(8, client.getPhoneNumber());
			preparedStatement.setString(9, client.getBirthDate());
			preparedStatement.setInt(10, id);
			preparedStatement.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		 
		
	}
	
	public static void main(String[] args) {
		connect();
		
		Client updatedClient = new Client("Tassos", "Liontos", "Thanasis", "Ioannina", 44444, "Student", "Test", 6999999999l, "1/1/22");
		update(4, updatedClient);
		
		
	}

}
