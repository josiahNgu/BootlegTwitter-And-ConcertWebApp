package model;


public class Users {
	
	private String userName;
	private String password;
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//used for login
	public Users(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	//used for registering
	public Users(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public Users() {
		super();
	}
	
}
