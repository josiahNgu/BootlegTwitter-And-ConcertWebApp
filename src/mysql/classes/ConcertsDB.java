package mysql.classes;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import model.Shows;

public class ConcertsDB {
	public ArrayList<Shows> searchResult(String search) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	ArrayList<Shows> searchResult = db.searchPerformance(search);
       	db.closeConnection();
		return searchResult;
	}
	public Shows detailResult(String performanceName) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		Shows searchResult = new Shows();
		searchResult = db.detailSearch(performanceName);
		db.closeConnection();
		return searchResult;
	}
}
