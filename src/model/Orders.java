package model;

public class Orders {
	private int orderNumber;
	private int orderTotal;
	private String orderDate;
	private String billingAddress;
	private int quantity;
	
	public Orders(int orderNumber, int orderTotal, String orderDate, String billingAddress, int quantity) {
		super();
		this.orderNumber = orderNumber;
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
		this.billingAddress = billingAddress;
		this.quantity = quantity;
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


}
