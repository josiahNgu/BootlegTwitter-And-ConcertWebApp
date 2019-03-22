package mysql;

import model.Bank;
import mysql.DBAccessClass;

public class BankDB {
	//insert new credit card
	public void CreditCards(Bank userCard) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.creditCards(userCard);
		db.closeConnection();
	}
	// return the remaining Fund on the card
	public String creditCardsValid(String cardNumber,int subtotal) {
		Bank cards = new Bank();
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
	// if existing card is in database
	public boolean cardExisted(String cardNumber) {
		DBAccessClass db = new DBAccessClass();
		Bank cards = new Bank();
		db.connectMeIn();
		cards = db.creditCardDetails(cardNumber);
		db.closeConnection();
		if(cards.getCardNumber()== null){
			return true;
		}
		return false;
	}
	public void updateBalance(String cardNumber,String balance) {
		DBAccessClass db = new DBAccessClass();
		db.connectMeIn();
		db.updateCreditCardBalance(cardNumber,balance);
		db.closeConnection();	
	}
}
