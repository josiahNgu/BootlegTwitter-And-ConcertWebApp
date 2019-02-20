package model;

public class Orders {
	private int orderNumber;
	private int orderTotal;
	private String orderDate;
	private String billingAddress;
	private int quantity;
	private int ticketPrice;
	private String movieName;
	private String venueName;
	private String showTime;
	private int itemTotalPrice;
	
	/*
	 * required data: 
	 * orderNumber,movieName,quantity,Total price,Venue name,Showtime/Date,orderTotal,OrderDate
	 */
	
	

	public Orders(int orderNumber, int orderTotal, String orderDate, String billingAddress) {
		super();
		this.orderNumber = orderNumber;
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
		this.billingAddress = billingAddress;
	}
	
	public Orders(int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity, int ticketPrice,
			String movieName, String venueName, String showTime, int itemTotalPrice) {
		super();
		this.orderNumber = orderNumber;
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
		this.billingAddress = billingAddress;
		this.quantity = quantity;
		this.ticketPrice = ticketPrice;
		this.movieName = movieName;
		this.venueName = venueName;
		this.showTime = showTime;
		this.itemTotalPrice = itemTotalPrice;
	}

	public Orders() {
		super();
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public int getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(int itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}
	
	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
