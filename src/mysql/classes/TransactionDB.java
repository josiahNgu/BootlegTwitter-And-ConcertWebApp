package mysql.classes;

import model.CreditCards;

public class TransactionDB {
	public void CreditCards(CreditCards userCard) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	db.creditCards(userCard);
       	db.closeConnection();
	}
	public void addOrder(String orderNumber,String customerId,String totalCost,String orderDate,String billingAddress,String creditcard) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	db.insertToOrders(orderNumber, customerId, totalCost, orderDate, billingAddress, creditcard);
       	db.closeConnection();
		
	}
	public void addOrderItems(String orderNumber,String performanceId,String quantity) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	db.insertToOrderItems(orderNumber, performanceId, quantity);
       	db.closeConnection();
	}
}
