package subApplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import subApplication.model.Client;
import subApplication.model.Subscription;

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
	
	
	public double getPrice(String subscription) {
		String sql = "SELECT price FROM subscription WHERE subscription = ?;";
		double priceSubscriptions = -1 ;
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			
			preparedStatement.setString(1, subscription);
			
			ResultSet rs = preparedStatement.executeQuery();
			 // loop through the result set
			
            while (rs.next()) {
            		priceSubscriptions = rs.getDouble("price");
            	
            }	
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return priceSubscriptions;
		
	}
	
	
	public  ArrayList<Subscription> selectAll() {
		String sql = "SELECT * FROM subscription;";
		ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
		
		
		try (	
	             Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	            while (rs.next()) {
	               
	                subscriptions.add(new Subscription(rs.getString("subscription"), rs.getInt("visible")));
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
		
		return subscriptions;
		
	}

}
