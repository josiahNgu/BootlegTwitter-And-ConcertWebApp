package mysql.classes;


import model.CreditCards;

public class TransactionDB {
	public void CreditCards(CreditCards userCard) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.creditCards(userCard);
		db.closeConnection();
	}
	public void addOrder(String orderNumber,String customerId,String totalCost,String orderDate,String billingAddress,String creditcard) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.insertToOrders(orderNumber, customerId, totalCost, orderDate, billingAddress, creditcard);
		db.closeConnection();

	}
	public void addOrderItems(String orderNumber,String performanceId,String quantity) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.insertToOrderItems(orderNumber, performanceId, quantity);
		db.closeConnection();
	}
	// return the remaining Fund on the card
	public String creditCardsValid(String cardNumber,int subtotal) {
		CreditCards cards = new CreditCards();
		Double remainingFund = 0.0;
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		cards = db.creditCardDetails(cardNumber);
		db.closeConnection();	
		if(!(cards.getCardNumber()== null) && !(cards.getBalance() == null)) {
			remainingFund = Double.parseDouble(cards.getBalance()) - subtotal;
		}
		return remainingFund.toString();
	}
	// update the balance on card
	public void updateBalance(String cardNumber,String balance) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.updateCreditCardBalance(cardNumber,balance);
		db.closeConnection();	
	}
	public boolean cardExisted(String cardNumber) {
		DBAccessClass db = new DBAccessClass();
		CreditCards cards = new CreditCards();
		db.connectMeIn();
		cards = db.creditCardDetails(cardNumber);
		db.closeConnection();
		if(cards.getCardNumber()== null){
			return true;
		}
		return false;
	}
	public void decreaseSeat(String updateNumber ,String performanceId) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.decreaseSeat(updateNumber, performanceId);
		db.closeConnection();

	}
	public String getSeat(String performanceId) {
		DBAccessClass db = new DBAccessClass();
		String seatLeft = "0";
		db.connectMeIn();
		seatLeft = db.seatLeft(performanceId);
		db.closeConnection();
		return seatLeft;
	}
}
