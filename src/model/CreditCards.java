package model;

public class CreditCards {
	private String firstName,lastName,cardNumber,cardType,expiryMonth,expirtYear,cvc,billingAddress,userId = "nil";

	public CreditCards() {}
	
	public CreditCards(String firstName, String lastName, String cardNumber, String cardType, String expiryMonth,
			String expirtYear, String cvc, String billingAddress, String userId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.expiryMonth = expiryMonth;
		this.expirtYear = expirtYear;
		this.cvc = cvc;
		this.billingAddress = billingAddress;
		this.userId = userId;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpirtYear() {
		return expirtYear;
	}

	public void setExpirtYear(String expirtYear) {
		this.expirtYear = expirtYear;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	
	
}
