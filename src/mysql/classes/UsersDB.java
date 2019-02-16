package mysql.classes;

import mysql.classes.DBAccessClass;
import model.Users;

public class UsersDB {

	public void registerUser(Users aUser) {
       	DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	db.addSingleUser(aUser);
       	db.closeConnection();
    }
    
    public boolean validateUserByUsername(String aUserName) {
    	boolean userExists = false;
       	DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	userExists = db.findUserByUsername(aUserName);
       	db.closeConnection();
       	
       	return userExists;
    }
    
    public boolean validateUser(String aUserName, String password) {
	    boolean passwordMatches = false;
   	    DBAccessClass db = new DBAccessClass();
   	    db.connectMeIn();
   	    passwordMatches = db.validateUser(aUserName,password);
   	    db.closeConnection();
   	
   	    return passwordMatches;
    }
    
    
    public Users getUser(String aUserName) {   
	   	DBAccessClass db = new DBAccessClass();
	   	db.connectMeIn();
	   	Users aUser = db.returnUserByUsername(aUserName);
	   	db.closeConnection();
	   	
	   	return aUser;
    }

}
