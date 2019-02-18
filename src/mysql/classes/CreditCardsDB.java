package mysql.classes;



public class CreditCardsDB {
	public void getCreditCards(String user) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	//db.creditCards(user);
       	db.closeConnection();
	}
}
