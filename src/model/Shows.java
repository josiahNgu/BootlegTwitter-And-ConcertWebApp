package model;

public class Shows {
	private String startTime, endTime,movieName,venue,thumbnail,rating ="nil";
	public Shows() {};
	public Shows(String startTime, String endTime, String movieName, String venue, String thumbnail, String rating) {
		setStartTime(startTime);
		setEndTime(endTime);
		setMovieName(movieName);
		setVenue(venue);
		setThumbnail(thumbnail);
		setRating(rating);

	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
