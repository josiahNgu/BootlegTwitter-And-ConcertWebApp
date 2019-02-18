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

}
