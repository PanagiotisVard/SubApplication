package subApplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import subApplication.model.Client;

public class ClientDAO {
	
	
	private static ClientDAO instance = null; 
	private Database database;
	private Connection connection;

	private ClientDAO() {
		database = Database.getInstance();
		connection = database.getConnection();
		
	}
	
	public static ClientDAO getInstance() {
		if (instance == null) {
			instance = new ClientDAO();
		}
		return instance;
		
	}
	
	
	
	public  ArrayList<Client> selectAll() {
		String sql = "SELECT * FROM client;";
		ArrayList<Client> clients = new ArrayList<Client>();
		
		
		try (	
	             Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	            while (rs.next()) {
	                //System.out.println(rs.getString("firstName"));
	            	Client client = new Client(rs.getString("firstName"),rs.getString("lastName"),rs.getString("fatherFirstName"),rs.getString("address"),
	            			Integer.parseInt(rs.getString("zipCode")),rs.getString("kindOfSubscription"),rs.getString("kindOfExercise"),Long.parseLong(rs.getString("phoneNumber")),
	            			rs.getString("birthDate"));
	                clients.add(client);
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		return clients;
		
	}
	
	public void insert(Client client) {
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
	
	public void delete(int id) {
		String sql = "DELETE FROM client WHERE id = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	
	public void update(int id, Client client) {
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
	
	public Client selectByPhoneNumber(int phoneNumber) {
		String sql = "SELECT * FROM client WHERE phoneNumber = ?;";
		Client client = null;
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	
			preparedStatement.setInt(1, phoneNumber);
			
			ResultSet rs = preparedStatement.executeQuery();
			 // loop through the result set
			
            while (rs.next()) {
            	client = new Client(rs.getString("firstName"),rs.getString("lastName"),rs.getString("fatherFirstName"),rs.getString("address"),
            			Integer.parseInt(rs.getString("zipCode")),rs.getString("kindOfSubscription"),rs.getString("kindOfExercise"),Long.parseLong(rs.getString("phoneNumber")),
            			rs.getString("birthDate"));
            	
//                System.out.println(rs.getString("firstName")+rs.getString("lastName"));
            }	
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return client;
	}

}
