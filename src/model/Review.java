package model;

public class Review {
private String userName, rating, comment, date = "nil";

public Review(String userName,String rating, String review, String reviewDate) {
	setUserName(userName);
	setDate(reviewDate);
	setRating(rating);
	setComment(review);
}


public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getRating() {
	return rating;
}

public void setRating(String rating) {
	this.rating = rating;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}

}
