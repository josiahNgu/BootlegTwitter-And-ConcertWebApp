package mysql.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBAccessClass {	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jngu";



	//  Database credentials
	static final String USER = "jngu"; // Replace with your CSE_LOGIN
	static final String PASS = "bZ4:9y";   // Replace with your CSE MySQL_PASSWORD

	public void connectMeIn() {
		try{
			//Register the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");			
		}catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		}
		try {
			//Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getVenue() {
		String sql = "SELECT * FROM venue";
		ArrayList<String> results = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(sql);			  
			ResultSet rs = ps.executeQuery();

			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				results.add(rs.getString("Name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public ArrayList<String> searchPerformance(String search){
		String sql="Select * FROM concert";
		ArrayList<String> results = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(sql);	
//			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();

			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				results.add(rs.getString("Name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

