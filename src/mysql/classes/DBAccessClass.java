package mysql.classes;

import java.io.File;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import model.Review;
import model.CreditCards;
import model.Shows;
import model.Users;
import mysql.classes.PasswordUtil;
import servlet.Login;
import model.Orders;


public class DBAccessClass {	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jngu";

	static Logger log 
    = Logger.getLogger(Login.class.getName());

	//  Database credentials
	static final String USER = "jngu"; // Replace with your CSE_LOGIN
	static final String PASS = "";   // Replace with your CSE MySQL_PASSWORD

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

	public Orders cancelOrder (int orderItemId) {
		String update = "update orderitems set isCancelled = 1 where Id = ?";

		String SQL = "select orders.Id, orders.TotalCost, orders.OrderDate, orders.BillingAddress, orders.CreditCardNumber, orderitems.Quantity, performance.StartTime, performance.Id as performanceId, concert.MovieName, venue.VenueName, TicketVenuePrices.TicketPrice, orderitems.Id as orderItemId from orders" + 
				"	join orderitems on orders.Id = orderitems.OrderId" + 
				"	join performance on orderitems.PerformanceID = performance.Id" + 
				"   join concert on performance.concertID = concert.Id" + 
				"   join venue on performance.venueID = venue.Id" + 
				"   join TicketVenuePrices on performance.Id = TicketVenuePrices.performanceID where orderitems.Id = ?";

		Orders result = new Orders();

		try {

			ps = conn.prepareStatement(update);	
			ps.setInt(1, orderItemId);
			ps.executeUpdate();
			//int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity, int ticketprice
			//String movieName, String venueName, String showTime, int itemTotalPrice
			
			ps = conn.prepareStatement(SQL);	
			ps.setInt(1, orderItemId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				int quantity = rs.getInt("Quantity");
				int ticketPrice = rs.getInt("TicketPrice");
				int itemTotal = quantity * ticketPrice;
				result = new Orders(rs.getInt("Id"),rs.getInt("TotalCost"),rs.getString("OrderDate"),rs.getString("BillingAddress"),
						quantity,ticketPrice,rs.getString("MovieName"),rs.getString("VenueName"),rs.getString("StartTime"),itemTotal,
						rs.getInt("orderItemId"),rs.getString("CreditCardNumber"),rs.getInt("performanceId"));

			}

			/*
			 * This part onwards is recalculating the sum of the order
			 */

			int orderId = result.getOrderNumber();
			String SQL2 = "select orders.Id, orders.TotalCost, orders.OrderDate, orders.BillingAddress, orderitems.Quantity, orderitems.Id as orderItemId, orderitems.isCancelled, performance.StartTime, concert.MovieName, venue.VenueName, TicketVenuePrices.TicketPrice from orders" + 
					"	join orderitems on orders.Id = orderitems.OrderId" + 
					"	join performance on orderitems.PerformanceID = performance.Id" + 
					"   join concert on performance.concertID = concert.Id" + 
					"   join venue on performance.venueID = venue.Id" + 
					"   join TicketVenuePrices on performance.Id = TicketVenuePrices.performanceID where orders.Id = ?";


			if(orderId == 0) {
				System.err.println("error getting orderId");
			}
			ps = conn.prepareStatement(SQL2);
			ps.setInt(1, orderId);
			ResultSet rs2 = ps.executeQuery();

			ArrayList<Orders> results = new ArrayList<Orders>();

			while (rs2.next()){
				//only get orders that aren't cancelled
				if(rs2.getInt("isCancelled") == 0) {
					int quantity = rs2.getInt("Quantity");
					int ticketPrice = rs2.getInt("TicketPrice");
					int itemTotal = quantity * ticketPrice;
					Orders anOrder = new Orders(rs2.getInt("Id"),rs2.getInt("TotalCost"),rs2.getString("OrderDate"),rs2.getString("BillingAddress"),
							quantity,ticketPrice,rs2.getString("MovieName"),rs2.getString("VenueName"),rs2.getString("StartTime"),itemTotal,rs2.getInt("orderItemId"));
					results.add(anOrder);
				}
			}

			int totalCost = 0;
			//add each item's total
			for(Orders order: results){
				totalCost = totalCost + order.getItemTotalPrice();

			}

			//update TotalCost for that order
			String SQL3 = "update orders set TotalCost = ? where Id = ?";
			ps = conn.prepareStatement(SQL3);
			ps.setInt(1, totalCost);
			ps.setInt(2, orderId);
			ps.executeUpdate();
			
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Orders cancelOrderInfo (int orderItemId) {

		String SQL = "select orders.Id, orders.TotalCost, orders.OrderDate, orders.BillingAddress, orderitems.Quantity, performance.StartTime, concert.MovieName, venue.VenueName, TicketVenuePrices.TicketPrice, orderitems.Id as orderItemId from orders" + 
				"	join orderitems on orders.Id = orderitems.OrderId" + 
				"	join performance on orderitems.PerformanceID = performance.Id" + 
				"   join concert on performance.concertID = concert.Id" + 
				"   join venue on performance.venueID = venue.Id" + 
				"   join TicketVenuePrices on performance.Id = TicketVenuePrices.performanceID where orderitems.Id = ?";
		Orders result = new Orders();

		try {
			ps = conn.prepareStatement(SQL);	
			ps.setInt(1, orderItemId);

			ResultSet rs = ps.executeQuery();
			//int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity, int ticketprice
			//String movieName, String venueName, String showTime, int itemTotalPrice

			while (rs.next()){
				int quantity = rs.getInt("Quantity");
				int ticketPrice = rs.getInt("TicketPrice");
				int itemTotal = quantity * ticketPrice;
				result = new Orders(rs.getInt("Id"),rs.getInt("TotalCost"),rs.getString("OrderDate"),rs.getString("BillingAddress"),
						quantity,ticketPrice,rs.getString("MovieName"),rs.getString("VenueName"),rs.getString("StartTime"),itemTotal,rs.getInt("orderItemId"));

			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Orders> editOrders (int orderId){
		/*
		 * required data: 
		 * orderNumber,movieName,quantity,Total price,Venue name,Showtime/Date,orderTotal,OrderDate
		 */

		String SQL = "select orders.Id, orders.TotalCost, orders.OrderDate, orders.BillingAddress, orderitems.Quantity, orderitems.Id as orderItemId, orderitems.isCancelled, performance.StartTime, concert.MovieName, venue.VenueName, TicketVenuePrices.TicketPrice from orders" + 
				"	join orderitems on orders.Id = orderitems.OrderId" + 
				"	join performance on orderitems.PerformanceID = performance.Id" + 
				"   join concert on performance.concertID = concert.Id" + 
				"   join venue on performance.venueID = venue.Id" + 
				"   join TicketVenuePrices on performance.Id = TicketVenuePrices.performanceID where orders.Id = ?";

		ArrayList<Orders> results = new ArrayList<Orders>();

		try {
			ps = conn.prepareStatement(SQL);	
			ps.setInt(1, orderId);

			ResultSet rs = ps.executeQuery();
			//int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity, int ticketprice
			//String movieName, String venueName, String showTime, int itemTotalPrice

			while (rs.next()){
				//only get orders that aren't cancelled
				if(rs.getInt("isCancelled") == 0) {
					int quantity = rs.getInt("Quantity");
					int ticketPrice = rs.getInt("TicketPrice");
					int itemTotal = quantity * ticketPrice;
					Orders anOrder = new Orders(rs.getInt("Id"),rs.getInt("TotalCost"),rs.getString("OrderDate"),rs.getString("BillingAddress"),
							quantity,ticketPrice,rs.getString("MovieName"),rs.getString("VenueName"),rs.getString("StartTime"),itemTotal,rs.getInt("orderItemId"));
					results.add(anOrder);
				}
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;

	}

	public ArrayList<Orders> getOrders(int userId) {
		String SQL = "SELECT * from orders where CustomerId = ?";

		ArrayList<Orders> results = new ArrayList<Orders>();
		try {
			ps = conn.prepareStatement(SQL);	
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			//int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity

			while (rs.next()){
				Orders anOrder = new Orders(rs.getInt("Id"),rs.getInt("TotalCost"),rs.getString("OrderDate"),rs.getString("BillingAddress"));
				results.add(anOrder);
			}

			ps.close();

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

	public ArrayList<Shows> searchPerformance(String search, String venue){


		String sql = "select performance.StartTime, performance.EndTime, performance.SeatLeft,performance.id, concert.MovieName ,concert.Thumbnail,concert.Description,"
				+ " concert.Rating,venue.VenueName,TicketVenuePrices.TicketPrice,TicketTypes.SeatName from performance inner join concert on "
				+ " performance.concertID = concert.ID  inner join TicketVenuePrices on TicketVenuePrices.performanceID = performance.ID"
				+ " inner join venue on performance.venueID = venue.id inner join TicketTypes on TicketVenuePrices.ticketTypeID = TicketTypes.id"
				+ " where concert.MovieName=? and venue.venueName = ?";
		
		String sql1="select performance.StartTime, performance.EndTime,performance.seatLeft,performance.id,concert.description,concert.MovieName,concert.Thumbnail,"
				+ "concert.Rating,venue.VenueName, TicketVenuePrices.TicketPrice"
				+" from performance inner join concert on performance.concertID = concert.ID inner join venue"
				+ " on performance.venueID = venue.id inner join TicketVenuePrices on TicketVenuePrices.performanceID=performance.ID"
				+ " where concert.MovieName=?";

		String sql2="select performance.StartTime, performance.EndTime,performance.seatLeft,performance.id,concert.MovieName,concert.Thumbnail,"
				+ "concert.Rating,venue.VenueName, TicketVenuePrices.TicketPrice "
				+"from performance inner join concert on performance.concertID = concert.ID inner join venue "
				+ "on performance.venueID = venue.id inner join TicketVenuePrices on TicketVenuePrices.performanceID=performance.ID";
		ArrayList<Shows> results = new ArrayList<Shows>();
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, search);
			ps.setString(2, venue);

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
				ps.setString(1, search);
				ResultSet rs = ps.executeQuery();

				//Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					Shows newShow = new Shows(rs.getString("StartTime"),rs.getString("EndTime"),rs.getString("MovieName"),
							rs.getString("VenueName"), rs.getString("Thumbnail"),rs.getString("Rating"),rs.getString("seatLeft"),
							rs.getString("TicketPrice"),rs.getString("id")
							);
					results.add(newShow);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(results.size()< 1) {
			try {
				ps = conn.prepareStatement(sql2);	
				ResultSet rs = ps.executeQuery();

				//Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					Shows newShow = new Shows(rs.getString("StartTime"),rs.getString("EndTime"),rs.getString("MovieName"),
							rs.getString("VenueName"), rs.getString("Thumbnail"),rs.getString("Rating"),rs.getString("seatLeft"),
							rs.getString("TicketPrice"),rs.getString("id")
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

	public Users returnUserByUsername(String aUserName) {
		String SQL = "SELECT * from users";
		PreparedStatement ps = null;
		Users aUser = new Users();
		try {
			ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery(SQL);

			while (rs.next()){
				if(aUserName.equals( rs.getString("Username") )) {
					aUser.setUserId(rs.getInt("Id"));
					aUser.setUserName(rs.getString("Username"));
					aUser.setPassword(rs.getString("Password"));
				} 
			}
			ps.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aUser;
	}
	

	public boolean changePassword (String userName, String password) {
		String sql = "update users set Password = ?, Salt = ? where Username = ?";
		boolean success = false;
		try {
			String salt = PasswordUtil.getSalt();
			String saltedPassword = PasswordUtil.hashPassword(password+salt);
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, saltedPassword);
			ps.setString(2, salt);
			ps.setString(3, userName);
			
			ps.executeUpdate();
			
			success = true;
			
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}
	
	public boolean validateUser(String username,String password) {
		
		//path to the log4j properties file
		PropertyConfigurator.configure("C:\\Users\\WhoAm\\git\\CSE464\\WebContent\\WEB-INF\\lib\\log4j.properties");
		
		boolean passwordMatches = false;
		String SQL = "SELECT * from users where Username =?";
		try {
			ps = conn.prepareStatement(SQL);	
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){	
				String salt = rs.getString("Salt");
				String saltedPassword = PasswordUtil.hashPassword(password+salt);
				if(saltedPassword.equals( rs.getString("Password") )) {
					passwordMatches = true;
				}    
			}
			

		} catch (SQLException e) {
			System.out.println("error information is written on the log file.");
			log.error("This is a error message.",e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("error information is written on the log file.");
			log.error("This is a error message.",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return passwordMatches;
	}

	public boolean findUserByUsername(String aUserName) {
		boolean userExists = false;
		String SQL = "SELECT * from users";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(SQL);

			ResultSet rs = prepareStmt.executeQuery(SQL);

			while (rs.next()){	
				if(aUserName.equals( rs.getString("Username") )) {
					userExists = true;
				}    
			}

			prepareStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userExists;
	}

	public void addSingleUser(Users aUser) {
		try {
			String sql;

			String userName = aUser.getUserName();
			String password = aUser.getPassword();
			
			String salt = PasswordUtil.getSalt();

			String saltedPassword = PasswordUtil.hashPassword(password+salt);

			sql = "INSERT INTO users (Username, Password, Salt) values (?,?,?)";
			
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, userName);
			prepareStmt.setString(2, saltedPassword);
			prepareStmt.setString(3, salt);

			prepareStmt.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//insert new creditcard to database
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
	//get credit card Balance
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
	public void decreaseSeat(String updateNumber,String performanceId) {
		String sql = "update performance set SeatLeft = ? where performance.Id = ?";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, updateNumber);
			prepareStmt.setString(2, performanceId);
			prepareStmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addSeat(String updateNumber,String performanceId) {
		String sql = "update performance set SeatLeft = SeatLeft + ? where performance.Id = ?";
		try {
			PreparedStatement prepareStmt = conn.prepareStatement(sql);
			prepareStmt.setString(1, updateNumber);
			prepareStmt.setString(2, performanceId);
			prepareStmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String seatLeft(String performanceId) {
		String sql = "select SeatLeft from performance where performance.Id =?";
		String seatLeft ="0";
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, performanceId);
			ResultSet rs = ps.executeQuery();
			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				seatLeft = rs.getString("SeatLeft");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seatLeft;

	}
}

