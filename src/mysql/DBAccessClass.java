package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Bank;

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
	public void creditCards(Bank newCard) {
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
	public Bank creditCardDetails(String cardNumber) {
		String sql ="select CreditCardNumber,Balance from creditcards where CreditCardNumber=?";	
		Bank cards = new Bank();
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, cardNumber);
			ResultSet rs = ps.executeQuery();
			//Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				cards = new Bank(rs.getString("CreditCardNumber"),rs.getString("Balance"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cards;
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
}
