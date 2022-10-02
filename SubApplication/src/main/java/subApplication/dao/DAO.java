package subApplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	protected Connection connection;
	private String dbUrl;

	
	
	protected void connect() {
		try {
			dbUrl = "jdbc:sqlite:db/clients.db";
			connection = DriverManager.getConnection(dbUrl);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	

}
