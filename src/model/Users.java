package model;


public class Users {
	
	private String userName;
	private String password;
	
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
	public Users(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public Users() {
		super();
	}
	
/*	public void registerUser(Users aUser, String propFilePath) {
		
		Properties p = new Properties();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(propFilePath);
			p.load(fis);
			p.setProperty(aUser.getUserName(), aUser.getPassword());
			p.store(new FileOutputStream(propFilePath), null);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	// validateUser

	public boolean validateUser(String userName, String password, String propFilePath) {
		Properties p = new Properties();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(propFilePath);

			p.load(fis);

			// Check whether the username exists or not
			if (!p.containsKey(userName)) {
				// Link-redirection
				fis.close();
				return false;
			} else { // Check whether the password matches or not
				String pword = p.getProperty(userName);
				if (!pword.equals(password)) {
					fis.close();
					return false; // Link-redirection
				} else {
					fis.close();
					return true; // Link-redirection
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}*/
	// removeUser
	
	
}
