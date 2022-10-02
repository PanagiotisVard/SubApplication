package subApplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private Connection connection;
	private String dbUrl;
	private static Database instance;

	
	
	private void connect() {
		try {
			dbUrl = "jdbc:sqlite:db/clients.db";
			connection = DriverManager.getConnection(dbUrl);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			instance.connect();
		}
		return instance;
		
	}

	public Connection getConnection() {
		return connection;
	}
	
	
	

}
