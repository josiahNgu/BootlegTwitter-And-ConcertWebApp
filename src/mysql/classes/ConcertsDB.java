package mysql.classes;

import java.util.ArrayList;

import model.Shows;

public class ConcertsDB {
	public ArrayList<Shows> searchResult(String search) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	ArrayList<Shows> searchResult = db.searchPerformance(search);
       	db.closeConnection();
		return searchResult;
	}
	public Shows detailResult(String performanceName,String venueID) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		Shows searchResult = new Shows();
		searchResult = db.detailSearch(performanceName,venueID);
		db.closeConnection();
		return searchResult;
	}
	
}
