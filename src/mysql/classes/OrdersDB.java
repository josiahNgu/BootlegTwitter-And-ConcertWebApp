package mysql.classes;

import java.util.ArrayList;

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
       	db.closeConnection();
       	
       	return result;
	}

}
