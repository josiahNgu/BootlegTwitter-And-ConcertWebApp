package mysql.classes;

import java.util.ArrayList;

public class ConcertsDB {
	public ArrayList<String> searchResult(String search) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	ArrayList<String> searchResult = db.searchPerformance(search);
       	db.closeConnection();
		return searchResult;
	}
}
