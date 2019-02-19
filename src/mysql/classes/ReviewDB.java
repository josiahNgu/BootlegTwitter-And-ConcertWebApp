package mysql.classes;

import java.util.ArrayList;

import model.Review;

public class ReviewDB {
	public void addReview(String comment,String userId, String movieName, String currentDate,String rating) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	db.addUserComment(comment,userId,movieName,currentDate,rating);
       	db.closeConnection();		
	}
	public ArrayList<Review> getReview(String movieName) {
		DBAccessClass db = new DBAccessClass();
       	db.connectMeIn();
       	ArrayList<Review>searchResult = db.getReview(movieName);
       	db.closeConnection();
		return searchResult;	
	}
}
