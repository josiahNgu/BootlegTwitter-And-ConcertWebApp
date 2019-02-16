package mysql.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Users;


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

	public void displayAllUsers() {
		String SQL = "SELECT * from users";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);

			while (rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getString(2) +  " " + rs.getString(3)
				+ " " + rs.getString(4) + " " + rs.getString(5));
			}

			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Users returnUserByUsername(String aUserName) {
		String SQL = "SELECT * from users";
		Statement stat;

		Users aUser = new Users();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);

			while (rs.next()){
				if(aUserName.equals( rs.getString("Username") )) {
					aUser.setUserName(rs.getString("Username"));
					aUser.setPassword(rs.getString("Password"));
				} 
			}

			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aUser;
	}
	public boolean validateUser(String username,String password) {
		boolean passwordMatches = false;
		String SQL = "SELECT * from users where Username =\"" + username + "\"";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);

			while (rs.next()){	
				if(password.equals( rs.getString("Password") )) {
					passwordMatches = true;
				}    
			}

			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return passwordMatches;
	}
	
	public boolean findUserByUsername(String aUserName) {
		boolean userExists = false;
		String SQL = "SELECT * from users";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);

			while (rs.next()){	
				if(aUserName.equals( rs.getString("Username") )) {
					userExists = true;
				}    
			}

			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userExists;
	}
	
	public void addSingleUser(Users aUser) {
		try {
			stmt = conn.createStatement();
			String sql;

			String userName = aUser.getUserName();
			String password = aUser.getPassword();


			sql = "INSERT INTO users (Username, Password)" +
					"VALUES ('" + userName +
					"', '" + password + "')";
			stmt.executeUpdate(sql);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

