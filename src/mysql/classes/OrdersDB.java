package mysql.classes;

import java.util.ArrayList;

import model.CreditCards;
import model.Orders;

public class OrdersDB {

	public ArrayList<Orders> getOrders(int userId) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	ArrayList<Orders> results = db.getOrders(userId);
       	db.closeConnection();
       	
       	return results;
	}
	
	public ArrayList<Orders> editOrders(int orderId) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	ArrayList<Orders> results = db.editOrders(orderId);
       	db.closeConnection();
       	
       	return results;
	}
	
	public Orders cancelOrderInfo (int orderItemId) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	Orders result = db.cancelOrderInfo(orderItemId);
       	db.closeConnection();
       	
       	return result;
	}
	
	public Orders cancelOrder (int orderItemId) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	Orders result = db.cancelOrder(orderItemId);
       	
       	//update card balance here
       	String cardNumber = result.getCreditCardNumber();
       	CreditCards card =db.creditCardDetails(cardNumber);
       	double balance = (double) result.getItemTotalPrice() + Double.parseDouble(card.getBalance());
       	System.out.println("card balance: "+balance);
       	db.updateCreditCardBalance(cardNumber, Double.toString(balance));
       	
       	//update seat number
       	String performanceId = Integer.toString(result.getPerformanceId());
       	String updateNumber = Integer.toString(result.getQuantity());
       	db.addSeat(updateNumber, performanceId);
       	
       	db.closeConnection();
       	
       	return result;
	}

}
