package mysql.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mysql.classes.DBAccessClass;; 
public class VenueDB {
	public ArrayList<String> getVenue() {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	ArrayList<String> venue = db.getVenue();
       	db.closeConnection();
		return venue;
	}
}
