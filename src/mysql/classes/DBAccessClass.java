package mysql.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Review;
import model.CreditCards;
import model.Shows;
import model.Users;
import model.Orders;


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
	
	public ArrayList<Orders> editOrders (int orderId){
		/*
		 * required data: 
		 * orderNumber,movieName,quantity,Total price,Venue name,Showtime/Date,orderTotal,OrderDate
		 */
		
		String SQL = "select orders.Id, orders.TotalCost, orders.OrderDate, orders.BillingAddress, orderitems.Quantity, performance.StartTime, concert.MovieName, venue.VenueName, TicketVenuePrices.TicketPrice from orders" + 
				"	join orderitems on orders.Id = orderitems.OrderId" + 
				"	join performance on orderitems.PerformanceID = performance.Id" + 
				"   join concert on performance.concertID = concert.Id" + 
				"   join venue on performance.venueID = venue.Id" + 
				"   join TicketVenuePrices on performance.Id = TicketVenuePrices.performanceID where orders.Id ="+orderId;
		Statement stat;
		
		ArrayList<Orders> results = new ArrayList<Orders>();
		
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			//int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity, int ticketprice
			//String movieName, String venueName, String showTime, int itemTotalPrice

			while (rs.next()){
				int quantity = rs.getInt("Quantity");
				int ticketPrice = rs.getInt("TicketPrice");
				int itemTotal = quantity * ticketPrice;
				Orders anOrder = new Orders(rs.getInt("Id"),rs.getInt("TotalCost"),rs.getString("OrderDate"),rs.getString("BillingAddress"),
						quantity,ticketPrice,rs.getString("MovieName"),rs.getString("VenueName"),rs.getString("StartTime"),itemTotal);
				results.add(anOrder);
			}

			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
		
	}

	public ArrayList<Orders> getOrders(int userId) {
		String SQL = "SELECT * from orders where CustomerId = "+userId;
		Statement stat;

		ArrayList<Orders> results = new ArrayList<Orders>();
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
			//int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity

			while (rs.next()){
				Orders anOrder = new Orders(rs.getInt("Id"),rs.getInt("TotalCost"),rs.getString("OrderDate"),rs.getString("BillingAddress"));
				results.add(anOrder);
			}

			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
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
		String sql="select performance.StartTime, performance.EndTime,performance.seatLeft,performance.id,concert.description,concert.MovieName,concert.Thumbnail,"
				+ "concert.Rating,venue.VenueName, TicketVenuePrices.TicketPrice"
				+" from performance inner join concert on performance.concertID = concert.ID inner join venue"
				+ " on performance.venueID = venue.id inner join TicketVenuePrices on TicketVenuePrices.performanceID=performance.ID"
				+ " where concert.MovieName=?";

		String sql1="select performance.StartTime, performance.EndTime,performance.seatLeft,performance.id,concert.MovieName,concert.Thumbnail,"
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
						rs.getString("VenueName"), rs.getString("Thumbnail"),rs.getString("Rating"),rs.getString("Description"),rs.getString("seatLeft"),
						rs.getString("TicketPrice"),rs.getString("id")
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
		String sql ="select performance.StartTime, performance.EndTime, performance.SeatLeft,concert.MovieName ,performance.id,concert.Thumbnail,concert.Description," 
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
				details.setPerformanceId(rs.getString("id"));
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
					aUser.setUserId(rs.getInt("Id"));
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
		String expirationDate = newCard.getExpiryYear() + "-" + newCard.getExpiryMonth()+"-00";
		System.out.print(expirationDate);
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, cardHolderName);
			prepareStmt.setString(2, newCard.getCardNumber());
			prepareStmt.setString(3, "5000");
			prepareStmt.setString(4, newCard.getCardType());
			prepareStmt.setString(5, newCard.getUserId());
			prepareStmt.setString(6, newCard.getCvc());
			prepareStmt.setString(7, expirationDate);
			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void updateCreditCardBalance(String  cardNumber,String balance) {
		String sql = "update creditcards set Balance = ?  where CreditCardNumber = ? ";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, balance);
			prepareStmt.setString(2, cardNumber);
			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CreditCards creditCardDetails(String cardNumber) {
		String sql ="select CreditCardNumber,Balance from creditcards where CreditCardNumber=?";	
		CreditCards cards = new CreditCards();
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, cardNumber);
			ResultSet rs = ps.executeQuery();
			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				cards = new CreditCards(rs.getString("CreditCardNumber"),rs.getString("Balance"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cards;
	}

	public void addUserComment(String comment,String userId, String concertId, String currentDate, String rating) {
		String sql = "insert into customerreviews(concertID,userID,ReviewDate,Rating,Review)values(?,?,?,?,?)";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, concertId);
			prepareStmt.setString(2, userId);
			prepareStmt.setString(3, currentDate);
			prepareStmt.setString(4, rating);
			prepareStmt.setString(5, comment);
			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Review> getReview(String movieName) {
		String sql = "select customerreviews.Review,customerreviews.Rating,customerreviews.ReviewDate,"
				+ "users.Username from customerreviews inner join users on customerreviews.userId = users.id inner join concert on concert.id = customerreviews.concertID" 
				+ " where concert.movieName=?";
		ArrayList<Review> results = new ArrayList<Review>();
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, movieName);
			ResultSet rs = ps.executeQuery();
			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				Review newComment = new Review(rs.getString("userName"),rs.getString("rating"),rs.getString("Review"),rs.getString("ReviewDate"));
				results.add(newComment);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	public String getMovieId(String movieName) {
		String sql = "select id from concert where movieName =?";
		String result ="nil";
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, movieName);
			ResultSet rs = ps.executeQuery();
			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				result = rs.getString("id");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}
	//need to fix to catch if there is two same concerts but diff time or venue
	public boolean haveTickets(String concertId, int requestedSeat) {
		boolean seatLeft = false;
		String sql = "select performance.SeatLeft from concert  join performance on performance.concertID = concert.id "
				+ "where concert.id=?";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()){	
				if(Integer.parseInt(rs.getString("SeatLeft")) > requestedSeat) {
					seatLeft= true;
				}
			}

			stat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return seatLeft;
	}

	//update seat #
	//same bug as haveTickets
	public void updateSeat(String updateNumber,String performaceId,String type) {
		String sql ="update performance set SeatLeft = SeatLeft + ? where performance.Id =?";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, updateNumber);
			prepareStmt.setString(2, performaceId);
			prepareStmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertToOrders(String orderNumber, String customerId, String totalCost, String orderDate, String billingAddress,String creditcard) {
		String sql ="insert into orders(Id,CustomerId,TotalCost,OrderDate,BillingAddress,CreditCardNumber)values(?,?,?,?,?,?)";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, orderNumber);
			prepareStmt.setString(2, customerId);
			prepareStmt.setString(3, totalCost);
			prepareStmt.setString(4, orderDate);
			prepareStmt.setString(5, billingAddress);
			prepareStmt.setString(6, creditcard);
			prepareStmt.executeUpdate();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertToOrderItems(String orderId, String performanceId, String quantity) {
		String sql = "insert into orderitems(OrderId,PerformanceID,Quantity)values(?,?,?)";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, orderId);
			prepareStmt.setString(2, performanceId);
			prepareStmt.setString(3, quantity);
			prepareStmt.executeUpdate();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

