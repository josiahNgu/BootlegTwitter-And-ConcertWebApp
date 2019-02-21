<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Concert Detail</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
</head>
<style type="text/css">
.commentSection{
background-color : whitesmoke;
}
.stars-container {
 position: relative;
 display: inline-block;
 color: transparent;
 font-size: 1.5rem;
}
.stars-container:before{
 position: absolute;
 top: 0;
 left: 0;
content: "★★★★★";
color:lightgray;
}
.stars-container:after {
 position: absolute;
 top: 0;
 left: 0;
 content: "★★★★★";
 color: gold;
 overflow: hidden;
}
.stars-0:after { width: 0%; }
.stars-10:after { width: 20%; }
.stars-20:after { width: 40%; }
.stars-30:after { width: 50%; }
.stars-40:after { width: 80%; }
.stars-50:after { width: 100%; }
</style>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders">Order</a></li>
				<li class="nav-item"><a class="nav-link"
					href="ViewAndCheckoutShoppingCart.jsp">Cart</a></li>
				<li class="nav-item"><form action=Logout method="post">
						<input type="submit" class="btn btn-secondary" value="logout" />
					</form></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div style="padding-bottom: 3vh;"></div>
		<div class="row border border-danger rounded-top rounded-bottom"
			style="padding: 5vh;">
			<div class="d-flex col-xs-12 row ">
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					<img class="img-fluid" src="${detailResult.thumbnail} "
					alt="${detailResult.movieName}">
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
					<h3>${detailResult.movieName}</h3>
					<p>The performance will begin at ${detailResult.startTime} and
						ends at ${detailResult.endTime} at the ${detailResult.venue}.
						There is only ${detailResult.seatLeft} seats left. Purchase a
						ticket at &dollar;${detailResult.ppSeat} per ticket now!</p>
					<div class="align-self-center">
						<form action=UpdateShoppingCart method="post">
							<select class="w-25" name="numOfTickets">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select> <input type="hidden" name="ticketPrice"
								value="${detailResult.ppSeat}">
							<button type="submit" class="btn btn-danger w-50">Add To
								Cart</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div style="padding-top: 5vh">
			<a href="ConcertSearchResult.jsp"> <input type="button"
				class="btn btn-dark" value="Back" />
			</a>
		</div>
	</div>
	<div class="commentSection">
		<hr>

		<!-- 		<div class="row" style="padding: 5vh;">
			<div class="star-ratings-sprite">
				<span style="width: 80%" class="star-ratings-sprite-rating"></span>
			</div> -->

		<div class="col-lg-12  ">
		<div class="d-flex flex-column align-items-center justify-content-center">
			<span class="stars-container stars-${overallRating}0" style="padding-top:2vh;">★★★★★</span>
			<a href="CustomerReview.jsp"> <input type="button"
				class="btn btn-dark" value="Add comment" />
			</a>
			</div>
			<c:forEach var="comments" items="${comments}">
				<div>
					<div class="col-lg-6">
						<div class="col">
							<span class="stars-container stars-${comments.rating}0" style="padding-top:2vh;">★★★★★</span>
							<h4>${comments.userName}</h4>
							<p>${comments.date}</p>
							<p>${comments.comment}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>