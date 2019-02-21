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
	private int orderItemId;
	private String creditCardNumber;
	private int performanceId;
	
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
			String movieName, String venueName, String showTime, int itemTotalPrice, int orderItemId) {
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
		this.orderItemId = orderItemId;
	}

	public Orders(int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity,
			int ticketPrice, String movieName, String venueName, String showTime, int itemTotalPrice, int orderItemId,
			String creditCardNumber, int performanceId) {
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
		this.orderItemId = orderItemId;
		this.creditCardNumber = creditCardNumber;
		this.performanceId = performanceId;
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
	
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public int getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(int performanceId) {
		this.performanceId = performanceId;
	}
}
