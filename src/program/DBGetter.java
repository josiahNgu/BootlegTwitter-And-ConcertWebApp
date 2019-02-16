package program;

import mysql.classes.DBAccessClass;

public class DBGetter {
	public static void main(String[] args) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.getVenue();
		db.closeConnection();
	}
}
