package subApplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class IdentifierDAO {
	
	private static IdentifierDAO instance = null; 
	private Database database;
	private Connection connection;

	private IdentifierDAO() {
		database = Database.getInstance();
		connection = database.getConnection();
		
	}
	
	public static IdentifierDAO getInstance() {
		if (instance == null) {
			instance = new IdentifierDAO();
		}
		return instance;
		
	}
	
	public void insert(String id) throws SQLException {
		String sql = "INSERT INTO identifier(id) VALUES(?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);
	
		preparedStatement.executeUpdate();
	}
			
		
	
	
	
	public  ArrayList<String> selectAll() throws SQLException{
		String sql = "SELECT * FROM client;";
		ArrayList<String> clients = new ArrayList<String>();
		
		
		Statement stmt  = connection.createStatement();
		ResultSet rs    = stmt.executeQuery(sql);
        // loop through the result set
		
        while (rs.next()) {
	    
		    clients.add(rs.getString("id"));
        }
        
        return clients;
        
    }

}
