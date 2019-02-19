package mysql.classes;

import model.CreditCards;

public class CreditCardsDB {
	public void CreditCards(CreditCards userCard) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	db.creditCards(userCard);
       	db.closeConnection();
	}
}
