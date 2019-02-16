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
				<li class="nav-item"><a class="nav-link"  href="ViewOrders.jsp">Order</a></li>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div style="padding-bottom: 3vh;"></div>
		<div class="row" style="border: black 1px solid; padding: 5vh;">
			<div class="d-flex col-xs-12 row ">
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
					<img src="pics/rennieHarris.jpg" alt="Rennie Harris Puremovement">
				</div>
				<div
					class="col-xs-12 col-sm-12 col-md-6 col-lg-8 d-flex flex-column">
					<h3>The King &amp; I</h3>
					<p>Two worlds collide in this “breathtaking and exquisite” (The
						New York Times) musical, based on the 2015 Tony Award®-winning
						Lincoln Center Theater production. One of Rodgers & Hammerstein’s
						finest works, THE KING AND I boasts a score that features such
						beloved classics as “Getting To Know You,” “I Whistle a Happy
						Tune,” “Hello Young Lovers,” “Shall We Dance” and “Something
						Wonderful.”</p>
					<p>The performance will begin at 8:30 pm and there is only 10
						seats left. Purchase a ticket at $25 pax</p>
					<a href="ViewAndCheckoutShoppingCart.jsp"> <input type="button"
			class="btn btn-dark w-25" value="Add to Cart"/>
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