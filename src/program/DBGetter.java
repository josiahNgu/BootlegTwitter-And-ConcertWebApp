package program;

import mysql.DBAccessClass;

public class DBGetter {
	public static void main(String[] args) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.closeConnection();
	}
}
