package mysql.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.CreditCards;
import model.Shows;
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
		String sql = "SELECT distinct VenueName from venue";
		ArrayList<String> results = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(sql);			  
			ResultSet rs = ps.executeQuery();

			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				results.add(rs.getString("VenueName"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public ArrayList<Shows> searchPerformance(String search){
		String sql="select performance.StartTime, performance.EndTime,performance.seatLeft,concert.MovieName,concert.Thumbnail,"
				+ "concert.Rating,venue.VenueName, TicketVenuePrices.TicketPrice"
				+" from performance inner join concert on performance.concertID = concert.ID inner join venue"
				+ " on performance.venueID = venue.id inner join TicketVenuePrices on TicketVenuePrices.performanceID=performance.ID"
				+ " where concert.MovieName=?";
		
		String sql1="select performance.StartTime, performance.EndTime,performance.seatLeft,concert.MovieName,concert.Thumbnail,"
				+ "concert.Rating,venue.VenueName, TicketVenuePrices.TicketPrice "
				+"from performance inner join concert on performance.concertID = concert.ID inner join venue "
				+ "on performance.venueID = venue.id inner join TicketVenuePrices on TicketVenuePrices.performanceID=performance.ID";
		ArrayList<Shows> results = new ArrayList<Shows>();
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, search);
			ResultSet rs = ps.executeQuery();

			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				Shows newShow = new Shows(rs.getString("StartTime"),rs.getString("EndTime"),rs.getString("MovieName"),
						rs.getString("VenueName"), rs.getString("Thumbnail"),rs.getString("Rating"),rs.getString("seatLeft"),
						rs.getString("TicketPrice")
						);
				results.add(newShow);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(results.size()< 1) {
			try {
				ps = conn.prepareStatement(sql1);	
				ResultSet rs = ps.executeQuery();

				//Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					Shows newShow = new Shows(rs.getString("StartTime"),rs.getString("EndTime"),rs.getString("MovieName"),
							rs.getString("VenueName"), rs.getString("Thumbnail"),rs.getString("Rating"),rs.getString("seatLeft"),
							rs.getString("TicketPrice")
							);
					results.add(newShow);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;
	}
	public Shows detailSearch(String performanceName, String venueName) {
		String sql ="select performance.StartTime, performance.EndTime, performance.SeatLeft, concert.MovieName ,concert.Thumbnail,concert.Description," 
				+ " concert.Rating,venue.VenueName,TicketVenuePrices.TicketPrice,TicketTypes.SeatName from performance inner join concert on"
				+ " performance.concertID = concert.ID  inner join TicketVenuePrices on TicketVenuePrices.performanceID = performance.ID"
				+ " inner join venue on performance.venueID = venue.id inner join TicketTypes on TicketVenuePrices.ticketTypeID = TicketTypes.id"
				+ " where concert.MovieName=? and venue.venueName=?";
		Shows details = new Shows();
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, performanceName);
			ps.setString(2, venueName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				details.setStartTime(rs.getString("StartTime"));
				details.setEndTime(rs.getString("EndTime"));
				details.setMovieName(rs.getString("MovieName"));
				details.setVenue(rs.getString("VenueName"));
				details.setThumbnail(rs.getString("Thumbnail"));
				details.setRating(rs.getString("Rating"));
				details.setDescription(rs.getString("Description"));
				details.setSeatLeft(rs.getString("SeatLeft"));
				details.setPpSeat(rs.getString("TicketPrice"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return details;
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
	public void creditCards(CreditCards newCard) {
		String sql = "insert into creditcards (CardHolderName,CreditCardNumber,Balance,CardType,UserId,CVV,ExpirationDate)values"
				+ "(?,?,?,?,?,?,?)";
		String cardHolderName = newCard.getFirstName().concat(newCard.getLastName());
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, newCard.getFirstName());
			
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}

