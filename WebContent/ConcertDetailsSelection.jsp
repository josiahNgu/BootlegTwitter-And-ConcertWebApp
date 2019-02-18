<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Concert Detail</title>
<link rel="stylesheet" href="styles/styles.css">

</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script> 
<body">
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link"  href="ViewOrders.jsp">Order</a></li>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div style="padding-bottom: 3vh;"></div>
		<div class="row" style="border: black 1px solid; padding: 5vh;">
			<div class="d-flex col-xs-12 row ">
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					<img class="img-fluid" src=${detailResult.thumbnail} alt=${detailResult.movieName}>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 d-flex flex-column">
					<h3>${detailResult.movieName}</h3>
					<p></p>
					<p>The performance will begin at ${detailResult.startTime}  and ends at ${detailResult.endTime}.
					   There is only ${detailResult.seatLeft} seats left. Purchase a ticket at &dollar;${detailResult.ppSeat} pax now</p>
					
					<a href="ViewAndCheckoutShoppingCart.jsp"> <input type="button" class="btn btn-dark w-25" value="Add to Cart"/>
		</a>
				</div>
			</div>
		</div>
		<div style="padding-top:5vh">
		<a href="ConcertSearchResult.jsp"> <input type="button"
			class="btn btn-dark" value="Back"/>
		</a>
		</div>
	</div>
	<div class="commentSection">
		<hr>
		<div class="row" style="padding: 5vh;">
			<div class="star-ratings-sprite">
				<span style="width: 80%" class="star-ratings-sprite-rating"></span>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-8 d-flex flex-column">
				<div class="star-ratings-sprite">
					<span style="width: 80%" class="star-ratings-sprite-rating"></span>
				</div>
				<h4>Jerry</h4>
				<p>08/14/2019</p>
				<p>It was an amazing performance...</p>
			</div>

		</div>
	</div>
	</div>
</body>
</html>