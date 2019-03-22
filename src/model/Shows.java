package model;

public class Shows {
	private String startTime, endTime,movieName,venue,thumbnail,rating,description,openingAct,seatLeft,ppSeat,numOfreqSeat,performanceId ="nil";

	private int orderCost = 0;

	public Shows() {};
	public Shows(String startTime, String endTime, String movieName, String venue, String thumbnail, String rating,String seatLeft,
			String ppSeat,String performanceId) {
		setStartTime(startTime);
		setEndTime(endTime);
		setMovieName(movieName);
		setVenue(venue);
		setThumbnail(thumbnail);
		setRating(rating);
		setSeatLeft(seatLeft);
		setPpSeat(ppSeat);
		setPerformanceId(performanceId);

	}
	public Shows(String startTime, String endTime, String movieName, String venue, String thumbnail, String rating,String description,
			String seatLeft,String ppSeat,String performanceId) {
		setStartTime(startTime);
		setEndTime(endTime);
		setMovieName(movieName);
		setVenue(venue);
		setThumbnail(thumbnail);
		setRating(rating);
		setDescription(description);
		setSeatLeft(seatLeft);
		setPpSeat(ppSeat);
		setPerformanceId(performanceId);
	}
	public Shows(String startTime, String endTime, String movieName, String venue, String thumbnail, String rating,String description,
			String seatLeft,String ppSeat, String numOfreqSeat,int orderCost,String performanceId) {
		setStartTime(startTime);
		setEndTime(endTime);
		setMovieName(movieName);
		setVenue(venue);
		setThumbnail(thumbnail);
		setRating(rating);
		setDescription(description);
		setSeatLeft(seatLeft);
		setPpSeat(ppSeat);
		setNumOfreqSeat(numOfreqSeat);
		setOrderCost(orderCost);
		setPerformanceId(performanceId);
	}
	public String getPerformanceId() {
		return performanceId;
	}
	public void setPerformanceId(String performanceId) {
		this.performanceId = performanceId;
	}

	public int getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(int orderCost) {
		this.orderCost = orderCost;
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
	public String getNumOfreqSeat() {
		return numOfreqSeat;
	}
	public void setNumOfreqSeat(String numOfreqSeat) {
		this.numOfreqSeat = numOfreqSeat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOpeningAct() {
		return openingAct;
	}
	public void setOpeningAct(String openingAct) {
		this.openingAct = openingAct;
	}
	public String getSeatLeft() {
		return seatLeft;
	}
	public void setSeatLeft(String seatLeft) {
		this.seatLeft = seatLeft;
	}
	public String getPpSeat() {
		return ppSeat;
	}
	public void setPpSeat(String ppSeat) {
		this.ppSeat = ppSeat;
	}
}
