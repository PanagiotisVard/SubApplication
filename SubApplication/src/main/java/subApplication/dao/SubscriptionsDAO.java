package subApplication.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import subApplication.model.Client;

public class SubscriptionsDAO {

	private static SubscriptionsDAO instance = null; 
	private Database database;
	private Connection connection;

	private SubscriptionsDAO() {
		database = Database.getInstance();
		connection = database.getConnection();
		
	}
	
	public static SubscriptionsDAO getInstance() {
		if (instance == null) {
			instance = new SubscriptionsDAO();
		}
		return instance;
		
	}
	
	
	
	public  ArrayList<String> selectAll() {
		String sql = "SELECT subscription FROM subscription;";
		ArrayList<String> exercises = new ArrayList<String>();
		
		
		try (	
	             Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	            while (rs.next()) {
	               
	                exercises.add(rs.getString(1));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		return exercises;
		
	}

}
