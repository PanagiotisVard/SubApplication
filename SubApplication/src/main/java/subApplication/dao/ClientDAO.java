package subApplication.dao;

import java.sql.Connection;
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
	
	
	
	public  ArrayList<Client> selectAll() throws SQLException{
		String sql = "SELECT * FROM client;";
		ArrayList<Client> clients = new ArrayList<Client>();
		
		
		Statement stmt  = connection.createStatement();
		ResultSet rs    = stmt.executeQuery(sql);
        // loop through the result set
		
        while (rs.next()) {
		    Client client = new Client(rs.getString("firstName"),rs.getString("lastName"),rs.getString("fatherFirstName"),rs.getString("address"),
		    							Integer.parseInt(rs.getString("zipCode")),rs.getString("kindOfSubscription"),rs.getString("kindOfExercise"),Long.parseLong(rs.getString("phoneNumber")),
		    							rs.getString("birthDate"), rs.getString("created_at"), rs.getInt("payments"));
	    
		    clients.add(client);
        }
        
        return clients;
        
    }
	
  
	
	
	
	
	public void insert(Client client) throws SQLException {
		String sql = "INSERT INTO client(firstName, lastName, fatherfirstName, address, zipCode, "
				+ "kindOfSubscription, kindOfExercise, phoneNumber, birthDate, created_at, payments) VALUES(?,?,?,?,?,?,?,?,?,?, 0)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, client.getFirstName());
		preparedStatement.setString(2, client.getLastName());
		preparedStatement.setString(3, client.getFatherFirstName());
		preparedStatement.setString(4, client.getAddress());
		preparedStatement.setInt(5, client.getZipCode());
		preparedStatement.setString(6, client.getKindOfSubscription());
		preparedStatement.setString(7, client.getKindOfExercise());
		preparedStatement.setLong(8, client.getPhoneNumber());
		preparedStatement.setString(9, client.getBirthDate());
		preparedStatement.setString(10, client.getCreated_at());
		preparedStatement.executeUpdate();
			
			
	}
	
	public void delete(long phoneNumber) throws SQLException {
		String sql = "DELETE FROM client WHERE phoneNumber = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, phoneNumber);
			
		preparedStatement.executeUpdate();
		
	}
	
	public void update(long phoneNumber, Client client) throws SQLException {
		String sql = "UPDATE client SET firstName = ?, lastName = ?, fatherfirstName = ?, address = ?, zipCode = ?, "
				+ "kindOfSubscription = ?, kindOfExercise = ?, phoneNumber = ?, birthDate = ?, created_at = ?, payments = ? WHERE phoneNumber = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, client.getFirstName());
		preparedStatement.setString(2, client.getLastName());
		preparedStatement.setString(3, client.getFatherFirstName());
		preparedStatement.setString(4, client.getAddress());
		preparedStatement.setInt(5, client.getZipCode());
		preparedStatement.setString(6, client.getKindOfSubscription());
		preparedStatement.setString(7, client.getKindOfExercise());
		preparedStatement.setLong(8, client.getPhoneNumber());
		preparedStatement.setString(9, client.getBirthDate());
		preparedStatement.setString(10, client.getCreated_at());
		preparedStatement.setInt(11, client.getPayments());
		preparedStatement.setLong(12, phoneNumber);
		preparedStatement.executeUpdate();
		 
	}
	
	public Client selectByPhoneNumber(long phoneNumber) {
		String sql = "SELECT * FROM client WHERE phoneNumber = ?;";
		Client client = null;
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	
			preparedStatement.setLong(1, phoneNumber);
			
			ResultSet rs = preparedStatement.executeQuery();
			 // loop through the result set
			
            while (rs.next()) {
            	client = new Client(rs.getString("firstName"),rs.getString("lastName"),rs.getString("fatherFirstName"),rs.getString("address"),
            			Integer.parseInt(rs.getString("zipCode")),rs.getString("kindOfSubscription"),rs.getString("kindOfExercise"),Long.parseLong(rs.getString("phoneNumber")),
            			rs.getString("birthDate"),rs.getString("created_at"), rs.getInt("payments"));
            	
            }	
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return client;
	}

}
